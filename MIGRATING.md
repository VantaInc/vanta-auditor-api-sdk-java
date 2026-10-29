# Migrating to 0.8.0

Version 0.8.0 changes controls calls to accept a request object and adds arguments
to two full model constructors.

## Controls calls

Import the request model:

```java
import com.vanta.vanta_auditor_api.models.operations.ListAuditControlsRequest;
```

Update direct calls:

```java
// Before
sdk.audits().listControls(auditId);

// After
sdk.audits().listControls(
    ListAuditControlsRequest.builder()
        .auditId(auditId)
        .build());
```

For fluent calls, move the parameters into the request builder:

```java
// Before
sdk.audits().listControls()
    .auditId(auditId)
    .pageSize(50)
    .call();

// After
sdk.audits().listControls()
    .request(ListAuditControlsRequest.builder()
        .auditId(auditId)
        .pageSize(50)
        .build())
    .call();
```

`pageCursor` and `externalIdMatchesAny` also move into the request builder. The new
`segmentIdsMatchesAny` filter is optional. The same migration applies to the async
client.

## Model constructors

| Constructor | Migration |
| --- | --- |
| Full `ListAuditControlsRequest(...)` | Add a fifth argument for `segmentIdsMatchesAny`; use `Optional.empty()` to retain the previous filtering behavior. |
| Full `Vendor(...)` | Insert `Optional<String> categoryId` immediately after `riskAttributeIds`; use `Optional.empty()` when unavailable. |

Prefer builders instead of long positional constructors. The one-argument
`ListAuditControlsRequest(auditId)` and seven-argument `Vendor(...)` convenience
constructors remain available.
