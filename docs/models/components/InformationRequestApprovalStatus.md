# InformationRequestApprovalStatus

Current approval status of the information request, tracking its lifecycle through the audit process.

The status progresses through the workflow: initial state → awaiting review → approved or flagged.
Status can move between awaiting review and flagged states as evidence is reviewed and resubmitted.

## Example Usage

```java
import com.vanta.vanta_auditor_api.models.components.InformationRequestApprovalStatus;

InformationRequestApprovalStatus value = InformationRequestApprovalStatus.NEEDS_EVIDENCE;
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `NEEDS_EVIDENCE`   | NEEDS_EVIDENCE     |
| `READY_FOR_AUDIT`  | READY_FOR_AUDIT    |
| `AUDITOR_APPROVED` | AUDITOR_APPROVED   |
| `AUDITOR_FLAGGED`  | AUDITOR_FLAGGED    |