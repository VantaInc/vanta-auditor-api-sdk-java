package com.vanta.smoke;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.errors.APIException;
import com.vanta.vanta_auditor_api.models.errors.AsyncAPIException;
import com.vanta.vanta_auditor_api.models.errors.VantaError;
import com.vanta.vanta_auditor_api.utils.SpeakeasyHTTPClient;
import com.vanta.vanta_auditor_api.utils.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ResponseBodyBufferingTest extends LoopbackTest {
    @ParameterizedTest
    @CsvSource({"200,false", "400,false", "302,false", "200,true", "400,true", "302,true"})
    void rejectsOversizedContentLengthWithoutReadingTheBody(int status, boolean async) {
        handler = exchange -> {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(status, (long) Utils.MAX_BUFFERED_BODY_BYTES + 1);
            awaitRelease();
            exchange.close();
        };
        Vanta sdk = sdk(new SpeakeasyHTTPClient());

        VantaError error;
        if (async) {
            ExecutionException failure = assertThrows(ExecutionException.class,
                    () -> sdk.async().audits().getInformationRequest("audit", "request").get(5, TimeUnit.SECONDS));
            error = assertInstanceOf(AsyncAPIException.class, failure.getCause());
        } else {
            error = assertThrows(APIException.class,
                    () -> sdk.audits().getInformationRequest("audit", "request"));
        }

        assertEquals(status, error.code());
        assertEquals(status, error.rawResponse().statusCode());
        assertTrue(error.body().isEmpty());
        IOException cause = assertInstanceOf(IOException.class, error.getCause());
        assertTrue(cause.getMessage().contains("maximum buffered size of " + Utils.MAX_BUFFERED_BODY_BYTES));
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void preservesReadableErrorBodies(boolean async) {
        respond(418, "rejected", false);
        Vanta sdk = sdk(new SpeakeasyHTTPClient());

        VantaError error;
        if (async) {
            ExecutionException failure = assertThrows(ExecutionException.class,
                    () -> sdk.async().audits().getInformationRequest("audit", "request").get(5, TimeUnit.SECONDS));
            error = assertInstanceOf(AsyncAPIException.class, failure.getCause());
        } else {
            error = assertThrows(APIException.class,
                    () -> sdk.audits().getInformationRequest("audit", "request"));
        }

        assertEquals(418, error.code());
        assertArrayEquals("rejected".getBytes(StandardCharsets.UTF_8), error.body().orElseThrow());
        assertNull(error.getCause());
    }

    @ParameterizedTest
    @CsvSource({"application/json,8,true", "text/plain,8,true", "application/json,0,false", "application/json,65537,false",
            "application/octet-stream,8,false", "text/event-stream,8,false"})
    void debugLoggingOnlyReadsSmallKnownText(String contentType, int length, boolean logged) throws Exception {
        byte[] expected = "12345678".getBytes(StandardCharsets.UTF_8);
        handler = exchange -> {
            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.sendResponseHeaders(200, length);
            if (logged) {
                try (var body = exchange.getResponseBody()) {
                    body.write(expected);
                }
            } else {
                awaitRelease();
                exchange.close();
            }
        };
        List<String> logs = new ArrayList<>();
        SpeakeasyHTTPClient.setLogger(logs::add);
        SpeakeasyHTTPClient client = new SpeakeasyHTTPClient();
        client.enableDebugLogging(true);

        try (InputStream body = client.send(request()).body()) {
            if (logged) {
                assertArrayEquals(expected, body.readAllBytes());
            }
        }

        assertEquals(logged, logs.stream().anyMatch(message -> message.equals("Response body:\n12345678")));
    }
}
