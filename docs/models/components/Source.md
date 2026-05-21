# Source

Source of user data, or null if not available.
Full Audit View only - omitted in Controlled Audit View.


## Fields

| Field                                  | Type                                   | Required                               | Description                            | Example                                |
| -------------------------------------- | -------------------------------------- | -------------------------------------- | -------------------------------------- | -------------------------------------- |
| `mainSource`                           | *String*                               | :heavy_check_mark:                     | Main source of user data.              | Okta                                   |
| `supplementarySource`                  | *Optional\<String>*                    | :heavy_check_mark:                     | Supplementary source, or null if none. | Google Workspace                       |