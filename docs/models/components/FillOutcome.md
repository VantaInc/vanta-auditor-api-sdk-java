# FillOutcome

Result of an automated evidence fill.
Only populated for evidence fill activities. Null for all other activity types.

## Example Usage

```java
import com.vanta.vanta_auditor_api.models.components.FillOutcome;

FillOutcome value = FillOutcome.SUCCESS;
```


## Values

| Name      | Value     |
| --------- | --------- |
| `SUCCESS` | SUCCESS   |
| `PARTIAL` | PARTIAL   |
| `FAILED`  | FAILED    |