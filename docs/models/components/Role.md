# Role

The GDPR role of the control, which specifies whether the data is being "collected" or "processed". See the GdprRole enum for possible values.
This field should only be included for controls that are to be mapped to the GDPR framework.

## Example Usage

```java
import com.vanta.vanta_auditor_api.models.components.Role;

Role value = Role.BOTH;
```


## Values

| Name         | Value        |
| ------------ | ------------ |
| `BOTH`       | BOTH         |
| `CONTROLLER` | CONTROLLER   |
| `PROCESSOR`  | PROCESSOR    |