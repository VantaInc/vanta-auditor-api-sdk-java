# ListAuditSnapshotsRequest


## Fields

| Field                                                       | Type                                                        | Required                                                    | Description                                                 |
| ----------------------------------------------------------- | ----------------------------------------------------------- | ----------------------------------------------------------- | ----------------------------------------------------------- |
| `auditId`                                                   | *String*                                                    | :heavy_check_mark:                                          | The audit ID                                                |
| `pageSize`                                                  | *Optional\<Integer>*                                        | :heavy_minus_sign:                                          | Maximum number of results per page (1-100, default 10)      |
| `pageCursor`                                                | *Optional\<String>*                                         | :heavy_minus_sign:                                          | Pagination cursor from previous response                    |
| `search`                                                    | *Optional\<String>*                                         | :heavy_minus_sign:                                          | Search term for filtering by snapshot title and description |