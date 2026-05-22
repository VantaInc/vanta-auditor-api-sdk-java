# ComputerStatusOutcome

The possible outcomes of a status check. The outcome can be one of the following:
FAIL:  The check is failing.
IN_PROGRESS: The check needs further data from the given computer in order to evaluate. The field(s) needed from a computer to calculate the ComputerStatusOutcome were null.
NA: The check is not applicable for the given computer.
PASS: The check is passing.

## Example Usage

```java
import com.vanta.vanta_auditor_api.models.components.ComputerStatusOutcome;

ComputerStatusOutcome value = ComputerStatusOutcome.FAIL;
```


## Values

| Name          | Value         |
| ------------- | ------------- |
| `FAIL`        | FAIL          |
| `IN_PROGRESS` | IN_PROGRESS   |
| `NA`          | NA            |
| `PASS`        | PASS          |