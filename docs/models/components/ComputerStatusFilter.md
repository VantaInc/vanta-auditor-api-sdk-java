# ComputerStatusFilter

Enum representing computer compliance statuses that can be utilized as a filter. The meanings are as follows:
AV_NOT_INSTALLED: The computer does not have antivirus software installed.
HD_NOT_ENCRYPTED: The computer's harddrive is not encrypted.
LAST_CHECK_OVER_14_DAYS: No data has been received from computer for over 14 days.
PWM_NOT_INSTALLED: The computer does not have a password manager installed.
SCREENLOCK_NOT_CONFIGURED: The computer does not have screenlock configured appropriately.

## Example Usage

```java
import com.vanta.vanta_auditor_api.models.components.ComputerStatusFilter;

ComputerStatusFilter value = ComputerStatusFilter.PWM_NOT_INSTALLED;
```


## Values

| Name                        | Value                       |
| --------------------------- | --------------------------- |
| `PWM_NOT_INSTALLED`         | PWM_NOT_INSTALLED           |
| `HD_NOT_ENCRYPTED`          | HD_NOT_ENCRYPTED            |
| `AV_NOT_INSTALLED`          | AV_NOT_INSTALLED            |
| `SCREENLOCK_NOT_CONFIGURED` | SCREENLOCK_NOT_CONFIGURED   |
| `LAST_CHECK_OVER14_DAYS`    | LAST_CHECK_OVER_14_DAYS     |