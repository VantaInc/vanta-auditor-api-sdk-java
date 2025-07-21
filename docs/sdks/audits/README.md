# Audits
(*audits()*)

## Overview

### Available Operations

* [list](#list) - List audits
* [getEvidenceUrls](#getevidenceurls) - List audit evidence url
* [listEvidence](#listevidence) - List audit evidence
* [listComments](#listcomments) - List audit comments
* [listControls](#listcontrols) - List audit controls
* [createCommentForEvidence](#createcommentforevidence) - Create a comment for audit evidence
* [updateEvidence](#updateevidence) - Update audit evidence
* [createCustomEvidenceRequest](#createcustomevidencerequest) - Create a custom evidence request for an audit
* [createCustomControl](#createcustomcontrol) - Create a custom control for an audit

## list

Returns a paginated list of audits scoped to the audit firm.

### Example Usage

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListAuditsResponse res = sdk.audits().list()
                .pageSize(10)
                .call();

        if (res.paginatedResponseAudit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `pageSize`                                                                                | *Optional\<Integer>*                                                                      | :heavy_minus_sign:                                                                        | N/A                                                                                       |
| `pageCursor`                                                                              | *Optional\<String>*                                                                       | :heavy_minus_sign:                                                                        | N/A                                                                                       |
| `changedSinceDate`                                                                        | [OffsetDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html) | :heavy_minus_sign:                                                                        | Includes all audits that have changed since changedSinceDate.                             |
| `isActiveAudit`                                                                           | *Optional\<Boolean>*                                                                      | :heavy_minus_sign:                                                                        | Includes only audits with no audit report uploaded                                        |

### Response

**[ListAuditsResponse](../../models/operations/ListAuditsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## getEvidenceUrls

Returns a paginated list of evidence urls for an audit. This endpoint should be called whenever an
evidence is created or has a statusUpdatedAt field that is more recent than the most recent polling event.

### Example Usage

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditEvidenceUrlsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListAuditEvidenceUrlsResponse res = sdk.audits().getEvidenceUrls()
                .auditId("<id>")
                .auditEvidenceId("<id>")
                .pageSize(10)
                .call();

        if (res.paginatedResponseEvidenceUrl().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter            | Type                 | Required             | Description          |
| -------------------- | -------------------- | -------------------- | -------------------- |
| `auditId`            | *String*             | :heavy_check_mark:   | N/A                  |
| `auditEvidenceId`    | *String*             | :heavy_check_mark:   | N/A                  |
| `pageSize`           | *Optional\<Integer>* | :heavy_minus_sign:   | N/A                  |
| `pageCursor`         | *Optional\<String>*  | :heavy_minus_sign:   | N/A                  |

### Response

**[ListAuditEvidenceUrlsResponse](../../models/operations/ListAuditEvidenceUrlsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listEvidence

Returns a paginated list of evidence for an audit.

### Example Usage

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditEvidenceResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListAuditEvidenceResponse res = sdk.audits().listEvidence()
                .auditId("<id>")
                .pageSize(10)
                .call();

        if (res.paginatedResponseEvidence().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `auditId`                                                                                 | *String*                                                                                  | :heavy_check_mark:                                                                        | N/A                                                                                       |
| `pageSize`                                                                                | *Optional\<Integer>*                                                                      | :heavy_minus_sign:                                                                        | N/A                                                                                       |
| `pageCursor`                                                                              | *Optional\<String>*                                                                       | :heavy_minus_sign:                                                                        | N/A                                                                                       |
| `changedSinceDate`                                                                        | [OffsetDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html) | :heavy_minus_sign:                                                                        | Includes all audit evidence that have changed since changedSinceDate.                     |

### Response

**[ListAuditEvidenceResponse](../../models/operations/ListAuditEvidenceResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listComments

Returns a paginated list of comments for an audit.

### Example Usage

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditCommentsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListAuditCommentsResponse res = sdk.audits().listComments()
                .auditId("<id>")
                .pageSize(10)
                .call();

        if (res.paginatedResponseComment().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `auditId`                                                                                 | *String*                                                                                  | :heavy_check_mark:                                                                        | N/A                                                                                       |
| `pageSize`                                                                                | *Optional\<Integer>*                                                                      | :heavy_minus_sign:                                                                        | N/A                                                                                       |
| `pageCursor`                                                                              | *Optional\<String>*                                                                       | :heavy_minus_sign:                                                                        | N/A                                                                                       |
| `changedSinceDate`                                                                        | [OffsetDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html) | :heavy_minus_sign:                                                                        | Includes all comments that have changed since changedSinceDate.                           |

### Response

**[ListAuditCommentsResponse](../../models/operations/ListAuditCommentsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listControls

Returns a paginated list of controls for an audit.

### Example Usage

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditControlsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListAuditControlsResponse res = sdk.audits().listControls()
                .auditId("<id>")
                .pageSize(10)
                .call();

        if (res.paginatedResponseAuditorControl().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter            | Type                 | Required             | Description          |
| -------------------- | -------------------- | -------------------- | -------------------- |
| `auditId`            | *String*             | :heavy_check_mark:   | N/A                  |
| `pageSize`           | *Optional\<Integer>* | :heavy_minus_sign:   | N/A                  |
| `pageCursor`         | *Optional\<String>*  | :heavy_minus_sign:   | N/A                  |

### Response

**[ListAuditControlsResponse](../../models/operations/ListAuditControlsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## createCommentForEvidence

Create a comment in Vanta for a piece of evidence.

### Example Usage

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.AddCommentInput;
import com.vanta.vanta_auditor_api.models.operations.CreateCommentForAuditEvidenceResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        CreateCommentForAuditEvidenceResponse res = sdk.audits().createCommentForEvidence()
                .auditId("<id>")
                .auditEvidenceId("<id>")
                .addCommentInput(AddCommentInput.builder()
                    .text("<value>")
                    .email("Carmen.Bogan@yahoo.com")
                    .creationDate(OffsetDateTime.parse("2024-05-28T11:04:29.369Z"))
                    .build())
                .call();

        if (res.comment().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                     | Type                                                          | Required                                                      | Description                                                   |
| ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- |
| `auditId`                                                     | *String*                                                      | :heavy_check_mark:                                            | N/A                                                           |
| `auditEvidenceId`                                             | *String*                                                      | :heavy_check_mark:                                            | N/A                                                           |
| `addCommentInput`                                             | [AddCommentInput](../../models/components/AddCommentInput.md) | :heavy_check_mark:                                            | N/A                                                           |

### Response

**[CreateCommentForAuditEvidenceResponse](../../models/operations/CreateCommentForAuditEvidenceResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## updateEvidence

Update audit evidence.

### Example Usage

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.AuditEvidenceUpdateInput;
import com.vanta.vanta_auditor_api.models.operations.UpdateAuditEvidenceResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        UpdateAuditEvidenceResponse res = sdk.audits().updateEvidence()
                .auditId("<id>")
                .auditEvidenceId("<id>")
                .auditEvidenceUpdateInput(AuditEvidenceUpdateInput.builder()
                    .build())
                .call();

        if (res.evidence().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                       | Type                                                                            | Required                                                                        | Description                                                                     |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| `auditId`                                                                       | *String*                                                                        | :heavy_check_mark:                                                              | N/A                                                                             |
| `auditEvidenceId`                                                               | *String*                                                                        | :heavy_check_mark:                                                              | N/A                                                                             |
| `auditEvidenceUpdateInput`                                                      | [AuditEvidenceUpdateInput](../../models/components/AuditEvidenceUpdateInput.md) | :heavy_check_mark:                                                              | N/A                                                                             |

### Response

**[UpdateAuditEvidenceResponse](../../models/operations/UpdateAuditEvidenceResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## createCustomEvidenceRequest

Create a custom evidence request for an audit.

### Example Usage

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.CreateCustomEvidenceRequestInput;
import com.vanta.vanta_auditor_api.models.components.RecurrenceDuration;
import com.vanta.vanta_auditor_api.models.operations.CreateCustomEvidenceRequestResponse;
import java.lang.Exception;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        CreateCustomEvidenceRequestResponse res = sdk.audits().createCustomEvidenceRequest()
                .auditId("<id>")
                .createCustomEvidenceRequestInput(CreateCustomEvidenceRequestInput.builder()
                    .controlIds(List.of(
                        "<value 1>",
                        "<value 2>"))
                    .title("<value>")
                    .description("pure bludgeon deliberately question although")
                    .cadence(RecurrenceDuration.P1_D)
                    .reminderWindow(RecurrenceDuration.P0_D)
                    .isRestricted(false)
                    .auditorEmail("<value>")
                    .build())
                .call();

        if (res.customEvidenceRequest().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                       | Type                                                                                            | Required                                                                                        | Description                                                                                     |
| ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- |
| `auditId`                                                                                       | *String*                                                                                        | :heavy_check_mark:                                                                              | N/A                                                                                             |
| `createCustomEvidenceRequestInput`                                                              | [CreateCustomEvidenceRequestInput](../../models/components/CreateCustomEvidenceRequestInput.md) | :heavy_check_mark:                                                                              | N/A                                                                                             |

### Response

**[CreateCustomEvidenceRequestResponse](../../models/operations/CreateCustomEvidenceRequestResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## createCustomControl

Create a custom control for an audit.

### Example Usage

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.ControlDomain;
import com.vanta.vanta_auditor_api.models.components.CreateCustomControlInput;
import com.vanta.vanta_auditor_api.models.operations.CreateCustomControlResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;
import java.util.Optional;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        CreateCustomControlResponse res = sdk.audits().createCustomControl()
                .auditId("<id>")
                .createCustomControlInput(CreateCustomControlInput.builder()
                    .externalId("<id>")
                    .name(Optional.empty())
                    .description("yet plus utter queasily what juvenile wound")
                    .effectiveDate(OffsetDateTime.parse("2024-11-10T13:58:54.564Z"))
                    .category(ControlDomain.CLOUD_SECURITY)
                    .build())
                .call();

        if (res.control().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                       | Type                                                                            | Required                                                                        | Description                                                                     |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| `auditId`                                                                       | *String*                                                                        | :heavy_check_mark:                                                              | N/A                                                                             |
| `createCustomControlInput`                                                      | [CreateCustomControlInput](../../models/components/CreateCustomControlInput.md) | :heavy_check_mark:                                                              | N/A                                                                             |

### Response

**[CreateCustomControlResponse](../../models/operations/CreateCustomControlResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |