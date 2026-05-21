# DeviceMonitoring

Device monitoring task details, or null if not available.
Full Audit View only - omitted in Controlled Audit View.


## Fields

| Field                                                                           | Type                                                                            | Required                                                                        | Description                                                                     | Example                                                                         |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| `status`                                                                        | [PeopleSecurityTaskStatus](../../models/components/PeopleSecurityTaskStatus.md) | :heavy_check_mark:                                                              | Security task status for individual task categories.                            |                                                                                 |
| `dueDate`                                                                       | *Optional\<String>*                                                             | :heavy_check_mark:                                                              | Due date for incomplete task in ISO 8601 format, or null if none.               | 2024-03-15                                                                      |
| `completionDate`                                                                | *Optional\<String>*                                                             | :heavy_check_mark:                                                              | Completion date for completed task in ISO 8601 format, or null if incomplete.   | 2024-01-20                                                                      |