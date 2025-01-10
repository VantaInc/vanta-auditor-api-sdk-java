# Audits
(*audits()*)

## Overview

### Available Operations

* [list](#list) - List audits
* [listEvidenceUrls](#listevidenceurls) - List audit evidence url
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

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `pageSize`                                                                                | *Optional\<Integer>*                                                                      | :heavy_minus_sign:                                                                        | N/A                                                                                       |
| `pageCursor`                                                                              | *Optional\<String>*                                                                       | :heavy_minus_sign:                                                                        | N/A                                                                                       |
| `changedSinceDate`                                                                        | [OffsetDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html) | :heavy_minus_sign:                                                                        | Includes all audits that have changed since changedSinceDate.                             |

### Response

**[ListAuditsResponse](../../models/operations/ListAuditsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listEvidenceUrls

Returns a paginated list of evidence urls for an audit.

### Example Usage

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditEvidenceUrlsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        ListAuditEvidenceUrlsResponse res = sdk.audits().listEvidenceUrls()
                .auditId("<id>")
                .auditEvidenceId("<id>")
                .pageSize(10)
                .pageCursor("<value>")
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
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        ListAuditEvidenceResponse res = sdk.audits().listEvidence()
                .auditId("<id>")
                .pageSize(10)
                .pageCursor("<value>")
                .changedSinceDate(OffsetDateTime.parse("2024-04-16T02:08:40.568Z"))
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
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        ListAuditCommentsResponse res = sdk.audits().listComments()
                .auditId("<id>")
                .pageSize(10)
                .pageCursor("<value>")
                .changedSinceDate(OffsetDateTime.parse("2023-04-04T02:10:35.499Z"))
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
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        ListAuditControlsResponse res = sdk.audits().listControls()
                .auditId("<id>")
                .pageSize(10)
                .pageCursor("<value>")
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
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        CreateCommentForAuditEvidenceResponse res = sdk.audits().createCommentForEvidence()
                .auditId("<id>")
                .auditEvidenceId("<id>")
                .addCommentInput(AddCommentInput.builder()
                    .text("<value>")
                    .email("Earnestine28@yahoo.com")
                    .creationDate(OffsetDateTime.parse("2024-11-28T04:59:12.710Z"))
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
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
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
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        CreateCustomEvidenceRequestResponse res = sdk.audits().createCustomEvidenceRequest()
                .auditId("<id>")
                .createCustomEvidenceRequestInput(CreateCustomEvidenceRequestInput.builder()
                    .controlIds(List.of(
                        "<value>"))
                    .title("<value>")
                    .description("now whoa while")
                    .cadence(RecurrenceDuration.P3_M)
                    .reminderWindow(RecurrenceDuration.P0_D)
                    .isRestricted(true)
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

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        CreateCustomControlResponse res = sdk.audits().createCustomControl()
                .auditId("<id>")
                .createCustomControlInput(CreateCustomControlInput.builder()
                    .externalId("<id>")
                    .name("<value>")
                    .description("even keenly afore mmm yearningly ouch tough yearly intent")
                    .effectiveDate(OffsetDateTime.parse("2024-07-08T11:52:30.590Z"))
                    .category(ControlDomain.THREAT_MANAGEMENT)
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