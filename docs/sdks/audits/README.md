# Audits

## Overview

### Available Operations

* [list](#list) - List audits
* [getAudit](#getaudit) - Get audit by ID
* [listCodeChanges](#listcodechanges) - List code changes for an audit
* [listComments](#listcomments) - List audit comments
* [listControls](#listcontrols) - List audit controls
* [createCustomControl](#createcustomcontrol) - Create a custom control for an audit
* [listCommentsForControl](#listcommentsforcontrol) - List comments for a control within an audit
* [createCommentForControl](#createcommentforcontrol) - Create a comment for a control within an audit
* [updateCommentForControl](#updatecommentforcontrol) - Update a comment for a control within an audit
* [deleteCommentForControl](#deletecommentforcontrol) - Delete a comment for a control within an audit
* [listInformationRequestsForControl](#listinformationrequestsforcontrol) - List information requests linked to a control within an audit
* [listEvidence](#listevidence) - List audit evidence
* [createCustomEvidenceRequest](#createcustomevidencerequest) - Create a custom evidence request for an audit
* [updateEvidence](#updateevidence) - Update audit evidence
* [createCommentForEvidence](#createcommentforevidence) - Create a comment for audit evidence
* [getEvidenceUrls](#getevidenceurls) - List audit evidence url
* [getFrameworkCodes](#getframeworkcodes) - Get framework codes for an audit
* [listInformationRequests](#listinformationrequests) - List information requests for an audit
* [createInformationRequest](#createinformationrequest) - Create a new information request
* [getInformationRequest](#getinformationrequest) - Get an information request by ID
* [updateInformationRequest](#updateinformationrequest) - Update an information request for an audit
* [deleteInformationRequest](#deleteinformationrequest) - Delete an information request for an audit
* [acceptInformationRequestEvidence](#acceptinformationrequestevidence) - Accept evidence for an information request
* [listInformationRequestActivity](#listinformationrequestactivity) - List information request activity
* [listCommentsForInformationRequest](#listcommentsforinformationrequest) - List comments for an information request
* [createCommentForInformationRequest](#createcommentforinformationrequest) - Create a comment for an information request
* [updateCommentForInformationRequest](#updatecommentforinformationrequest) - Update a comment for an information request
* [deleteCommentForInformationRequest](#deletecommentforinformationrequest) - Delete a comment for an information request
* [listInformationRequestEvidence](#listinformationrequestevidence) - List evidence for an information request
* [getInformationRequestTestSnapshotEvidenceDetail](#getinformationrequesttestsnapshotevidencedetail) - Get test snapshot detail for an evidence row
* [flagInformationRequestEvidence](#flaginformationrequestevidence) - Flag evidence for an information request
* [listIntegrations](#listintegrations) - List integrations for an audit
* [listAuditIssues](#listauditissues) - List snapshotted issues for an audit
* [listAuditSnapshots](#listauditsnapshots) - List snapshotted issues for an audit
* [listVendors](#listvendors) - List vendors for an audit
* [~~listMonitoredComputersInAuditScope~~](#listmonitoredcomputersinauditscope) - List monitored computers :warning: **Deprecated**
* [getOrganizationInformation](#getorganizationinformation) - Get organization information for an audit
* [getOrganizationNotifications](#getorganizationnotifications) - Get organization notification settings for an audit
* [~~listPeopleInAuditScope~~](#listpeopleinauditscope) - List of people who are in scope for this audit :warning: **Deprecated**
* [listAccountAccessServices](#listaccountaccessservices) - List account access services for an audit
* [listPersonnelAccountAccess](#listpersonnelaccountaccess) - List account access records for an audit
* [listPersonnelGroups](#listpersonnelgroups) - List groups for an audit
* [listPersonnelPeople](#listpersonnelpeople) - List people for an audit
* [listRiskSnapshots](#listrisksnapshots) - List risk snapshots for an audit
* [listAuditRisks](#listauditrisks) - List risks for an audit
* [shareInformationRequestList](#shareinformationrequestlist) - Share information request list with customer
* [~~listVendorsInAuditScope~~](#listvendorsinauditscope) - List of vendors who are in scope for this audit :warning: **Deprecated**
* [~~listVulnerabilities~~](#listvulnerabilities) - List vulnerabilities within the scope of a given audit :warning: **Deprecated**
* [~~listVulnerabilityRemediationsInAuditScope~~](#listvulnerabilityremediationsinauditscope) - List vulnerability remediations that are in scope for this audit :warning: **Deprecated**
* [~~getVulnerableAssets~~](#getvulnerableassets) - List assets associated with vulnerabilities :warning: **Deprecated**

## list

Returns a paginated list of audits scoped to the audit firm.

To identify IRL (Information Request List) audits, check for the presence of the
`auditorRequestListMetadata` field. This field is only present for IRL-based audits
and will be `undefined` for standard audits.

Rate limit: 250 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListAudits" method="get" path="/audits" example="Example 1" -->
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
            System.out.println(res.paginatedResponseAudit().get());
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

## getAudit

Returns a single audit by ID, scoped to the audit firm.

To identify IRL (Information Request List) audits, check for the presence of the
`auditorRequestListMetadata` field. This field is only present for IRL-based audits
and will be `undefined` for standard audits.

Rate limit: 250 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="GetAudit" method="get" path="/audits/{auditId}" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.GetAuditResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        GetAuditResponse res = sdk.audits().getAudit()
                .auditId("<id>")
                .call();

        if (res.audit().isPresent()) {
            System.out.println(res.audit().get());
        }
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        |
| ------------------ | ------------------ | ------------------ | ------------------ |
| `auditId`          | *String*           | :heavy_check_mark: | N/A                |

### Response

**[GetAuditResponse](../../models/operations/GetAuditResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listCodeChanges

Retrieves code changes population data for an audit.

This endpoint provides access to code change records (pull requests)
visible to auditors during an audit engagement.

Supports filtering by:
- `search`: Searches code change titles and repository names (case-insensitive)
- `sourcesMatchesAny`: Filters by version control source (accepted values: github, gitlab, bitbucket, azuredevops)
- `closedAfterDate` / `closedBeforeDate`: Filters by the closed date range

Uses cursor-based pagination. To paginate:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage`
3. Use `results.pageInfo.endCursor` as `pageCursor` for next request

Results are sorted by closed date (newest first). This sort order is
fixed and cannot be customized via query parameters.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListCodeChanges" method="get" path="/audits/{auditId}/assets/code-changes" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListCodeChangesRequest;
import com.vanta.vanta_auditor_api.models.operations.ListCodeChangesResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListCodeChangesRequest req = ListCodeChangesRequest.builder()
                .auditId("<id>")
                .build();

        ListCodeChangesResponse res = sdk.audits().listCodeChanges()
                .request(req)
                .call();

        if (res.paginatedResponseCodeChange().isPresent()) {
            System.out.println(res.paginatedResponseCodeChange().get());
        }
    }
}
```

### Parameters

| Parameter                                                                   | Type                                                                        | Required                                                                    | Description                                                                 |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `request`                                                                   | [ListCodeChangesRequest](../../models/operations/ListCodeChangesRequest.md) | :heavy_check_mark:                                                          | The request object to use for the request.                                  |

### Response

**[ListCodeChangesResponse](../../models/operations/ListCodeChangesResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listComments

Returns a paginated list of comments for an audit.

Rate limit: 250 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListAuditComments" method="get" path="/audits/{auditId}/comments" example="Example 1" -->
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
            System.out.println(res.paginatedResponseComment().get());
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

Rate limit: 250 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListAuditControls" method="get" path="/audits/{auditId}/controls" example="Example 1" -->
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
            System.out.println(res.paginatedResponseAuditorControl().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                          | Type                                                                                               | Required                                                                                           | Description                                                                                        |
| -------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------- |
| `auditId`                                                                                          | *String*                                                                                           | :heavy_check_mark:                                                                                 | N/A                                                                                                |
| `pageSize`                                                                                         | *Optional\<Integer>*                                                                               | :heavy_minus_sign:                                                                                 | N/A                                                                                                |
| `pageCursor`                                                                                       | *Optional\<String>*                                                                                | :heavy_minus_sign:                                                                                 | N/A                                                                                                |
| `externalIdMatchesAny`                                                                             | List\<*String*>                                                                                    | :heavy_minus_sign:                                                                                 | Filter controls whose externalId matches any of the provided values (exact, case-sensitive match). |

### Response

**[ListAuditControlsResponse](../../models/operations/ListAuditControlsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## createCustomControl

Create a custom control for an audit.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="CreateCustomControl" method="post" path="/audits/{auditId}/controls/custom-controls" example="Example 1" -->
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
            System.out.println(res.control().get());
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

## listCommentsForControl

Retrieves a paginated list of comments on a control within an IRL audit,
enabling auditors to view collaboration history on the control.

This endpoint always includes soft-deleted records (where `deletionDate !== null`).
Clients should check the `deletionDate` field to identify and handle deleted records
appropriately in their systems.

This endpoint supports delta synchronization via the `changedSinceDate` parameter,
allowing efficient polling for changes without retrieving the entire dataset.

Returns 404 when the control is not part of the audit.

Pagination usage:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage` to see if more data exists
3. If true, use `results.pageInfo.endCursor` as `pageCursor` in next request
4. Repeat until `hasNextPage` is false

Delta sync usage:
1. Store the timestamp of your last sync
2. Pass that timestamp as `changedSinceDate`
3. Only comments created, modified, or deleted since that timestamp are returned
4. Process updates, including soft-deletes (deletionDate !== null)
5. Update your last sync timestamp to the current time

Rate limit: 50 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListCommentsForControl" method="get" path="/audits/{auditId}/controls/{controlId}/comments" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListCommentsForControlRequest;
import com.vanta.vanta_auditor_api.models.operations.ListCommentsForControlResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListCommentsForControlRequest req = ListCommentsForControlRequest.builder()
                .auditId("<id>")
                .controlId("<id>")
                .build();

        ListCommentsForControlResponse res = sdk.audits().listCommentsForControl()
                .request(req)
                .call();

        if (res.paginatedResponseAuditControlComment().isPresent()) {
            System.out.println(res.paginatedResponseAuditControlComment().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `request`                                                                                 | [ListCommentsForControlRequest](../../models/operations/ListCommentsForControlRequest.md) | :heavy_check_mark:                                                                        | The request object to use for the request.                                                |

### Response

**[ListCommentsForControlResponse](../../models/operations/ListCommentsForControlResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## createCommentForControl

Creates a new comment on a control within an IRL audit. The comment author
must be an auditor in the audit firm making the request. The comment will be
associated with the control and visible to all authorized users.

Returns 404 when the control is not part of the audit.

Rate limit: 50 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="CreateCommentForControl" method="post" path="/audits/{auditId}/controls/{controlId}/comments" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.AddAuditControlCommentInput;
import com.vanta.vanta_auditor_api.models.operations.CreateCommentForControlResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        CreateCommentForControlResponse res = sdk.audits().createCommentForControl()
                .auditId("<id>")
                .controlId("<id>")
                .addAuditControlCommentInput(AddAuditControlCommentInput.builder()
                    .text("<value>")
                    .email("Justice.Konopelski@hotmail.com")
                    .creationDate(OffsetDateTime.parse("2024-04-23T18:18:35.232Z"))
                    .build())
                .call();

        if (res.auditControlComment().isPresent()) {
            System.out.println(res.auditControlComment().get());
        }
    }
}
```

### Parameters

| Parameter                                                                             | Type                                                                                  | Required                                                                              | Description                                                                           |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `auditId`                                                                             | *String*                                                                              | :heavy_check_mark:                                                                    | N/A                                                                                   |
| `controlId`                                                                           | *String*                                                                              | :heavy_check_mark:                                                                    | N/A                                                                                   |
| `addAuditControlCommentInput`                                                         | [AddAuditControlCommentInput](../../models/components/AddAuditControlCommentInput.md) | :heavy_check_mark:                                                                    | N/A                                                                                   |

### Response

**[CreateCommentForControlResponse](../../models/operations/CreateCommentForControlResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## updateCommentForControl

Updates an existing comment on a control. Only the original author
of the comment can update it. The author is identified by their email address,
which must match the email of the user who created the comment.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="UpdateCommentForControl" method="patch" path="/audits/{auditId}/controls/{controlId}/comments/{commentId}" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.UpdateAuditControlCommentInput;
import com.vanta.vanta_auditor_api.models.operations.UpdateCommentForControlResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        UpdateCommentForControlResponse res = sdk.audits().updateCommentForControl()
                .auditId("<id>")
                .controlId("<id>")
                .commentId("<id>")
                .updateAuditControlCommentInput(UpdateAuditControlCommentInput.builder()
                    .text("<value>")
                    .email("Leonardo71@hotmail.com")
                    .build())
                .call();

        if (res.auditControlComment().isPresent()) {
            System.out.println(res.auditControlComment().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                   | Type                                                                                        | Required                                                                                    | Description                                                                                 |
| ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| `auditId`                                                                                   | *String*                                                                                    | :heavy_check_mark:                                                                          | N/A                                                                                         |
| `controlId`                                                                                 | *String*                                                                                    | :heavy_check_mark:                                                                          | N/A                                                                                         |
| `commentId`                                                                                 | *String*                                                                                    | :heavy_check_mark:                                                                          | N/A                                                                                         |
| `updateAuditControlCommentInput`                                                            | [UpdateAuditControlCommentInput](../../models/components/UpdateAuditControlCommentInput.md) | :heavy_check_mark:                                                                          | N/A                                                                                         |

### Response

**[UpdateCommentForControlResponse](../../models/operations/UpdateCommentForControlResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## deleteCommentForControl

Deletes an existing comment on a control. Only the original author
of the comment can delete it. The author is identified by their email address,
which must match the email of the user who created the comment.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="DeleteCommentForControl" method="delete" path="/audits/{auditId}/controls/{controlId}/comments/{commentId}" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.DeleteAuditControlCommentInput;
import com.vanta.vanta_auditor_api.models.operations.DeleteCommentForControlResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        DeleteCommentForControlResponse res = sdk.audits().deleteCommentForControl()
                .auditId("<id>")
                .controlId("<id>")
                .commentId("<id>")
                .deleteAuditControlCommentInput(DeleteAuditControlCommentInput.builder()
                    .email("Lorenzo.Ondricka@yahoo.com")
                    .build())
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                                                                                   | Type                                                                                        | Required                                                                                    | Description                                                                                 |
| ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| `auditId`                                                                                   | *String*                                                                                    | :heavy_check_mark:                                                                          | N/A                                                                                         |
| `controlId`                                                                                 | *String*                                                                                    | :heavy_check_mark:                                                                          | N/A                                                                                         |
| `commentId`                                                                                 | *String*                                                                                    | :heavy_check_mark:                                                                          | N/A                                                                                         |
| `deleteAuditControlCommentInput`                                                            | [DeleteAuditControlCommentInput](../../models/components/DeleteAuditControlCommentInput.md) | :heavy_check_mark:                                                                          | N/A                                                                                         |

### Response

**[DeleteCommentForControlResponse](../../models/operations/DeleteCommentForControlResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listInformationRequestsForControl

Returns a paginated list of active information requests linked to a specific
control within an IRL audit. An information request is linked to a control
either via its framework codes (`criteriaIds`) or via a direct association
(`additionalControlIds`).

Soft-deleted information requests are not included in the response. To
synchronize deletions, use `GET /audits/{auditId}/information-requests`,
which supports `changedSinceDate` and includes soft-deleted records.

Returns 404 when the control is not part of the audit. Returns an empty page
when the control is part of the audit but has no active IRLs linked to it.

Pagination usage:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage` to see if more data exists
3. If true, use `results.pageInfo.endCursor` as `pageCursor` in next request
4. Repeat until `hasNextPage` is false

Rate limit: 50 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListInformationRequestsForControl" method="get" path="/audits/{auditId}/controls/{controlId}/information-requests" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListInformationRequestsForControlResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListInformationRequestsForControlResponse res = sdk.audits().listInformationRequestsForControl()
                .auditId("<id>")
                .controlId("<id>")
                .pageSize(10)
                .call();

        if (res.paginatedResponseInformationRequest().isPresent()) {
            System.out.println(res.paginatedResponseInformationRequest().get());
        }
    }
}
```

### Parameters

| Parameter                                                                              | Type                                                                                   | Required                                                                               | Description                                                                            |
| -------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------- |
| `auditId`                                                                              | *String*                                                                               | :heavy_check_mark:                                                                     | N/A                                                                                    |
| `controlId`                                                                            | *String*                                                                               | :heavy_check_mark:                                                                     | N/A                                                                                    |
| `pageSize`                                                                             | *Optional\<Integer>*                                                                   | :heavy_minus_sign:                                                                     | Maximum number of information requests to return per page.                             |
| `pageCursor`                                                                           | *Optional\<String>*                                                                    | :heavy_minus_sign:                                                                     | Pagination cursor from a previous response. Provide to fetch the next page of results. |

### Response

**[ListInformationRequestsForControlResponse](../../models/operations/ListInformationRequestsForControlResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listEvidence

Returns a paginated list of evidence for an audit.

Rate limit: 250 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListAuditEvidence" method="get" path="/audits/{auditId}/evidence" example="Example 1" -->
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
            System.out.println(res.paginatedResponseEvidence().get());
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

## createCustomEvidenceRequest

Create a custom evidence request for an audit.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="CreateCustomEvidenceRequest" method="post" path="/audits/{auditId}/evidence/custom-evidence-requests" example="Example 1" -->
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
            System.out.println(res.customEvidenceRequest().get());
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

## updateEvidence

Update audit evidence.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="UpdateAuditEvidence" method="patch" path="/audits/{auditId}/evidence/{auditEvidenceId}" example="Example 1" -->
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
            System.out.println(res.evidence().get());
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

## createCommentForEvidence

Create a comment in Vanta for a piece of evidence.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="CreateCommentForAuditEvidence" method="post" path="/audits/{auditId}/evidence/{auditEvidenceId}/comments" example="Example 1" -->
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
            System.out.println(res.comment().get());
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

## getEvidenceUrls

Returns a paginated list of evidence urls for an audit. This endpoint should be called whenever an
evidence is created or has a statusUpdatedAt field that is more recent than the most recent polling event.

Evidence must be in one of the following states to retrieve URLs: "Ready for audit", "Accepted", "Flagged", or "NA".

Rate limit: 600 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListAuditEvidenceUrls" method="get" path="/audits/{auditId}/evidence/{auditEvidenceId}/urls" example="Example 1" -->
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
            System.out.println(res.paginatedResponseEvidenceUrl().get());
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

## getFrameworkCodes

Retrieves all valid framework codes for the specified audit. This endpoint helps users discover which framework codes are available for creating and updating information requests for this audit.

Use this endpoint to:
- Discover available framework codes before creating information requests
- Validate framework codes against the audit's framework
- Get context about what framework codes are available for the audit type

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="GetFrameworkCodes" method="get" path="/audits/{auditId}/framework-codes" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.GetFrameworkCodesResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        GetFrameworkCodesResponse res = sdk.audits().getFrameworkCodes()
                .auditId("<id>")
                .call();

        if (res.frameworkCodes().isPresent()) {
            System.out.println(res.frameworkCodes().get());
        }
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        |
| ------------------ | ------------------ | ------------------ | ------------------ |
| `auditId`          | *String*           | :heavy_check_mark: | N/A                |

### Response

**[GetFrameworkCodesResponse](../../models/operations/GetFrameworkCodesResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listInformationRequests

Retrieves a paginated list of all information requests for an audit, enabling
external audit management systems to display and track evidence requests.

This endpoint always includes soft-deleted records (where `deletionDate !== null`).
Clients should check the `deletionDate` field to identify and handle deleted records
appropriately in their systems.

This endpoint supports delta synchronization via the `changedSinceDate` parameter,
allowing efficient polling for changes without retrieving the entire dataset.

Pagination usage:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage` to see if more data exists
3. If true, use `results.pageInfo.endCursor` as `pageCursor` in next request
4. Repeat until `hasNextPage` is false

Delta sync usage:
1. Store the timestamp of your last sync
2. Pass that timestamp as `changedSinceDate`
3. Only requests created, modified, or deleted since that timestamp are returned
4. Process updates and soft-deletes by checking the `deletionDate` field
5. Update your last sync timestamp to the current time

Rate limit: 50 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListInformationRequests" method="get" path="/audits/{auditId}/information-requests" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListInformationRequestsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListInformationRequestsResponse res = sdk.audits().listInformationRequests()
                .auditId("<id>")
                .pageSize(10)
                .call();

        if (res.paginatedResponseInformationRequest().isPresent()) {
            System.out.println(res.paginatedResponseInformationRequest().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                   | Type                                                                                                                                                                        | Required                                                                                                                                                                    | Description                                                                                                                                                                 |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `auditId`                                                                                                                                                                   | *String*                                                                                                                                                                    | :heavy_check_mark:                                                                                                                                                          | N/A                                                                                                                                                                         |
| `pageSize`                                                                                                                                                                  | *Optional\<Integer>*                                                                                                                                                        | :heavy_minus_sign:                                                                                                                                                          | Maximum number of information requests to return per page.                                                                                                                  |
| `pageCursor`                                                                                                                                                                | *Optional\<String>*                                                                                                                                                         | :heavy_minus_sign:                                                                                                                                                          | Pagination cursor from a previous response. Provide to fetch the next page of results.                                                                                      |
| `changedSinceDate`                                                                                                                                                          | [OffsetDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html)                                                                                   | :heavy_minus_sign:                                                                                                                                                          | Includes all information requests that have changed since changedSinceDate.<br/>Considers creationDate, modificationDate, and deletionDate timestamps when determining changes. |

### Response

**[ListInformationRequestsResponse](../../models/operations/ListInformationRequestsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## createInformationRequest

Creates a new information request for an audit during audit setup or as requirements evolve.

After creating all information requests, use POST /audits/{auditId}/share-information-request-list
to make them visible to the customer organization. Until shared, requests remain in draft state
visible only to auditors.

New requests are created in an initial state indicating evidence is needed. The status
progresses through the workflow: initial state → awaiting review → approved or flagged.

Rate limit: 600 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="CreateInformationRequest" method="post" path="/audits/{auditId}/information-requests" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.CreateInformationRequestInput;
import com.vanta.vanta_auditor_api.models.components.InformationRequestType;
import com.vanta.vanta_auditor_api.models.operations.CreateInformationRequestResponse;
import java.lang.Exception;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        CreateInformationRequestResponse res = sdk.audits().createInformationRequest()
                .auditId("<id>")
                .createInformationRequestInput(CreateInformationRequestInput.builder()
                    .uniqueId("<id>")
                    .title("<value>")
                    .requestType(InformationRequestType.SAMPLE)
                    .frameworkCodes(List.of())
                    .build())
                .call();

        if (res.informationRequest().isPresent()) {
            System.out.println(res.informationRequest().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `auditId`                                                                                 | *String*                                                                                  | :heavy_check_mark:                                                                        | N/A                                                                                       |
| `createInformationRequestInput`                                                           | [CreateInformationRequestInput](../../models/components/CreateInformationRequestInput.md) | :heavy_check_mark:                                                                        | N/A                                                                                       |

### Response

**[CreateInformationRequestResponse](../../models/operations/CreateInformationRequestResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## getInformationRequest

Retrieves a single information request by its ID for an audit, allowing external
audit management systems to fetch the latest state of a specific request without
paginating through the full list.

Soft-deleted records (where `deletionDate !== null`) are included in the response.
Clients should check `deletionDate` to determine whether the request has been deleted.

Rate limit: 50 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="GetInformationRequest" method="get" path="/audits/{auditId}/information-requests/{requestId}" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.GetInformationRequestResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        GetInformationRequestResponse res = sdk.audits().getInformationRequest()
                .auditId("<id>")
                .requestId("<id>")
                .call();

        if (res.informationRequest().isPresent()) {
            System.out.println(res.informationRequest().get());
        }
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        |
| ------------------ | ------------------ | ------------------ | ------------------ |
| `auditId`          | *String*           | :heavy_check_mark: | N/A                |
| `requestId`        | *String*           | :heavy_check_mark: | N/A                |

### Response

**[GetInformationRequestResponse](../../models/operations/GetInformationRequestResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## updateInformationRequest

Updates an existing information request for an audit, allowing modification of request
details as audit requirements evolve. Supports partial updates where only specified
fields are changed; omitted fields remain unchanged.

Common use cases:
- Updating due dates as audit timelines shift
- Refining descriptions to clarify requirements
- Adjusting request type

Note: The `modificationDate` is automatically updated to the current timestamp
when any field is changed.

Rate limit: 50 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="UpdateInformationRequest" method="patch" path="/audits/{auditId}/information-requests/{requestId}" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.PartialUpdateInformationRequest;
import com.vanta.vanta_auditor_api.models.operations.UpdateInformationRequestResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        UpdateInformationRequestResponse res = sdk.audits().updateInformationRequest()
                .auditId("<id>")
                .requestId("<id>")
                .partialUpdateInformationRequest(PartialUpdateInformationRequest.builder()
                    .build())
                .call();

        if (res.informationRequest().isPresent()) {
            System.out.println(res.informationRequest().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                     | Type                                                                                          | Required                                                                                      | Description                                                                                   |
| --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
| `auditId`                                                                                     | *String*                                                                                      | :heavy_check_mark:                                                                            | N/A                                                                                           |
| `requestId`                                                                                   | *String*                                                                                      | :heavy_check_mark:                                                                            | N/A                                                                                           |
| `partialUpdateInformationRequest`                                                             | [PartialUpdateInformationRequest](../../models/components/PartialUpdateInformationRequest.md) | :heavy_check_mark:                                                                            | N/A                                                                                           |

### Response

**[UpdateInformationRequestResponse](../../models/operations/UpdateInformationRequestResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## deleteInformationRequest

Deletes an information request for an audit. This performs a soft delete, marking
the request as deleted (setting `deletionDate`) while preserving it in the system
for audit history and compliance tracking.

Soft deletion allows:
- Maintaining complete audit trail of all requests ever created
- Retrieving deleted requests via `changedSinceDate` for synchronization

After deletion:
- The request will not appear in normal list responses (without `changedSinceDate`)
- The request's `deletionDate` field will be populated

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="DeleteInformationRequest" method="delete" path="/audits/{auditId}/information-requests/{requestId}" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.DeleteInformationRequestResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        DeleteInformationRequestResponse res = sdk.audits().deleteInformationRequest()
                .auditId("<id>")
                .requestId("<id>")
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        |
| ------------------ | ------------------ | ------------------ | ------------------ |
| `auditId`          | *String*           | :heavy_check_mark: | N/A                |
| `requestId`        | *String*           | :heavy_check_mark: | N/A                |

### Response

**[DeleteInformationRequestResponse](../../models/operations/DeleteInformationRequestResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## acceptInformationRequestEvidence

Accepts evidence for an information request, confirming that all submitted evidence
meets audit requirements. This action changes the request's approvalStatus to
an approved state and creates an activity log entry.

Acceptance workflow:
1. Auditor reviews submitted evidence
2. If evidence is satisfactory, auditor calls this endpoint
3. Request status changes to approved state and is considered complete for this audit cycle

Use this endpoint when:
- All required evidence has been submitted
- Evidence quality meets audit standards
- Evidence addresses all specified framework codes
- No additional information is needed

Rate limit: 50 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="AcceptInformationRequestEvidence" method="post" path="/audits/{auditId}/information-requests/{requestId}/accept-evidence" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.AcceptInformationRequestEvidenceInput;
import com.vanta.vanta_auditor_api.models.operations.AcceptInformationRequestEvidenceResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        AcceptInformationRequestEvidenceResponse res = sdk.audits().acceptInformationRequestEvidence()
                .auditId("<id>")
                .requestId("<id>")
                .acceptInformationRequestEvidenceInput(AcceptInformationRequestEvidenceInput.builder()
                    .auditorEmail("<value>")
                    .build())
                .call();

        if (res.informationRequest().isPresent()) {
            System.out.println(res.informationRequest().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                 | Type                                                                                                      | Required                                                                                                  | Description                                                                                               |
| --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| `auditId`                                                                                                 | *String*                                                                                                  | :heavy_check_mark:                                                                                        | N/A                                                                                                       |
| `requestId`                                                                                               | *String*                                                                                                  | :heavy_check_mark:                                                                                        | N/A                                                                                                       |
| `acceptInformationRequestEvidenceInput`                                                                   | [AcceptInformationRequestEvidenceInput](../../models/components/AcceptInformationRequestEvidenceInput.md) | :heavy_check_mark:                                                                                        | N/A                                                                                                       |

### Response

**[AcceptInformationRequestEvidenceResponse](../../models/operations/AcceptInformationRequestEvidenceResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listInformationRequestActivity

Retrieves a paginated list of activity logs for an information request, providing
a complete audit trail of all changes and actions.

This endpoint supports delta synchronization via the `changedSinceDate` parameter,
allowing efficient polling for changes without retrieving the entire dataset.

Pagination usage:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage` to see if more data exists
3. If true, use `results.pageInfo.endCursor` as `pageCursor` in next request
4. Repeat until `hasNextPage` is false

Delta sync usage:
1. Store the timestamp of your last sync
2. Pass that timestamp as `changedSinceDate`
3. Only activity created since that timestamp is returned
4. Process updates to track all changes to the information request
5. Update your last sync timestamp to the current time

Rate limit: 50 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListInformationRequestActivity" method="get" path="/audits/{auditId}/information-requests/{requestId}/activity" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListInformationRequestActivityRequest;
import com.vanta.vanta_auditor_api.models.operations.ListInformationRequestActivityResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListInformationRequestActivityRequest req = ListInformationRequestActivityRequest.builder()
                .auditId("<id>")
                .requestId("<id>")
                .build();

        ListInformationRequestActivityResponse res = sdk.audits().listInformationRequestActivity()
                .request(req)
                .call();

        if (res.paginatedResponseInformationRequestActivityLog().isPresent()) {
            System.out.println(res.paginatedResponseInformationRequestActivityLog().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                 | Type                                                                                                      | Required                                                                                                  | Description                                                                                               |
| --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                 | [ListInformationRequestActivityRequest](../../models/operations/ListInformationRequestActivityRequest.md) | :heavy_check_mark:                                                                                        | The request object to use for the request.                                                                |

### Response

**[ListInformationRequestActivityResponse](../../models/operations/ListInformationRequestActivityResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listCommentsForInformationRequest

Retrieves a paginated list of comments for an information request, enabling
auditors to view communication history and collaborate with customers.

This endpoint always includes soft-deleted records (where `deletionDate !== null`).
Clients should check the `deletionDate` field to identify and handle deleted records
appropriately in their systems.

This endpoint supports delta synchronization via the `changedSinceDate` parameter,
allowing efficient polling for changes without retrieving the entire dataset.

Pagination usage:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage` to see if more data exists
3. If true, use `results.pageInfo.endCursor` as `pageCursor` in next request
4. Repeat until `hasNextPage` is false

Delta sync usage:
1. Store the timestamp of your last sync
2. Pass that timestamp as `changedSinceDate`
3. Only comments created, modified, or deleted since that timestamp are returned
4. Process updates, including soft-deletes (deletionDate !== null)
5. Update your last sync timestamp to the current time

Rate limit: 50 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListCommentsForInformationRequest" method="get" path="/audits/{auditId}/information-requests/{requestId}/comments" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListCommentsForInformationRequestRequest;
import com.vanta.vanta_auditor_api.models.operations.ListCommentsForInformationRequestResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListCommentsForInformationRequestRequest req = ListCommentsForInformationRequestRequest.builder()
                .auditId("<id>")
                .requestId("<id>")
                .build();

        ListCommentsForInformationRequestResponse res = sdk.audits().listCommentsForInformationRequest()
                .request(req)
                .call();

        if (res.paginatedResponseInformationRequestComment().isPresent()) {
            System.out.println(res.paginatedResponseInformationRequestComment().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                       | Type                                                                                                            | Required                                                                                                        | Description                                                                                                     |
| --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                       | [ListCommentsForInformationRequestRequest](../../models/operations/ListCommentsForInformationRequestRequest.md) | :heavy_check_mark:                                                                                              | The request object to use for the request.                                                                      |

### Response

**[ListCommentsForInformationRequestResponse](../../models/operations/ListCommentsForInformationRequestResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## createCommentForInformationRequest

Creates a new comment for an information request. The comment author must be an auditor
in the audit firm making the request. The comment will be associated with the information
request and visible to all authorized users.

Rate limit: 50 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="CreateCommentForInformationRequest" method="post" path="/audits/{auditId}/information-requests/{requestId}/comments" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.AddInformationRequestCommentInput;
import com.vanta.vanta_auditor_api.models.operations.CreateCommentForInformationRequestResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        CreateCommentForInformationRequestResponse res = sdk.audits().createCommentForInformationRequest()
                .auditId("<id>")
                .requestId("<id>")
                .addInformationRequestCommentInput(AddInformationRequestCommentInput.builder()
                    .text("<value>")
                    .email("Daryl.Bartell38@gmail.com")
                    .creationDate(OffsetDateTime.parse("2024-06-25T05:04:31.105Z"))
                    .build())
                .call();

        if (res.informationRequestComment().isPresent()) {
            System.out.println(res.informationRequestComment().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                         | Type                                                                                              | Required                                                                                          | Description                                                                                       |
| ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- |
| `auditId`                                                                                         | *String*                                                                                          | :heavy_check_mark:                                                                                | N/A                                                                                               |
| `requestId`                                                                                       | *String*                                                                                          | :heavy_check_mark:                                                                                | N/A                                                                                               |
| `addInformationRequestCommentInput`                                                               | [AddInformationRequestCommentInput](../../models/components/AddInformationRequestCommentInput.md) | :heavy_check_mark:                                                                                | N/A                                                                                               |

### Response

**[CreateCommentForInformationRequestResponse](../../models/operations/CreateCommentForInformationRequestResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## updateCommentForInformationRequest

Updates an existing comment for an information request. Only the original author
of the comment can update it. The author is identified by their email address,
which must match the email of the user who created the comment.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="UpdateCommentForInformationRequest" method="patch" path="/audits/{auditId}/information-requests/{requestId}/comments/{commentId}" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.UpdateInformationRequestCommentInput;
import com.vanta.vanta_auditor_api.models.operations.UpdateCommentForInformationRequestResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        UpdateCommentForInformationRequestResponse res = sdk.audits().updateCommentForInformationRequest()
                .auditId("<id>")
                .requestId("<id>")
                .commentId("<id>")
                .updateInformationRequestCommentInput(UpdateInformationRequestCommentInput.builder()
                    .text("<value>")
                    .email("Gerald_Gusikowski36@yahoo.com")
                    .build())
                .call();

        if (res.informationRequestComment().isPresent()) {
            System.out.println(res.informationRequestComment().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                               | Type                                                                                                    | Required                                                                                                | Description                                                                                             |
| ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| `auditId`                                                                                               | *String*                                                                                                | :heavy_check_mark:                                                                                      | N/A                                                                                                     |
| `requestId`                                                                                             | *String*                                                                                                | :heavy_check_mark:                                                                                      | N/A                                                                                                     |
| `commentId`                                                                                             | *String*                                                                                                | :heavy_check_mark:                                                                                      | N/A                                                                                                     |
| `updateInformationRequestCommentInput`                                                                  | [UpdateInformationRequestCommentInput](../../models/components/UpdateInformationRequestCommentInput.md) | :heavy_check_mark:                                                                                      | N/A                                                                                                     |

### Response

**[UpdateCommentForInformationRequestResponse](../../models/operations/UpdateCommentForInformationRequestResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## deleteCommentForInformationRequest

Deletes an existing comment for an information request. Only the original author
of the comment can delete it. The author is identified by their email address,
which must match the email of the user who created the comment.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="DeleteCommentForInformationRequest" method="delete" path="/audits/{auditId}/information-requests/{requestId}/comments/{commentId}" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.DeleteInformationRequestCommentInput;
import com.vanta.vanta_auditor_api.models.operations.DeleteCommentForInformationRequestResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        DeleteCommentForInformationRequestResponse res = sdk.audits().deleteCommentForInformationRequest()
                .auditId("<id>")
                .requestId("<id>")
                .commentId("<id>")
                .deleteInformationRequestCommentInput(DeleteInformationRequestCommentInput.builder()
                    .email("Ole.Adams@gmail.com")
                    .build())
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                                                                                               | Type                                                                                                    | Required                                                                                                | Description                                                                                             |
| ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| `auditId`                                                                                               | *String*                                                                                                | :heavy_check_mark:                                                                                      | N/A                                                                                                     |
| `requestId`                                                                                             | *String*                                                                                                | :heavy_check_mark:                                                                                      | N/A                                                                                                     |
| `commentId`                                                                                             | *String*                                                                                                | :heavy_check_mark:                                                                                      | N/A                                                                                                     |
| `deleteInformationRequestCommentInput`                                                                  | [DeleteInformationRequestCommentInput](../../models/components/DeleteInformationRequestCommentInput.md) | :heavy_check_mark:                                                                                      | N/A                                                                                                     |

### Response

**[DeleteCommentForInformationRequestResponse](../../models/operations/DeleteCommentForInformationRequestResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listInformationRequestEvidence

Retrieves a paginated list of all evidence attached to an information request,
enabling auditors to review evidence submitted by customers.

This endpoint always includes soft-deleted records (where `deletionDate !== null`).
Clients should check the `deletionDate` field to identify and handle deleted records
appropriately in their systems.

This endpoint supports delta synchronization via the `changedSinceDate` parameter,
allowing efficient polling for changes without retrieving the entire dataset.

Pagination usage:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage` to see if more data exists
3. If true, use `results.pageInfo.endCursor` as `pageCursor` in next request
4. Repeat until `hasNextPage` is false

Delta sync usage:
1. Store the timestamp of your last sync
2. Pass that timestamp as `changedSinceDate`
3. Only evidence created, modified, shared, or deleted since that timestamp is returned
4. Process updates, including soft-deletes (deletionDate !== null)
5. Update your last sync timestamp to the current time

Rate limit: 50 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListInformationRequestEvidence" method="get" path="/audits/{auditId}/information-requests/{requestId}/evidence" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListInformationRequestEvidenceRequest;
import com.vanta.vanta_auditor_api.models.operations.ListInformationRequestEvidenceResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListInformationRequestEvidenceRequest req = ListInformationRequestEvidenceRequest.builder()
                .auditId("<id>")
                .requestId("<id>")
                .build();

        ListInformationRequestEvidenceResponse res = sdk.audits().listInformationRequestEvidence()
                .request(req)
                .call();

        if (res.paginatedResponseInformationRequestEvidence().isPresent()) {
            System.out.println(res.paginatedResponseInformationRequestEvidence().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                 | Type                                                                                                      | Required                                                                                                  | Description                                                                                               |
| --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                 | [ListInformationRequestEvidenceRequest](../../models/operations/ListInformationRequestEvidenceRequest.md) | :heavy_check_mark:                                                                                        | The request object to use for the request.                                                                |

### Response

**[ListInformationRequestEvidenceResponse](../../models/operations/ListInformationRequestEvidenceResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## getInformationRequestTestSnapshotEvidenceDetail

Retrieves the rich detail for a single VANTA_TEST_SNAPSHOT evidence row
attached to an information request. The response includes test-level
metadata (description, integrations, SLA), the raw test data captured
at snapshot time, and the resources that were excluded from the test
(out-of-scope resources).

For structured snapshots, the `rawTestData` array contains one row per
resource the test ran against; each row carries `resourceId`,
`resourceType`, and the raw JSON for that resource.

For unstructured snapshots, the `rawTestData` array contains a single
row with `resourceId` and `resourceType` set to `null` and `rawJson`
containing the entire test-run JSON blob.

The `outOfScopeResources` field lists resources excluded at the test
level (customer-disabled) and the framework level (segment
configuration). Empty exclusion groups are filtered out.

The `apiRequests` array contains the HTTP requests captured during API
introspection tests. Empty when the test does not perform API
introspection.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="GetInformationRequestTestSnapshotEvidenceDetail" method="get" path="/audits/{auditId}/information-requests/{requestId}/evidence/{evidenceId}/test-snapshot" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.GetInformationRequestTestSnapshotEvidenceDetailResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        GetInformationRequestTestSnapshotEvidenceDetailResponse res = sdk.audits().getInformationRequestTestSnapshotEvidenceDetail()
                .auditId("<id>")
                .requestId("<id>")
                .evidenceId("<id>")
                .call();

        if (res.vantaTestSnapshotEvidenceDetail().isPresent()) {
            System.out.println(res.vantaTestSnapshotEvidenceDetail().get());
        }
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        |
| ------------------ | ------------------ | ------------------ | ------------------ |
| `auditId`          | *String*           | :heavy_check_mark: | N/A                |
| `requestId`        | *String*           | :heavy_check_mark: | N/A                |
| `evidenceId`       | *String*           | :heavy_check_mark: | N/A                |

### Response

**[GetInformationRequestTestSnapshotEvidenceDetailResponse](../../models/operations/GetInformationRequestTestSnapshotEvidenceDetailResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## flagInformationRequestEvidence

Flags evidence for an information request when it doesn't meet audit requirements,
marking issues that need to be addressed before approval. This action changes the
request's approvalStatus to a flagged state and creates an activity log entry.

Flagging workflow:
1. Auditor reviews submitted evidence
2. If issues are found, auditor calls this endpoint with detailed reason
3. Request status changes to flagged state
4. Customer is notified and can see the reason in activity logs
5. Customer addresses issues and updates evidence
6. When ready, customer changes status back to awaiting review
7. Auditor reviews again and either flags again or accepts

The `reason` field should clearly explain what's missing or incorrect so the
customer knows exactly what to fix. This reason is visible to the customer
and appears in the activity log.

Rate limit: 50 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="FlagInformationRequestEvidence" method="post" path="/audits/{auditId}/information-requests/{requestId}/flag-evidence" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.FlagInformationRequestEvidenceInput;
import com.vanta.vanta_auditor_api.models.operations.FlagInformationRequestEvidenceResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        FlagInformationRequestEvidenceResponse res = sdk.audits().flagInformationRequestEvidence()
                .auditId("<id>")
                .requestId("<id>")
                .flagInformationRequestEvidenceInput(FlagInformationRequestEvidenceInput.builder()
                    .auditorEmail("<value>")
                    .reason("<value>")
                    .build())
                .call();

        if (res.informationRequest().isPresent()) {
            System.out.println(res.informationRequest().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                             | Type                                                                                                  | Required                                                                                              | Description                                                                                           |
| ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| `auditId`                                                                                             | *String*                                                                                              | :heavy_check_mark:                                                                                    | N/A                                                                                                   |
| `requestId`                                                                                           | *String*                                                                                              | :heavy_check_mark:                                                                                    | N/A                                                                                                   |
| `flagInformationRequestEvidenceInput`                                                                 | [FlagInformationRequestEvidenceInput](../../models/components/FlagInformationRequestEvidenceInput.md) | :heavy_check_mark:                                                                                    | N/A                                                                                                   |

### Response

**[FlagInformationRequestEvidenceResponse](../../models/operations/FlagInformationRequestEvidenceResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listIntegrations

Retrieves integration population data for an audit.

This endpoint provides access to integration records visible to auditors
during an audit engagement. Integrations represent connected services
(e.g., GitHub, AWS, Slack) that provide data for the audit.

Supports filtering by:
- `search`: Searches integration names (case-insensitive)
- `tagsMatchesAny`: Filters by integration tag (ACCESS, COMPUTERS, etc.)
- `categoriesMatchesAny`: Filters by service category (CLOUD_PROVIDER, HR_PROVIDER, etc.)

Uses cursor-based pagination. To paginate:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage`
3. Use `results.pageInfo.endCursor` as `pageCursor` for next request

Results are sorted by integration display name (ascending). This sort order
is fixed and cannot be customized via query parameters.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListIntegrations" method="get" path="/audits/{auditId}/integrations" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListIntegrationsRequest;
import com.vanta.vanta_auditor_api.models.operations.ListIntegrationsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListIntegrationsRequest req = ListIntegrationsRequest.builder()
                .auditId("<id>")
                .build();

        ListIntegrationsResponse res = sdk.audits().listIntegrations()
                .request(req)
                .call();

        if (res.paginatedResponseAuditIntegration().isPresent()) {
            System.out.println(res.paginatedResponseAuditIntegration().get());
        }
    }
}
```

### Parameters

| Parameter                                                                     | Type                                                                          | Required                                                                      | Description                                                                   |
| ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- |
| `request`                                                                     | [ListIntegrationsRequest](../../models/operations/ListIntegrationsRequest.md) | :heavy_check_mark:                                                            | The request object to use for the request.                                    |

### Response

**[ListIntegrationsResponse](../../models/operations/ListIntegrationsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listAuditIssues

Retrieves a list of all issues that have been shared with an audit.

The issues returned are immutable, point-in-time snapshots; there may be duplicates of issues that have been snapshotted at different times.
The GET /audits/{auditId}/issues/snapshots endpoint can be used to retrieve metadata about the snapshots that issues belong to.
Issues represent compliance findings from a variety of sources that need to be tracked and remediated.

Supports filtering by:
- `search`: full text search across issue title and description
- `snapshotId`: filtering to a specific snapshot or snapshots, which represent point-in-time captures of issues. Use the GET /audits/{auditId}/issues/snapshots endpoint to retrieve snapshot IDs and metadata.
- `createdAfterDate` / `createdBeforeDate`: filter to issues created within a date range (inclusive)
- `detectedAfterDate` / `detectedBeforeDate`: filter to issues detected within a date range (inclusive)

Results are sorted by issue creation date in descending order (newest first) by default.
Use `orderBy` and `orderDirection` to customize sorting.
Sort parameters must remain consistent across paginated requests.

Uses cursor-based pagination. To paginate:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage`
3. Use `results.pageInfo.endCursor` as `pageCursor` for next request

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListAuditIssues" method="get" path="/audits/{auditId}/issues/items" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditIssuesRequest;
import com.vanta.vanta_auditor_api.models.operations.ListAuditIssuesResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListAuditIssuesRequest req = ListAuditIssuesRequest.builder()
                .auditId("<id>")
                .build();

        ListAuditIssuesResponse res = sdk.audits().listAuditIssues()
                .request(req)
                .call();

        if (res.paginatedIssueSnapshotItemsResponse().isPresent()) {
            System.out.println(res.paginatedIssueSnapshotItemsResponse().get());
        }
    }
}
```

### Parameters

| Parameter                                                                   | Type                                                                        | Required                                                                    | Description                                                                 |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `request`                                                                   | [ListAuditIssuesRequest](../../models/operations/ListAuditIssuesRequest.md) | :heavy_check_mark:                                                          | The request object to use for the request.                                  |

### Response

**[ListAuditIssuesResponse](../../models/operations/ListAuditIssuesResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listAuditSnapshots

Retrieves a list of snapshots that have been shared with an audit.

The snapshots returned contain metadata about point-in-time captures of issues for an audit.
This data can be used to filter down the list of issues to specific snapshots when querying the GET /audits/{auditId}/issues/items endpoint.

Supports filtering by:
- `search`: full text search across snapshot title and description

Results are sorted by snapshot creation date in descending order (newest first).

Uses cursor-based pagination. To paginate:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage`
3. Use `results.pageInfo.endCursor` as `pageCursor` for next request

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListAuditSnapshots" method="get" path="/audits/{auditId}/issues/snapshots" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditSnapshotsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListAuditSnapshotsResponse res = sdk.audits().listAuditSnapshots()
                .auditId("<id>")
                .pageSize(10)
                .call();

        if (res.paginatedIssueSnapshotMetadataResponse().isPresent()) {
            System.out.println(res.paginatedIssueSnapshotMetadataResponse().get());
        }
    }
}
```

### Parameters

| Parameter                                                   | Type                                                        | Required                                                    | Description                                                 |
| ----------------------------------------------------------- | ----------------------------------------------------------- | ----------------------------------------------------------- | ----------------------------------------------------------- |
| `auditId`                                                   | *String*                                                    | :heavy_check_mark:                                          | The audit ID                                                |
| `pageSize`                                                  | *Optional\<Integer>*                                        | :heavy_minus_sign:                                          | Maximum number of results per page (1-100, default 10)      |
| `pageCursor`                                                | *Optional\<String>*                                         | :heavy_minus_sign:                                          | Pagination cursor from previous response                    |
| `search`                                                    | *Optional\<String>*                                         | :heavy_minus_sign:                                          | Search term for filtering by snapshot title and description |

### Response

**[ListAuditSnapshotsResponse](../../models/operations/ListAuditSnapshotsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listVendors

Retrieves vendor population data for an audit.

This endpoint provides access to vendor records visible to auditors
during an audit engagement.

Supports filtering by:
- `search`: Searches vendor names (case-insensitive)
- `vendorStatusesMatchesAny`: Filters by vendor status (ACTIVE, ARCHIVED, IN_PROCUREMENT)
- `inherentRiskMatchesAny`: Filters by inherent risk level

Results are sorted by name (ascending) by default.
Use `orderBy` and `orderDirection` to customize sorting.
Sort parameters must remain consistent across paginated requests.

Uses cursor-based pagination. To paginate:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage`
3. Use `results.pageInfo.endCursor` as `pageCursor` for next request

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListVendors" method="get" path="/audits/{auditId}/managed-vendors" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListVendorsRequest;
import com.vanta.vanta_auditor_api.models.operations.ListVendorsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListVendorsRequest req = ListVendorsRequest.builder()
                .auditId("<id>")
                .build();

        ListVendorsResponse res = sdk.audits().listVendors()
                .request(req)
                .call();

        if (res.paginatedResponseAuditVendor().isPresent()) {
            System.out.println(res.paginatedResponseAuditVendor().get());
        }
    }
}
```

### Parameters

| Parameter                                                           | Type                                                                | Required                                                            | Description                                                         |
| ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- |
| `request`                                                           | [ListVendorsRequest](../../models/operations/ListVendorsRequest.md) | :heavy_check_mark:                                                  | The request object to use for the request.                          |

### Response

**[ListVendorsResponse](../../models/operations/ListVendorsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## ~~listMonitoredComputersInAuditScope~~

Returns a list of computers monitored by an MDM (with an integration built
by Vanta) or by Vanta Device Monitor. Currently this list does not include
resources from partner or customer-built integrations.

End of life — this endpoint works for classic audits only; it does not support
controlled audit view. It remains available for existing classic audits but will be removed once
classic audits are fully phased out, so do not build new integrations on it.

Rate limit: 10 requests / minute.

> :warning: **DEPRECATED**: This will be removed in a future release, please migrate away from it as soon as possible.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListMonitoredComputersInAuditScope" method="get" path="/audits/{auditId}/monitored-computers" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListMonitoredComputersInAuditScopeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListMonitoredComputersInAuditScopeResponse res = sdk.audits().listMonitoredComputersInAuditScope()
                .auditId("<id>")
                .pageSize(10)
                .call();

        if (res.paginatedResponseMonitoredComputer().isPresent()) {
            System.out.println(res.paginatedResponseMonitoredComputer().get());
        }
    }
}
```

### Parameters

| Parameter                                                                      | Type                                                                           | Required                                                                       | Description                                                                    |
| ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ |
| `auditId`                                                                      | *String*                                                                       | :heavy_check_mark:                                                             | N/A                                                                            |
| `pageSize`                                                                     | *Optional\<Integer>*                                                           | :heavy_minus_sign:                                                             | N/A                                                                            |
| `pageCursor`                                                                   | *Optional\<String>*                                                            | :heavy_minus_sign:                                                             | N/A                                                                            |
| `complianceStatusFilterMatchesAny`                                             | List\<[ComputerStatusFilter](../../models/components/ComputerStatusFilter.md)> | :heavy_minus_sign:                                                             | Filters for monitored computers matching any status declared in the filter.    |

### Response

**[ListMonitoredComputersInAuditScopeResponse](../../models/operations/ListMonitoredComputersInAuditScopeResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## getOrganizationInformation

Retrieves organization information for an audit.

This endpoint returns a single record containing the organization's
business information visible to auditors during an audit engagement.

Sorting and pagination are not applicable.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="GetOrganizationInformation" method="get" path="/audits/{auditId}/organization/information" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.GetOrganizationInformationResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        GetOrganizationInformationResponse res = sdk.audits().getOrganizationInformation()
                .auditId("<id>")
                .call();

        if (res.auditOrganizationInformation().isPresent()) {
            System.out.println(res.auditOrganizationInformation().get());
        }
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        |
| ------------------ | ------------------ | ------------------ | ------------------ |
| `auditId`          | *String*           | :heavy_check_mark: | The audit ID       |

### Response

**[GetOrganizationInformationResponse](../../models/operations/GetOrganizationInformationResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## getOrganizationNotifications

Retrieves organization notification settings for an audit.

This endpoint returns a single record containing the auditee
organization's notification configuration — schedule, personnel
reminder settings, and external notification subscriptions
(Compliance, Vendors, Access Reviews, Trust Center).

The response is a single aggregate object per domain. Sorting and
pagination are not applicable. Under a controlled audit view
(TRIMMED_DOWN), only CAV-approved fields are included.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="GetOrganizationNotifications" method="get" path="/audits/{auditId}/organization/notifications" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.GetOrganizationNotificationsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        GetOrganizationNotificationsResponse res = sdk.audits().getOrganizationNotifications()
                .auditId("<id>")
                .call();

        if (res.auditOrganizationNotifications().isPresent()) {
            System.out.println(res.auditOrganizationNotifications().get());
        }
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        |
| ------------------ | ------------------ | ------------------ | ------------------ |
| `auditId`          | *String*           | :heavy_check_mark: | The audit ID       |

### Response

**[GetOrganizationNotificationsResponse](../../models/operations/GetOrganizationNotificationsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## ~~listPeopleInAuditScope~~

Returns a list of people who are in scope for this audit.

End of life — this endpoint works for classic audits only; it does not support
controlled audit view. It remains available for existing classic audits but will be removed once
classic audits are fully phased out, so do not build new integrations on it.

Rate limit: 10 requests / minute.

> :warning: **DEPRECATED**: This will be removed in a future release, please migrate away from it as soon as possible.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListPeopleInAuditScope" method="get" path="/audits/{auditId}/people" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListPeopleInAuditScopeRequest;
import com.vanta.vanta_auditor_api.models.operations.ListPeopleInAuditScopeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListPeopleInAuditScopeRequest req = ListPeopleInAuditScopeRequest.builder()
                .auditId("<id>")
                .build();

        ListPeopleInAuditScopeResponse res = sdk.audits().listPeopleInAuditScope()
                .request(req)
                .call();

        if (res.paginatedResponsePerson().isPresent()) {
            System.out.println(res.paginatedResponsePerson().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `request`                                                                                 | [ListPeopleInAuditScopeRequest](../../models/operations/ListPeopleInAuditScopeRequest.md) | :heavy_check_mark:                                                                        | The request object to use for the request.                                                |

### Response

**[ListPeopleInAuditScopeResponse](../../models/operations/ListPeopleInAuditScopeResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listAccountAccessServices

Retrieves connected account access services for an audit.

Returns the list of identity providers and access integrations (such as
Okta, Azure AD, Google Workspace, AWS IAM) that are connected to the
organization and provide account access data for personnel.

These integrations are used to verify user access and identity management
during an audit engagement.

Uses cursor-based pagination. To paginate:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage`
3. Use `results.pageInfo.endCursor` as `pageCursor` for next request

Results are returned in connection order. Sort order is not guaranteed
and cannot be customized via query parameters.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListAccountAccessServices" method="get" path="/audits/{auditId}/personnel/account-access/services" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAccountAccessServicesResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListAccountAccessServicesResponse res = sdk.audits().listAccountAccessServices()
                .auditId("<id>")
                .pageSize(10)
                .call();

        if (res.paginatedResponseAccountAccessService().isPresent()) {
            System.out.println(res.paginatedResponseAccountAccessService().get());
        }
    }
}
```

### Parameters

| Parameter                                              | Type                                                   | Required                                               | Description                                            |
| ------------------------------------------------------ | ------------------------------------------------------ | ------------------------------------------------------ | ------------------------------------------------------ |
| `auditId`                                              | *String*                                               | :heavy_check_mark:                                     | The audit ID                                           |
| `pageSize`                                             | *Optional\<Integer>*                                   | :heavy_minus_sign:                                     | Maximum number of results per page (1-100, default 10) |
| `pageCursor`                                           | *Optional\<String>*                                    | :heavy_minus_sign:                                     | Pagination cursor from previous response               |

### Response

**[ListAccountAccessServicesResponse](../../models/operations/ListAccountAccessServicesResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listPersonnelAccountAccess

Retrieves account access population data for an audit.

This endpoint provides access to account access records visible to auditors
during an audit engagement. Account access data comes from various sources:

- **IDP Services** (Identity Providers): Okta, Azure AD, Google Workspace, OneLogin, PingOne

  - Returns user accounts from identity providers
  - Supports filtering by search and status

- **Role Grants Services**: GCP, Azure (when role grants are enabled)

  - Returns accounts with role-based access grants
  - Supports filtering by search and status

- **First-Party Account Services**: AWS, Oracle Cloud, Azure (when not using role grants), etc.

  - Returns cloud provider account access records
  - Supports filtering by search and status

- **Received Account Services**: External applications (Jira, GitHub, Slack, etc.)

  - Returns user accounts from third-party integrations
  - Supports filtering by search and status


Supports filtering by:
- `search`: Searches account names/emails (case-insensitive)
- `status`: Filters by account status

Uses cursor-based pagination. To paginate:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage`
3. Use `results.pageInfo.endCursor` as `pageCursor` for next request

The default sort order depends on the service type:
- Identity provider services (e.g. Okta, Azure AD): sorted by email, ascending
- Cloud provider services (e.g. AWS, GCP): sorted by account name, ascending
- Role grant services: sorted by account name, ascending
- Third-party application services (e.g. GitHub, Jira): sorted by account name, ascending

Sort order cannot be customized via query parameters.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListPersonnelAccountAccess" method="get" path="/audits/{auditId}/personnel/account-access/{serviceId}" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListPersonnelAccountAccessRequest;
import com.vanta.vanta_auditor_api.models.operations.ListPersonnelAccountAccessResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListPersonnelAccountAccessRequest req = ListPersonnelAccountAccessRequest.builder()
                .auditId("<id>")
                .serviceId("<id>")
                .build();

        ListPersonnelAccountAccessResponse res = sdk.audits().listPersonnelAccountAccess()
                .request(req)
                .call();

        if (res.paginatedResponseAccountAccess().isPresent()) {
            System.out.println(res.paginatedResponseAccountAccess().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                         | Type                                                                                              | Required                                                                                          | Description                                                                                       |
| ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- |
| `request`                                                                                         | [ListPersonnelAccountAccessRequest](../../models/operations/ListPersonnelAccountAccessRequest.md) | :heavy_check_mark:                                                                                | The request object to use for the request.                                                        |

### Response

**[ListPersonnelAccountAccessResponse](../../models/operations/ListPersonnelAccountAccessResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listPersonnelGroups

Retrieves groups population data for an audit.

This endpoint provides access to the group records visible to auditors
during an audit engagement. Groups represent organizational units that
contain people, either imported from an identity provider (IDP) or
created manually in Vanta.

Only Controlled Audit View (CAV) audits are supported. Full Audit
View audits are rejected with 403.

Supports filtering by:
- `search`: Searches group names (case-insensitive)
- `sourcesMatchesAny`: Filters by IDP source service names

Results are sorted by name (ascending) by default.
Use `orderBy` and `orderDirection` to customize sorting.
Sort parameters must remain consistent across paginated requests.

Uses cursor-based pagination. To paginate:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage`
3. Use `results.pageInfo.endCursor` as `pageCursor` for next request

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListPersonnelGroups" method="get" path="/audits/{auditId}/personnel/groups" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListPersonnelGroupsRequest;
import com.vanta.vanta_auditor_api.models.operations.ListPersonnelGroupsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListPersonnelGroupsRequest req = ListPersonnelGroupsRequest.builder()
                .auditId("<id>")
                .build();

        ListPersonnelGroupsResponse res = sdk.audits().listPersonnelGroups()
                .request(req)
                .call();

        if (res.paginatedResponsePersonnelGroup().isPresent()) {
            System.out.println(res.paginatedResponsePersonnelGroup().get());
        }
    }
}
```

### Parameters

| Parameter                                                                           | Type                                                                                | Required                                                                            | Description                                                                         |
| ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| `request`                                                                           | [ListPersonnelGroupsRequest](../../models/operations/ListPersonnelGroupsRequest.md) | :heavy_check_mark:                                                                  | The request object to use for the request.                                          |

### Response

**[ListPersonnelGroupsResponse](../../models/operations/ListPersonnelGroupsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listPersonnelPeople

Retrieves people population data for an audit.

This endpoint provides access to the people records visible to auditors
during an audit engagement. Only Controlled Audit View (CAV) audits
are supported. Full Audit View audits are rejected with 403.

Supports filtering by:
- `search`: Searches names and email addresses
- `status`: Filters by employment status
- `groupsMatchesAny`: Filter people by group/role IDs

Results are sorted by name (ascending) by default.
Use `orderBy` and `orderDirection` to customize sorting.
Sort parameters must remain consistent across paginated requests.

Uses cursor-based pagination. To paginate:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage`
3. Use `results.pageInfo.endCursor` as `pageCursor` for next request

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListPersonnelPeople" method="get" path="/audits/{auditId}/personnel/people" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListPersonnelPeopleRequest;
import com.vanta.vanta_auditor_api.models.operations.ListPersonnelPeopleResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListPersonnelPeopleRequest req = ListPersonnelPeopleRequest.builder()
                .auditId("<id>")
                .build();

        ListPersonnelPeopleResponse res = sdk.audits().listPersonnelPeople()
                .request(req)
                .call();

        if (res.paginatedResponsePersonnelPerson().isPresent()) {
            System.out.println(res.paginatedResponsePersonnelPerson().get());
        }
    }
}
```

### Parameters

| Parameter                                                                           | Type                                                                                | Required                                                                            | Description                                                                         |
| ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| `request`                                                                           | [ListPersonnelPeopleRequest](../../models/operations/ListPersonnelPeopleRequest.md) | :heavy_check_mark:                                                                  | The request object to use for the request.                                          |

### Response

**[ListPersonnelPeopleResponse](../../models/operations/ListPersonnelPeopleResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listRiskSnapshots

Returns a paginated list of risk assessment snapshots available for an audit.

Risk snapshots capture the state of an organization's risk register at a
point in time. Each snapshot has an `id` that can be used with the
`/audits/{auditId}/risks` endpoint to retrieve the individual risk
scenarios within that snapshot.

Results are sorted by creation date (newest first). This sort order is
fixed and cannot be customized via query parameters. Only snapshots
that are shared with auditors are included.

Uses cursor-based pagination. To paginate:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage`
3. Use `results.pageInfo.endCursor` as `pageCursor` for next request

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListRiskSnapshots" method="get" path="/audits/{auditId}/risks/snapshots" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListRiskSnapshotsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListRiskSnapshotsResponse res = sdk.audits().listRiskSnapshots()
                .auditId("<id>")
                .pageSize(10)
                .call();

        if (res.paginatedResponseRiskSnapshot().isPresent()) {
            System.out.println(res.paginatedResponseRiskSnapshot().get());
        }
    }
}
```

### Parameters

| Parameter                                              | Type                                                   | Required                                               | Description                                            |
| ------------------------------------------------------ | ------------------------------------------------------ | ------------------------------------------------------ | ------------------------------------------------------ |
| `auditId`                                              | *String*                                               | :heavy_check_mark:                                     | The audit ID                                           |
| `pageSize`                                             | *Optional\<Integer>*                                   | :heavy_minus_sign:                                     | Maximum number of results per page (1-100, default 10) |
| `pageCursor`                                           | *Optional\<String>*                                    | :heavy_minus_sign:                                     | Pagination cursor from previous response               |

### Response

**[ListRiskSnapshotsResponse](../../models/operations/ListRiskSnapshotsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## listAuditRisks

Retrieves risk population data for an audit.

This endpoint provides access to the risk records visible to auditors
during an audit engagement. Risk data is scoped to a specific risk
assessment snapshot identified by the `snapshotId` parameter.

Only Controlled Audit View (CAV) audits are supported. Full Audit
View audits are rejected with 403.

Supports filtering by:
- `search`: Searches risk scenario descriptions (case-insensitive)

Results are sorted by identified date (newest first) by default.
Use `orderBy` and `orderDirection` to customize sorting.
Sort parameters must remain consistent across paginated requests.

Uses cursor-based pagination. To paginate:
1. Make initial request with desired `pageSize`
2. Check `results.pageInfo.hasNextPage`
3. Use `results.pageInfo.endCursor` as `pageCursor` for next request

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListAuditRisks" method="get" path="/audits/{auditId}/risks/{snapshotId}" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditRisksRequest;
import com.vanta.vanta_auditor_api.models.operations.ListAuditRisksResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListAuditRisksRequest req = ListAuditRisksRequest.builder()
                .auditId("<id>")
                .snapshotId("<id>")
                .build();

        ListAuditRisksResponse res = sdk.audits().listAuditRisks()
                .request(req)
                .call();

        if (res.paginatedResponseAuditRisk().isPresent()) {
            System.out.println(res.paginatedResponseAuditRisk().get());
        }
    }
}
```

### Parameters

| Parameter                                                                 | Type                                                                      | Required                                                                  | Description                                                               |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `request`                                                                 | [ListAuditRisksRequest](../../models/operations/ListAuditRisksRequest.md) | :heavy_check_mark:                                                        | The request object to use for the request.                                |

### Response

**[ListAuditRisksResponse](../../models/operations/ListAuditRisksResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## shareInformationRequestList

Shares the current information request list for an audit with the customer organization,
making it visible in their portal. This action allows the customer to see all information
requests that have been created for their audit. Only IRL audits are supported.

Rate limit: 10 requests / minute.

### Example Usage

<!-- UsageSnippet language="java" operationID="ShareInformationRequestList" method="post" path="/audits/{auditId}/share-information-request-list" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ShareInformationRequestListResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ShareInformationRequestListResponse res = sdk.audits().shareInformationRequestList()
                .auditId("<id>")
                .call();

        if (res.audit().isPresent()) {
            System.out.println(res.audit().get());
        }
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        |
| ------------------ | ------------------ | ------------------ | ------------------ |
| `auditId`          | *String*           | :heavy_check_mark: | N/A                |

### Response

**[ShareInformationRequestListResponse](../../models/operations/ShareInformationRequestListResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## ~~listVendorsInAuditScope~~

Returns a list of vendors who are in scope for this audit.

End of life — this endpoint works for classic audits only; it does not support
controlled audit view. It remains available for existing classic audits but will be removed once
classic audits are fully phased out, so do not build new integrations on it.

Rate limit: 10 requests / minute.

> :warning: **DEPRECATED**: This will be removed in a future release, please migrate away from it as soon as possible.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListVendorsInAuditScope" method="get" path="/audits/{auditId}/vendors" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListVendorsInAuditScopeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListVendorsInAuditScopeResponse res = sdk.audits().listVendorsInAuditScope()
                .auditId("<id>")
                .pageSize(10)
                .call();

        if (res.paginatedResponseVendor().isPresent()) {
            System.out.println(res.paginatedResponseVendor().get());
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

**[ListVendorsInAuditScopeResponse](../../models/operations/ListVendorsInAuditScopeResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## ~~listVulnerabilities~~

List all vulnerabilities based on selected filters.

End of life — this endpoint works for classic audits only; it does not support
controlled audit view. It remains available for existing classic audits but will be removed once
classic audits are fully phased out, so do not build new integrations on it.

Rate limit: 10 requests / minute.

> :warning: **DEPRECATED**: This will be removed in a future release, please migrate away from it as soon as possible.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListVulnerabilities" method="get" path="/audits/{auditId}/vulnerabilities" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListVulnerabilitiesRequest;
import com.vanta.vanta_auditor_api.models.operations.ListVulnerabilitiesResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListVulnerabilitiesRequest req = ListVulnerabilitiesRequest.builder()
                .auditId("<id>")
                .build();

        ListVulnerabilitiesResponse res = sdk.audits().listVulnerabilities()
                .request(req)
                .call();

        if (res.paginatedResponseAuditorApiVulnerability().isPresent()) {
            System.out.println(res.paginatedResponseAuditorApiVulnerability().get());
        }
    }
}
```

### Parameters

| Parameter                                                                           | Type                                                                                | Required                                                                            | Description                                                                         |
| ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| `request`                                                                           | [ListVulnerabilitiesRequest](../../models/operations/ListVulnerabilitiesRequest.md) | :heavy_check_mark:                                                                  | The request object to use for the request.                                          |

### Response

**[ListVulnerabilitiesResponse](../../models/operations/ListVulnerabilitiesResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## ~~listVulnerabilityRemediationsInAuditScope~~

List all vulnerability remediations based on selected filters that are in scope for this audit.

End of life — this endpoint works for classic audits only; it does not support
controlled audit view. It remains available for existing classic audits but will be removed once
classic audits are fully phased out, so do not build new integrations on it.

Rate limit: 10 requests / minute.

> :warning: **DEPRECATED**: This will be removed in a future release, please migrate away from it as soon as possible.

### Example Usage

<!-- UsageSnippet language="java" operationID="ListVulnerabilityRemediationsInAuditScope" method="get" path="/audits/{auditId}/vulnerability-remediations" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListVulnerabilityRemediationsInAuditScopeRequest;
import com.vanta.vanta_auditor_api.models.operations.ListVulnerabilityRemediationsInAuditScopeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        ListVulnerabilityRemediationsInAuditScopeRequest req = ListVulnerabilityRemediationsInAuditScopeRequest.builder()
                .auditId("<id>")
                .build();

        ListVulnerabilityRemediationsInAuditScopeResponse res = sdk.audits().listVulnerabilityRemediationsInAuditScope()
                .request(req)
                .call();

        if (res.paginatedResponseVulnerabilityRemediation().isPresent()) {
            System.out.println(res.paginatedResponseVulnerabilityRemediation().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                       | Type                                                                                                                            | Required                                                                                                                        | Description                                                                                                                     |
| ------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                                       | [ListVulnerabilityRemediationsInAuditScopeRequest](../../models/operations/ListVulnerabilityRemediationsInAuditScopeRequest.md) | :heavy_check_mark:                                                                                                              | The request object to use for the request.                                                                                      |

### Response

**[ListVulnerabilityRemediationsInAuditScopeResponse](../../models/operations/ListVulnerabilityRemediationsInAuditScopeResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |

## ~~getVulnerableAssets~~

List assets that Vanta monitors that are associated with vulnerabilities.

End of life — this endpoint works for classic audits only; it does not support
controlled audit view. It remains available for existing classic audits but will be removed once
classic audits are fully phased out, so do not build new integrations on it.

Rate limit: 10 requests / minute.

> :warning: **DEPRECATED**: This will be removed in a future release, please migrate away from it as soon as possible.

### Example Usage

<!-- UsageSnippet language="java" operationID="GetVulnerableAssets" method="get" path="/audits/{auditId}/vulnerable-assets" example="Example 1" -->
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.GetVulnerableAssetsRequest;
import com.vanta.vanta_auditor_api.models.operations.GetVulnerableAssetsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        GetVulnerableAssetsRequest req = GetVulnerableAssetsRequest.builder()
                .auditId("<id>")
                .build();

        GetVulnerableAssetsResponse res = sdk.audits().getVulnerableAssets()
                .request(req)
                .call();

        if (res.paginatedResponseVulnerableAsset().isPresent()) {
            System.out.println(res.paginatedResponseVulnerableAsset().get());
        }
    }
}
```

### Parameters

| Parameter                                                                           | Type                                                                                | Required                                                                            | Description                                                                         |
| ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| `request`                                                                           | [GetVulnerableAssetsRequest](../../models/operations/GetVulnerableAssetsRequest.md) | :heavy_check_mark:                                                                  | The request object to use for the request.                                          |

### Response

**[GetVulnerableAssetsResponse](../../models/operations/GetVulnerableAssetsResponse.md)**

### Errors

| Error Type                 | Status Code                | Content Type               |
| -------------------------- | -------------------------- | -------------------------- |
| models/errors/APIException | 4XX, 5XX                   | \*/\*                      |