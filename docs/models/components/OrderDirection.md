# OrderDirection

Sort direction shared across the external REST API surface.

`"asc"` for ascending, `"desc"` for descending. Endpoints expose this as the
`orderDirection` / `sortDirection` query parameter and map it onto whatever
internal direction representation the underlying service expects.

## Example Usage

```java
import com.vanta.vanta_auditor_api.models.components.OrderDirection;

OrderDirection value = OrderDirection.ASC;
```


## Values

| Name   | Value  |
| ------ | ------ |
| `ASC`  | asc    |
| `DESC` | desc   |