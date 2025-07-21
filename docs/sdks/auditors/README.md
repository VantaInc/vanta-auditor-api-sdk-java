# Auditors
(*auditors()*)

## Overview

### Available Operations

* [create](#create) - Create an auditor

## create

Create an auditor in Vanta.

### Example Usage

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
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                 | Type                                                      | Required                                                  | Description                                               |
| --------------------------------------------------------- | --------------------------------------------------------- | --------------------------------------------------------- | --------------------------------------------------------- |
| `request`                                                 | [AddAuditorInput](../../models/shared/AddAuditorInput.md) | :heavy_check_mark:                                        | The request object to use for the request.                |

### Response

**[CreateAuditorResponse](../../models/operations/CreateAuditorResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |