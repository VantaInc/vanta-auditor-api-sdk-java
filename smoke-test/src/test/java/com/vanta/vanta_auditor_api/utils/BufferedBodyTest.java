package com.vanta.vanta_auditor_api.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BufferedBodyTest {
    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void syncCollectionCountsBytesAndClosesTheSource(int length) throws Exception {
        AtomicBoolean closed = new AtomicBoolean();
        byte[] expected = new byte[length];
        ByteArrayInputStream body = new ByteArrayInputStream(expected) {
            @Override
            public void close() {
                closed.set(true);
            }
        };

        if (length == 8) {
            assertArrayEquals(expected, Utils.toByteArrayAndClose(body, 8));
        } else {
            assertThrows(IOException.class, () -> Utils.toByteArrayAndClose(body, 8));
        }
        assertTrue(closed.get());
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void asyncCollectionCountsUnknownLengthBytesAndCancelsOnOverflow(int length) throws Exception {
        AtomicBoolean cancelled = new AtomicBoolean();
        byte[] expected = new byte[length];
        Flow.Publisher<List<ByteBuffer>> publisher = subscriber -> subscriber.onSubscribe(new Flow.Subscription() {
            private int sent;

            @Override
            public void request(long count) {
                if (sent == length) {
                    subscriber.onComplete();
                } else {
                    sent++;
                    subscriber.onNext(List.of(ByteBuffer.wrap(new byte[1])));
                }
            }

            @Override
            public void cancel() {
                cancelled.set(true);
            }
        });
        var result = Blob.from(publisher, 8, -1).toByteArray();

        if (length == 8) {
            assertArrayEquals(expected, result.get(5, TimeUnit.SECONDS));
        } else {
            ExecutionException failure = assertThrows(ExecutionException.class, () -> result.get(5, TimeUnit.SECONDS));
            assertInstanceOf(IOException.class, failure.getCause());
        }
        assertEquals(length > 8, cancelled.get());
    }

    @Test
    void declaredLengthIsRejectedBeforeRequestingBytes() {
        AtomicInteger requests = new AtomicInteger();
        AtomicBoolean cancelled = new AtomicBoolean();
        Flow.Publisher<List<ByteBuffer>> publisher = subscriber -> subscriber.onSubscribe(new Flow.Subscription() {
            @Override
            public void request(long count) {
                requests.incrementAndGet();
            }

            @Override
            public void cancel() {
                cancelled.set(true);
            }
        });

        ExecutionException failure = assertThrows(ExecutionException.class,
                () -> Blob.from(publisher, 8, 9).toByteArray().get(5, TimeUnit.SECONDS));

        assertInstanceOf(IOException.class, failure.getCause());
        assertEquals(0, requests.get());
        assertTrue(cancelled.get());
    }
}
