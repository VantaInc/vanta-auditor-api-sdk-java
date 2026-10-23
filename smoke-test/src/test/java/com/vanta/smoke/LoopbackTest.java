package com.vanta.smoke;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.utils.SpeakeasyHTTPClient;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;

@Timeout(15)
abstract class LoopbackTest {
    private HttpServer server;
    private ExecutorService executor;
    volatile HttpHandler handler;
    final CountDownLatch release = new CountDownLatch(1);

    @BeforeEach
    void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        executor = Executors.newCachedThreadPool();
        server.setExecutor(executor);
        server.createContext("/", exchange -> handler.handle(exchange));
        server.start();
    }

    @AfterEach
    void stopServer() {
        release.countDown();
        server.stop(0);
        executor.shutdownNow();
        SpeakeasyHTTPClient.setLogger(System.out::println);
    }

    void respond(int status, String body, boolean chunked) {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        handler = exchange -> {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(status, chunked ? 0 : bytes.length);
            try (OutputStream response = exchange.getResponseBody()) {
                response.write(bytes);
            }
        };
    }

    Vanta sdk(SpeakeasyHTTPClient client) {
        return Vanta.builder().serverURL(request().uri().toString()).bearerAuth("test").client(client).build();
    }

    HttpRequest request() {
        return HttpRequest.newBuilder(URI.create("http://127.0.0.1:" + server.getAddress().getPort())).build();
    }

    void awaitRelease() {
        try {
            release.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
        }
    }
}
