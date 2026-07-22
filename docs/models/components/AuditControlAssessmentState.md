# AuditControlAssessmentState

An auditor's assessment of a control within an audit. This is the full flat
union of every framework's assessment states (the superset); a given audit's
framework only uses its own subset. `NOT_ASSESSED` is shared by all
frameworks and is the default for a control that has not yet been assessed.

Which states apply to which framework:
- Most frameworks (e.g. SOC 2): `IN_PLACE`, `NOT_IN_PLACE`, `PARTIAL`, `NOT_ASSESSED`
- ISO 27001: `CONFORMING`, `MINOR_NON_CONFORMITY`, `MAJOR_NON_CONFORMITY`, `NOT_ASSESSED`
- FedRAMP: `SATISFIED`, `NOT_SATISFIED`, `OTHER_THAN_SATISFIED`, `NOT_ASSESSED`
- FedRAMP Key Security Indicators (KSI): `TRUE`, `FALSE`, `PARTIAL`, `NOT_ASSESSED`

Distinct from `ControlStatus`, which is the computed tests/documents-passing
status — a different concept.

## Example Usage

```java
import com.vanta.vanta_auditor_api.models.components.AuditControlAssessmentState;

AuditControlAssessmentState value = AuditControlAssessmentState.IN_PLACE;
```


## Values

| Name                   | Value                  |
| ---------------------- | ---------------------- |
| `IN_PLACE`             | IN_PLACE               |
| `NOT_IN_PLACE`         | NOT_IN_PLACE           |
| `PARTIAL`              | PARTIAL                |
| `NOT_ASSESSED`         | NOT_ASSESSED           |
| `CONFORMING`           | CONFORMING             |
| `MINOR_NON_CONFORMITY` | MINOR_NON_CONFORMITY   |
| `MAJOR_NON_CONFORMITY` | MAJOR_NON_CONFORMITY   |
| `SATISFIED`            | SATISFIED              |
| `NOT_SATISFIED`        | NOT_SATISFIED          |
| `OTHER_THAN_SATISFIED` | OTHER_THAN_SATISFIED   |
| `TRUE`                 | TRUE                   |
| `FALSE`                | FALSE                  |