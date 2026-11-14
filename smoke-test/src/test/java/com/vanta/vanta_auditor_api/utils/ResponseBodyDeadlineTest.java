package com.vanta.vanta_auditor_api.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpTimeoutException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

@Timeout(10)
class ResponseBodyDeadlineTest {
    @TempDir
    Path directory;

    @Test
    void cancelsAnAsyncBodyThatNeverCompletes() {
        StallingPublisher source = new StallingPublisher();
        Blob body = body(source);

        ExecutionException failure = assertThrows(ExecutionException.class,
                () -> body.toByteArray().get(5, TimeUnit.SECONDS));

        assertInstanceOf(HttpTimeoutException.class, failure.getCause());
        assertEquals(0, source.cancelled.getCount());
    }

    @Test
    void cancelsAnUnconsumedBodyAtTheDeadline() throws Exception {
        StallingPublisher source = new StallingPublisher();
        Blob body = body(source);

        assertTrue(source.cancelled.await(5, TimeUnit.SECONDS));
        ExecutionException failure = assertThrows(ExecutionException.class,
                () -> body.toByteArray().get(5, TimeUnit.SECONDS));

        assertInstanceOf(HttpTimeoutException.class, failure.getCause());
    }

    @Test
    void cancelsASubscriptionThatArrivesAfterTheDeadline() throws Exception {
        StallingPublisher source = new StallingPublisher();
        source.delaySubscription = true;
        Blob body = body(source);

        ExecutionException failure = assertThrows(ExecutionException.class,
                () -> body.toByteArray().get(5, TimeUnit.SECONDS));
        source.subscriber.onSubscribe(source.subscription);

        assertInstanceOf(HttpTimeoutException.class, failure.getCause());
        assertTrue(source.cancelled.await(5, TimeUnit.SECONDS));
        assertEquals(0, source.requests.get());
    }

    @Test
    void cancellingCollectionCancelsTheSubscription() throws Exception {
        StallingPublisher source = new StallingPublisher();
        CompletableFuture<byte[]> result = body(source).toByteArray();

        assertTrue(result.cancel(true));

        assertTrue(source.cancelled.await(5, TimeUnit.SECONDS));
    }

    @Test
    void inputStreamConsumptionKeepsTheDeadline() throws Exception {
        StallingPublisher source = new StallingPublisher();
        try (InputStream input = body(source).toInputStream().get(5, TimeUnit.SECONDS)) {
            IOException failure = assertThrows(IOException.class, input::read);
            assertInstanceOf(HttpTimeoutException.class, failure.getCause());
        }
        assertEquals(0, source.cancelled.getCount());
    }

    @Test
    void fileConsumptionKeepsTheDeadline() {
        StallingPublisher source = new StallingPublisher();

        ExecutionException failure = assertThrows(ExecutionException.class,
                () -> body(source).toFile(directory.resolve("response")).get(5, TimeUnit.SECONDS));

        assertInstanceOf(HttpTimeoutException.class, failure.getCause());
        assertEquals(0, source.cancelled.getCount());
    }

    @Test
    void completionBeforeConsumptionIsDeliveredOnce() throws Exception {
        AtomicInteger completions = new AtomicInteger();
        Flow.Publisher<List<ByteBuffer>> source = subscriber -> {
            subscriber.onSubscribe(new Flow.Subscription() {
                @Override
                public void request(long count) {
                }

                @Override
                public void cancel() {
                }
            });
            subscriber.onComplete();
        };
        Blob body = Blob.from(new ResponseBodyPublisher(source, new ResponseBodyDeadline(Duration.ofSeconds(5))));

        byte[] result = body.toByteArray().whenComplete((bytes, error) -> completions.incrementAndGet())
                .get(5, TimeUnit.SECONDS);

        assertArrayEquals(new byte[0], result);
        assertEquals(1, completions.get());
    }

    @Test
    void completionAfterTheDeadlineCannotSucceedBeforeTheTimerRuns() {
        Flow.Publisher<List<ByteBuffer>> source = subscriber -> {
            subscriber.onSubscribe(new Flow.Subscription() {
                @Override
                public void request(long count) {
                }

                @Override
                public void cancel() {
                }
            });
            subscriber.onComplete();
        };
        Blob body = Blob.from(new ResponseBodyPublisher(source, new ResponseBodyDeadline(Duration.ZERO)));

        ExecutionException failure = assertThrows(ExecutionException.class,
                () -> body.toByteArray().get(5, TimeUnit.SECONDS));

        assertInstanceOf(HttpTimeoutException.class, failure.getCause());
    }

    @Test
    void aBlockedSubscriberDoesNotPreventNetworkCancellation() throws Exception {
        StallingPublisher source = new StallingPublisher();
        CountDownLatch receiving = new CountDownLatch(1);
        CountDownLatch release = new CountDownLatch(1);
        Blob body = Blob.from(new ResponseBodyPublisher(source, new ResponseBodyDeadline(Duration.ofSeconds(2))));
        body.asPublisher().subscribe(new Flow.Subscriber<ByteBuffer>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                subscription.request(1);
            }

            @Override
            public void onNext(ByteBuffer buffer) {
                receiving.countDown();
                await(release);
            }

            @Override
            public void onError(Throwable error) {
            }

            @Override
            public void onComplete() {
            }
        });
        CompletableFuture<Void> delivery = CompletableFuture.runAsync(
                () -> source.subscriber.onNext(List.of(ByteBuffer.wrap(new byte[] {1}))));

        try {
            assertTrue(receiving.await(5, TimeUnit.SECONDS));
            assertTrue(source.cancelled.await(5, TimeUnit.SECONDS));
        } finally {
            release.countDown();
        }
        delivery.get(5, TimeUnit.SECONDS);
    }

    @Test
    void anApplicationErrorCallbackDoesNotBlockOtherRequestDeadlines() throws Exception {
        StallingPublisher first = new StallingPublisher();
        CountDownLatch handlingError = new CountDownLatch(1);
        CountDownLatch release = new CountDownLatch(1);
        Blob firstBody = Blob.from(new ResponseBodyPublisher(first, new ResponseBodyDeadline(Duration.ofSeconds(2))));
        firstBody.toByteArray().whenComplete((bytes, error) -> {
            handlingError.countDown();
            await(release);
        });

        try {
            assertTrue(handlingError.await(5, TimeUnit.SECONDS));
            StallingPublisher second = new StallingPublisher();
            ExecutionException failure = assertThrows(ExecutionException.class,
                    () -> body(second).toByteArray().get(5, TimeUnit.SECONDS));
            assertInstanceOf(HttpTimeoutException.class, failure.getCause());
            assertEquals(0, second.cancelled.getCount());
        } finally {
            release.countDown();
        }
    }

    private static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
        }
    }

    private static Blob body(StallingPublisher source) {
        return Blob.from(new ResponseBodyPublisher(source, new ResponseBodyDeadline(Duration.ofMillis(100))));
    }

    private static final class StallingPublisher implements Flow.Publisher<List<ByteBuffer>> {
        private final CountDownLatch cancelled = new CountDownLatch(1);
        private final AtomicInteger requests = new AtomicInteger();
        private Flow.Subscriber<? super List<ByteBuffer>> subscriber;
        private boolean delaySubscription;
        private final Flow.Subscription subscription = new Flow.Subscription() {
            @Override
            public void request(long count) {
                requests.incrementAndGet();
            }

            @Override
            public void cancel() {
                cancelled.countDown();
            }
        };

        @Override
        public void subscribe(Flow.Subscriber<? super List<ByteBuffer>> downstream) {
            subscriber = downstream;
            if (!delaySubscription) {
                downstream.onSubscribe(subscription);
            }
        }
    }
}
