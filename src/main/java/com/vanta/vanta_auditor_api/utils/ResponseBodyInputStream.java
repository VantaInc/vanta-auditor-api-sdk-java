package com.vanta.vanta_auditor_api.utils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpTimeoutException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;

final class ResponseBodyInputStream extends FilterInputStream {
    private final int maxBufferedBodyBytes;
    private final ResponseBodyDeadline deadline;
    private final AtomicReference<State> state = new AtomicReference<>(State.READING);
    private final HttpTimeoutException timeout = ResponseBodyDeadline.timeoutException();
    private final ScheduledFuture<?> timeoutTask;

    private enum State {
        READING, FINISHED, TIMED_OUT
    }

    ResponseBodyInputStream(InputStream source, int maxBufferedBodyBytes) {
        this(source, maxBufferedBodyBytes, null);
    }

    ResponseBodyInputStream(InputStream source, int maxBufferedBodyBytes, ResponseBodyDeadline deadline) {
        super(source);
        this.maxBufferedBodyBytes = maxBufferedBodyBytes;
        this.deadline = deadline;
        timeoutTask = deadline == null ? null : deadline.schedule(this::expire);
    }

    static int bufferLimit(InputStream input) {
        return input instanceof ResponseBodyInputStream
                ? ((ResponseBodyInputStream) input).maxBufferedBodyBytes
                : Utils.MAX_BUFFERED_BODY_BYTES;
    }

    @Override
    public synchronized int read() throws IOException {
        checkDeadline();
        try {
            return afterRead(super.read());
        } catch (IOException error) {
            checkDeadline();
            throw error;
        }
    }

    @Override
    public synchronized int read(byte[] buffer, int offset, int length) throws IOException {
        checkDeadline();
        try {
            return afterRead(super.read(buffer, offset, length));
        } catch (IOException error) {
            checkDeadline();
            throw error;
        }
    }

    private int afterRead(int count) throws IOException {
        checkDeadline();
        if (count == -1) {
            finish();
            checkDeadline();
        }
        return count;
    }

    private void checkDeadline() throws HttpTimeoutException {
        if (deadline != null && deadline.isExpired() && state.get() == State.READING) {
            expire();
        }
        if (state.get() == State.TIMED_OUT) {
            throw timeout;
        }
    }

    private void expire() {
        if (state.compareAndSet(State.READING, State.TIMED_OUT)) {
            try {
                // Close the source directly so a blocked read cannot hold up expiry.
                in.close();
            } catch (IOException error) {
                timeout.addSuppressed(error);
            }
        }
    }

    private void finish() {
        state.compareAndSet(State.READING, State.FINISHED);
        if (timeoutTask != null) {
            timeoutTask.cancel(false);
        }
    }

    @Override
    public void close() throws IOException {
        finish();
        super.close();
    }
}
