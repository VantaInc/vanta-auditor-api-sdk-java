# OldStatus

Previous approval status before the status change.
Only populated for status change activities. Null for all other activity types.

## Example Usage

```java
import com.vanta.vanta_auditor_api.models.components.OldStatus;

OldStatus value = OldStatus.READY_FOR_AUDIT;
```


## Values

| Name                        | Value                       |
| --------------------------- | --------------------------- |
| `READY_FOR_AUDIT`           | READY_FOR_AUDIT             |
| `NEEDS_EVIDENCE`            | NEEDS_EVIDENCE              |
| `AUDITOR_APPROVED`          | AUDITOR_APPROVED            |
| `AUDITOR_FLAGGED`           | AUDITOR_FLAGGED             |
| `READY_FOR_INTERNAL_REVIEW` | READY_FOR_INTERNAL_REVIEW   |