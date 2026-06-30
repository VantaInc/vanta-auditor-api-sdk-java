# AuditIntegration

Integration row returned by the auditor integrations API.

`id` and `name` are always present. Other fields are optional to support
controlled audits where only approved columns are returned.


## Fields

| Field                                                                         | Type                                                                          | Required                                                                      | Description                                                                   | Example                                                                       |
| ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- |
| `id`                                                                          | *String*                                                                      | :heavy_check_mark:                                                            | Integration identifier.                                                       | github                                                                        |
| `name`                                                                        | *String*                                                                      | :heavy_check_mark:                                                            | Display name of the integration.                                              | GitHub                                                                        |
| `tags`                                                                        | List\<*String*>                                                               | :heavy_minus_sign:                                                            | Integration tag display names describing what data this integration provides. | [<br/>"Vulnerabilities",<br/>"Code changes"<br/>]                             |
| `categories`                                                                  | List\<*String*>                                                               | :heavy_minus_sign:                                                            | Category display names describing what the integration is used for.           | [<br/>"Version control systems"<br/>]                                         |