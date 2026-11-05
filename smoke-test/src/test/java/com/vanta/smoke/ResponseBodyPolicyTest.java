package com.vanta.smoke;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.errors.APIException;
import com.vanta.vanta_auditor_api.models.errors.AsyncAPIException;
import com.vanta.vanta_auditor_api.models.errors.VantaError;
import com.vanta.vanta_auditor_api.utils.Blob;
import com.vanta.vanta_auditor_api.utils.SpeakeasyHTTPClient;
import com.vanta.vanta_auditor_api.utils.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ResponseBodyPolicyTest extends LoopbackTest {
    @ParameterizedTest
    @ValueSource(ints = {200, 400, 302})
    void syncSdkRejectsOversizedChunkedBodiesWithHttpMetadata(int status) {
        respond(status, "x".repeat(128), true);
        Vanta sdk = sdk(new SpeakeasyHTTPClient(64, Duration.ofSeconds(5)));

        APIException error = assertThrows(APIException.class,
                () -> sdk.audits().getInformationRequest("audit", "request"));

        assertSizeFailure(error, status);
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 400, 302})
    void asyncSdkRejectsOversizedChunkedBodiesWithHttpMetadata(int status) {
        respond(status, "x".repeat(128), true);
        Vanta sdk = sdk(new SpeakeasyHTTPClient(64, Duration.ofSeconds(5)));

        ExecutionException failure = assertThrows(ExecutionException.class,
                () -> sdk.async().audits().getInformationRequest("audit", "request").get(10, TimeUnit.SECONDS));

        assertSizeFailure(assertInstanceOf(AsyncAPIException.class, failure.getCause()), status);
    }

    @Test
    void rejectsOversizedContentLengthWithoutWaitingForTheBody() {
        handler = exchange -> {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(400, 128);
            awaitRelease();
            exchange.close();
        };

        APIException error = assertThrows(APIException.class,
                () -> sdk(new SpeakeasyHTTPClient(64, Duration.ofSeconds(2)))
                        .audits().getInformationRequest("audit", "request"));

        assertSizeFailure(error, 400);
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void acceptsExactlyTheConfiguredLimit(boolean async) throws Exception {
        byte[] expected = "12345678".getBytes(StandardCharsets.UTF_8);
        respond(200, "12345678", true);
        SpeakeasyHTTPClient client = new SpeakeasyHTTPClient(expected.length, Duration.ofSeconds(5));

        byte[] actual;
        if (async) {
            HttpResponse<Blob> response = client.sendAsync(request()).get(10, TimeUnit.SECONDS);
            actual = response.body().toByteArray().get(10, TimeUnit.SECONDS);
        } else {
            actual = Utils.extractByteArrayFromBody(client.send(request()));
        }

        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"false,false", "false,true", "true,false", "true,true"})
    void bodyDeadlineStopsStalledAndTrickledSdkResponses(boolean async, boolean trickle) {
        handler = exchange -> {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, 0);
            try (OutputStream body = exchange.getResponseBody()) {
                body.write(' ');
                body.flush();
                while (!release.await(25, TimeUnit.MILLISECONDS)) {
                    if (trickle) {
                        body.write(' ');
                        body.flush();
                    }
                }
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            } finally {
                exchange.close();
            }
        };
        Vanta sdk = sdk(new SpeakeasyHTTPClient(1024 * 1024, Duration.ofSeconds(2)));

        VantaError error;
        if (async) {
            ExecutionException failure = assertThrows(ExecutionException.class,
                    () -> sdk.async().audits().getInformationRequest("audit", "request").get(10, TimeUnit.SECONDS));
            error = assertInstanceOf(AsyncAPIException.class, failure.getCause());
        } else {
            error = assertThrows(APIException.class,
                    () -> sdk.audits().getInformationRequest("audit", "request"));
        }

        assertEquals(200, error.code());
        assertInstanceOf(HttpTimeoutException.class, error.getCause());
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void requestDeadlineAlsoBoundsTheWaitForHeaders(boolean async) {
        handler = exchange -> {
            awaitRelease();
            exchange.close();
        };
        SpeakeasyHTTPClient client = new SpeakeasyHTTPClient(64, Duration.ofSeconds(2));

        if (async) {
            ExecutionException failure = assertThrows(ExecutionException.class,
                    () -> client.sendAsync(request()).get(10, TimeUnit.SECONDS));
            assertInstanceOf(HttpTimeoutException.class, failure.getCause());
        } else {
            assertThrows(HttpTimeoutException.class, () -> client.send(request()));
        }
    }

    @Test
    void preservesAnExplicitRequestTimeout() throws Exception {
        respond(200, "ok", false);
        SpeakeasyHTTPClient client = new SpeakeasyHTTPClient(64, Duration.ofNanos(1));
        HttpRequest request = HttpRequest.newBuilder(request().uri()).timeout(Duration.ofSeconds(5)).build();
        HttpResponse<InputStream> response = client.send(request);

        assertEquals(Optional.of(Duration.ofSeconds(5)), response.request().timeout());
        assertArrayEquals("ok".getBytes(StandardCharsets.UTF_8), Utils.extractByteArrayFromBody(response));
    }

    @Test
    void appliesTheConfiguredDefaultRequestTimeout() throws Exception {
        respond(200, "ok", false);
        HttpResponse<InputStream> response = new SpeakeasyHTTPClient(64, Duration.ofSeconds(5)).send(request());

        assertEquals(Optional.of(Duration.ofSeconds(5)), response.request().timeout());
        response.body().close();
    }

    private static void assertSizeFailure(VantaError error, int status) {
        assertEquals(status, error.code());
        assertEquals(status, error.rawResponse().statusCode());
        IOException cause = assertInstanceOf(IOException.class, error.getCause());
        assertTrue(cause.getMessage().contains("maximum buffered size of 64 bytes"));
    }
}
