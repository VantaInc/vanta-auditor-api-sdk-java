<!-- Start SDK Example Usage [usage] -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditsResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        ListAuditsResponse res = sdk.audits().list()
                .pageSize(10)
                .pageCursor("<value>")
                .changedSinceDate(OffsetDateTime.parse("2025-04-22T08:39:55.981Z"))
                .call();

        if (res.paginatedResponseAudit().isPresent()) {
            // handle response
        }
    }
}
```
<!-- End SDK Example Usage [usage] -->