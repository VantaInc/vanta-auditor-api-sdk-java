package com.vanta.vanta_auditor_api.utils;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;
import java.util.concurrent.ScheduledFuture;

final class ResponseBodyPublisher implements Flow.Publisher<List<ByteBuffer>>, Flow.Subscriber<List<ByteBuffer>> {
    private final ResponseBodyDeadline deadline;
    private volatile Flow.Subscription upstream;
    private Flow.Subscriber<? super List<ByteBuffer>> downstream;
    private ScheduledFuture<?> timeoutTask;
    private boolean subscribed;
    private boolean consumerRegistered;
    private volatile boolean finished;
    private boolean terminalDelivered;
    private Throwable error;
    private volatile Throwable expired;

    ResponseBodyPublisher(Flow.Publisher<List<ByteBuffer>> source, ResponseBodyDeadline deadline) {
        this.deadline = deadline;
        source.subscribe(this);
        ScheduledFuture<?> task = deadline.schedule(this::expire);
        synchronized (this) {
            timeoutTask = task;
            if (finished) {
                task.cancel(false);
            }
        }
    }

    @Override
    public synchronized void subscribe(Flow.Subscriber<? super List<ByteBuffer>> subscriber) {
        Objects.requireNonNull(subscriber, "subscriber");
        if (consumerRegistered) {
            subscriber.onSubscribe(new Flow.Subscription() {
                @Override
                public void request(long count) {
                }

                @Override
                public void cancel() {
                }
            });
            subscriber.onError(new IllegalStateException("Response body already subscribed"));
            return;
        }
        consumerRegistered = true;
        downstream = subscriber;
        if (expired != null) {
            fail(expired);
        }
        notifySubscriber();
    }

    @Override
    public synchronized void onSubscribe(Flow.Subscription subscription) {
        if (upstream != null || finished || expired != null) {
            subscription.cancel();
            return;
        }
        upstream = subscription;
        notifySubscriber();
    }

    private void notifySubscriber() {
        if (downstream == null) {
            return;
        }
        if (!subscribed && (upstream != null || finished)) {
            subscribed = true;
            downstream.onSubscribe(new Flow.Subscription() {
                @Override
                public void request(long count) {
                    synchronized (ResponseBodyPublisher.this) {
                        if (!finished && expired == null && upstream != null) {
                            upstream.request(count);
                        }
                    }
                }

                @Override
                public void cancel() {
                    synchronized (ResponseBodyPublisher.this) {
                        finished = true;
                        terminalDelivered = true;
                        downstream = null;
                        cancelTimeout();
                        if (upstream != null) {
                            upstream.cancel();
                            upstream = null;
                        }
                    }
                }
            });
        }
        if (finished && subscribed && !terminalDelivered) {
            terminalDelivered = true;
            Flow.Subscriber<? super List<ByteBuffer>> subscriber = downstream;
            downstream = null;
            upstream = null;
            if (error == null) {
                subscriber.onComplete();
            } else {
                subscriber.onError(error);
            }
        }
    }

    @Override
    public synchronized void onNext(List<ByteBuffer> buffers) {
        if (deadline.isExpired()) {
            fail(ResponseBodyDeadline.timeoutException());
            return;
        }
        if (!finished && subscribed) {
            downstream.onNext(buffers);
        }
    }

    @Override
    public synchronized void onError(Throwable cause) {
        fail(expired == null ? cause : expired);
    }

    private void expire() {
        if (!finished) {
            Throwable timeout = ResponseBodyDeadline.timeoutException();
            expired = timeout;
            Flow.Subscription subscription = upstream;
            if (subscription != null) {
                subscription.cancel();
            }
            // Completing a future can run application callbacks; keep them off the shared timer.
            CompletableFuture.runAsync(() -> fail(timeout));
        }
    }

    private synchronized void fail(Throwable cause) {
        if (!finished) {
            finished = true;
            error = cause;
            cancelTimeout();
            if (upstream != null) {
                upstream.cancel();
            }
            notifySubscriber();
        }
    }

    @Override
    public synchronized void onComplete() {
        if (deadline.isExpired()) {
            fail(ResponseBodyDeadline.timeoutException());
            return;
        }
        if (!finished) {
            finished = true;
            cancelTimeout();
            notifySubscriber();
        }
    }

    private void cancelTimeout() {
        if (timeoutTask != null) {
            timeoutTask.cancel(false);
        }
    }
}
