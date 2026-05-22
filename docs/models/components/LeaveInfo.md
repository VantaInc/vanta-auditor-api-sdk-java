# LeaveInfo

If present, the user's active/upcoming leave. Empty if the user has no active/upcoming leave.


## Fields

| Field                                                                                     | Type                                                                                      | Required                                                                                  | Description                                                                               |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `startDate`                                                                               | [OffsetDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html) | :heavy_check_mark:                                                                        | The start of the person's leave.                                                          |
| `endDate`                                                                                 | [OffsetDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html) | :heavy_check_mark:                                                                        | The end of the person's leave. Null endDate implies indefinite leave.                     |
| `status`                                                                                  | [LeaveStatus](../../models/components/LeaveStatus.md)                                     | :heavy_check_mark:                                                                        | User can be active or upcoming leave period                                               |