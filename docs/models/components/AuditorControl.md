# AuditorControl


## Fields

| Field                                                      | Type                                                       | Required                                                   | Description                                                |
| ---------------------------------------------------------- | ---------------------------------------------------------- | ---------------------------------------------------------- | ---------------------------------------------------------- |
| `id`                                                       | *String*                                                   | :heavy_check_mark:                                         | The control's unique ID.                                   |
| `externalId`                                               | *Optional\<String>*                                        | :heavy_check_mark:                                         | The control's external ID.                                 |
| `name`                                                     | *String*                                                   | :heavy_check_mark:                                         | The control's name.                                        |
| `description`                                              | *String*                                                   | :heavy_check_mark:                                         | The control's description.                                 |
| `source`                                                   | [ControlSource](../../models/components/ControlSource.md)  | :heavy_check_mark:                                         | N/A                                                        |
| `domains`                                                  | List\<*String*>                                            | :heavy_check_mark:                                         | The security domains that the control belongs to.          |
| `owner`                                                    | [Optional\<Owner>](../../models/components/Owner.md)       | :heavy_check_mark:                                         | The control's owner.                                       |
| `role`                                                     | *Optional\<String>*                                        | :heavy_minus_sign:                                         | The control's GDPR role, if the control is a GDPR control. |
| `framework`                                                | *String*                                                   | :heavy_check_mark:                                         | The report standard framework fulfilled by the control.    |