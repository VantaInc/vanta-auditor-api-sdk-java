package com.vanta.vanta_auditor_api.utils;

import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class ResponseBodyDeadline {
    private final long expiresAt;

    ResponseBodyDeadline(Duration timeout) {
        expiresAt = System.nanoTime() + timeout.toNanos();
    }

    boolean isExpired() {
        return expiresAt - System.nanoTime() <= 0;
    }

    ScheduledFuture<?> schedule(Runnable expire) {
        return Scheduler.INSTANCE.schedule(expire, Math.max(0, expiresAt - System.nanoTime()), TimeUnit.NANOSECONDS);
    }

    static HttpTimeoutException timeoutException() {
        return new HttpTimeoutException("HTTP request deadline exceeded while reading the response body");
    }

    private static final class Scheduler {
        private static final ScheduledThreadPoolExecutor INSTANCE = create();

        private static ScheduledThreadPoolExecutor create() {
            ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1, task -> {
                Thread thread = new Thread(task, "vanta-response-deadline");
                thread.setDaemon(true);
                return thread;
            });
            scheduler.setRemoveOnCancelPolicy(true);
            return scheduler;
        }
    }
}
