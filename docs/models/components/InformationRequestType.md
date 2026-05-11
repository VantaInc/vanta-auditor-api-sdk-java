# InformationRequestType

Type of information request, defining what scope of evidence is needed.

- POINT_IN_TIME: Evidence for a specific moment (e.g., current state of a policy)
- POPULATION: Evidence covering all items in a category (e.g., all employees)
- SAMPLE: Evidence for a representative sample (e.g., 10 random customer records)

## Example Usage

```java
import com.vanta.vanta_auditor_api.models.components.InformationRequestType;

InformationRequestType value = InformationRequestType.POINT_IN_TIME;
```


## Values

| Name            | Value           |
| --------------- | --------------- |
| `POINT_IN_TIME` | POINT_IN_TIME   |
| `POPULATION`    | POPULATION      |
| `SAMPLE`        | SAMPLE          |