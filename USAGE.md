<!-- Start SDK Example Usage [usage] -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        ListAuditsResponse res = sdk.audits().list()
                .call();

        if (res.paginatedResponseAudit().isPresent()) {
            // handle response
        }
    }
}
```
<!-- End SDK Example Usage [usage] -->