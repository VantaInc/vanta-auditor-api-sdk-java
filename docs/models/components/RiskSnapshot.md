# RiskSnapshot

A risk assessment snapshot available for an audit.

Risk snapshots capture the state of an organization's risk register at a
point in time. Use the snapshot `id` with the `/audits/{auditId}/risks/{snapshotId}`
endpoint to retrieve the risk scenarios within a snapshot.


## Fields

| Field                                                                                     | Type                                                                                      | Required                                                                                  | Description                                                                               | Example                                                                                   |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `id`                                                                                      | *String*                                                                                  | :heavy_check_mark:                                                                        | Unique identifier for the risk snapshot.                                                  | 507f1f77bcf86cd799439011                                                                  |
| `createdAt`                                                                               | [OffsetDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html) | :heavy_check_mark:                                                                        | The date and time when this risk snapshot was created.                                    |                                                                                           |