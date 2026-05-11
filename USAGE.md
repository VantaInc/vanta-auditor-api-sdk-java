<!-- Start SDK Example Usage [usage] -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.AddAuditorInput;
import com.vanta.vanta_auditor_api.models.operations.CreateAuditorResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        AddAuditorInput req = AddAuditorInput.builder()
                .email("Genesis_Kunze87@yahoo.com")
                .givenName("<value>")
                .familyName("<value>")
                .build();

        CreateAuditorResponse res = sdk.auditors().create()
                .request(req)
                .call();

        if (res.auditor().isPresent()) {
            System.out.println(res.auditor().get());
        }
    }
}
```
<!-- End SDK Example Usage [usage] -->