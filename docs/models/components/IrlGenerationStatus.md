# IrlGenerationStatus

Generation status of this audit's Vanta-generated information request list
(IRL). `PENDING`/`RUNNING` defer initial sync, `READY` permits a full sync,
and `FAILED` is terminal. Null means the audit has no generated IRL.

## Example Usage

```java
import com.vanta.vanta_auditor_api.models.components.IrlGenerationStatus;

IrlGenerationStatus value = IrlGenerationStatus.PENDING;
```


## Values

| Name      | Value     |
| --------- | --------- |
| `PENDING` | PENDING   |
| `RUNNING` | RUNNING   |
| `READY`   | READY     |
| `FAILED`  | FAILED    |