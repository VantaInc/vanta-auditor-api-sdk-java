# ApprovalProgress

The issue's approval progress at the time the snapshot was captured,
or null if no approval was in progress or issue approvals are not enabled.


## Fields

| Field                                                        | Type                                                         | Required                                                     | Description                                                  | Example                                                      |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `approvedCount`                                              | *double*                                                     | :heavy_check_mark:                                           | The number of approvers who have approved the issue.         | 1                                                            |
| `totalApprovers`                                             | *double*                                                     | :heavy_check_mark:                                           | The total number of approvers in the issue's approval chain. | 2                                                            |