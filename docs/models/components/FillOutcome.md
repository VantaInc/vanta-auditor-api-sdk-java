# ~~FillOutcome~~

Result of an automated evidence fill. Always null: only evidence fill activities
could populate it, and those are never returned, so nothing can set it. Do not
branch on it.

> :warning: **DEPRECATED**: This will be removed in a future release, please migrate away from it as soon as possible.

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