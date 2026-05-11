package com.vanta.smoke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.Audit;
import com.vanta.vanta_auditor_api.models.components.Evidence;
import com.vanta.vanta_auditor_api.models.operations.ListAuditCommentsResponse;
import com.vanta.vanta_auditor_api.models.operations.ListAuditControlsResponse;
import com.vanta.vanta_auditor_api.models.operations.ListAuditEvidenceResponse;
import com.vanta.vanta_auditor_api.models.operations.ListAuditEvidenceUrlsResponse;
import com.vanta.vanta_auditor_api.models.operations.ListAuditsResponse;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Live smoke test against a real Vanta API tenant. Run by ./gradlew :smoke-test:test
 * when the four SMOKE_* environment variables are populated; otherwise every test
 * is reported as skipped. See SMOKE_TEST.md for fixture details and CI wiring.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SmokeTest {

    private static String auditId;
    private static Vanta sdk;

    @BeforeAll
    static void setup() throws Exception {
        String clientId     = require("SMOKE_OAUTH_CLIENT_ID");
        String clientSecret = require("SMOKE_OAUTH_CLIENT_SECRET");
        auditId             = require("SMOKE_AUDIT_ID");
        String apiBaseUrl   = require("SMOKE_API_BASE_URL");

        String accessToken = fetchAccessToken(apiBaseUrl, clientId, clientSecret);

        sdk = Vanta.builder()
            .bearerAuth(accessToken)
            .serverIndex(0)
            // Intentionally do NOT call .enableDebugLogging(true) — it writes request
            // and response detail to stdout and would surface in CI logs.
            .build();
    }

    private static String require(String key) {
        String val = System.getProperty(key, "");
        assumeFalse(val == null || val.isBlank(),
            "smoke test skipped — " + key + " is not set (CI populates via SMOKE_* env vars)");
        return val;
    }

    private static String fetchAccessToken(String baseUrl, String clientId, String clientSecret)
            throws Exception {
        String form = "grant_type=client_credentials"
            + "&client_id="     + URLEncoder.encode(clientId,     StandardCharsets.UTF_8)
            + "&client_secret=" + URLEncoder.encode(clientSecret, StandardCharsets.UTF_8);

        HttpRequest req = HttpRequest.newBuilder()
            .uri(URI.create(baseUrl + "/oauth/token"))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("Accept",       "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(form))
            .build();

        HttpResponse<String> resp = HttpClient.newHttpClient()
            .send(req, HttpResponse.BodyHandlers.ofString());

        if (resp.statusCode() != 200) {
            // OAuth 2.0 error responses (RFC 6749 §5.2) contain {error, error_description}
            // and never include the access_token or client_secret, so they're safe to log.
            String detail = resp.body();
            try {
                JsonNode err = new ObjectMapper().readTree(resp.body());
                if (err.has("error") || err.has("error_description")) {
                    detail = "error=" + err.path("error").asText("?")
                        + " error_description=" + err.path("error_description").asText("?");
                }
            } catch (Exception ignored) {
                // body wasn't JSON — fall back to the raw string
            }
            throw new AssertionError("OAuth token endpoint " + baseUrl
                + "/oauth/token returned HTTP " + resp.statusCode() + " — " + detail);
        }

        JsonNode body = new ObjectMapper().readTree(resp.body());
        JsonNode tok = body.get("access_token");
        if (tok == null || tok.isNull() || tok.asText().isBlank()) {
            throw new AssertionError("OAuth response missing access_token field");
        }
        return tok.asText();
    }

    /**
     * Catches the case where the Speakeasy overlay regression drops the Commercial
     * host from the generated SERVERS array. Deliberately permissive while the
     * EU/AUS phantom entries are still in the spec — tighten to exact-match
     * [Commercial, Gov] after PR #19 lands and the new Mintlify-sourced regen
     * produces the post-cleanup server list.
     */
    @Test
    @Order(1)
    void serversArrayContainsCommercial() {
        String[] servers = Vanta.SERVERS;
        assertNotNull(servers, "Vanta.SERVERS should not be null");
        assertTrue(servers.length >= 1, "Vanta.SERVERS should have at least one entry");
        assertTrue(servers[0].startsWith("https://api.vanta.com"),
            "SERVERS[0] should be the Commercial host, was: " + servers[0]);
    }

    @Test
    @Order(2)
    void listAudits_findsFixtureAudit() throws Exception {
        ListAuditsResponse res = sdk.audits().listDirect();
        assertEquals(200, res.statusCode());
        assertTrue(res.paginatedResponseAudit().isPresent(), "expected paginated response body");

        List<Audit> audits = res.paginatedResponseAudit().get().results().data();
        assertNotNull(audits, "audits list should not be null");
        assertTrue(
            audits.stream().anyMatch(a -> auditId.equals(a.id())),
            "fixture audit " + auditId + " not found in /audits results — "
                + "was it deleted? See SMOKE_TEST.md for recovery steps."
        );
    }

    @Test
    @Order(3)
    void listEvidence_andDrillIntoEvidenceUrls() throws Exception {
        ListAuditEvidenceResponse listRes = sdk.audits().listEvidence(auditId);
        assertEquals(200, listRes.statusCode());
        assertTrue(listRes.paginatedResponseEvidence().isPresent(), "expected paginated response body");

        List<Evidence> evidence = listRes.paginatedResponseEvidence().get().results().data();
        assertNotNull(evidence, "evidence list should not be null");

        assumeFalse(evidence.isEmpty(),
            "fixture audit has no evidence — skipping getEvidenceUrls drill-down "
                + "(see SMOKE_TEST.md for seeding guidance)");

        String evidenceId = evidence.get(0).id();
        ListAuditEvidenceUrlsResponse urlsRes = sdk.audits().getEvidenceUrls(auditId, evidenceId);
        assertEquals(200, urlsRes.statusCode());
        assertTrue(urlsRes.paginatedResponseEvidenceUrl().isPresent(),
            "expected paginated evidence-urls body");
    }

    @Test
    @Order(4)
    void listComments() throws Exception {
        ListAuditCommentsResponse res = sdk.audits().listComments(auditId);
        assertEquals(200, res.statusCode());
        assertTrue(res.paginatedResponseComment().isPresent(), "expected paginated response body");
        assertNotNull(res.paginatedResponseComment().get().results().data(),
            "comments list should not be null (empty list is fine)");
    }

    @Test
    @Order(5)
    void listControls() throws Exception {
        ListAuditControlsResponse res = sdk.audits().listControls(auditId);
        assertEquals(200, res.statusCode());
        assertTrue(res.paginatedResponseAuditorControl().isPresent(), "expected paginated response body");
        assertNotNull(res.paginatedResponseAuditorControl().get().results().data(),
            "controls list should not be null (empty list is fine)");
    }
}
