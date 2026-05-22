# OperatingSystem

The computer's operating system name and version.


## Fields

| Field                                                                               | Type                                                                                | Required                                                                            | Description                                                                         |
| ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| `type`                                                                              | [OperatingSystemType](../../models/components/OperatingSystemType.md)               | :heavy_check_mark:                                                                  | The possible types of the operating system. One of `mac_OS`, `linux`, or `windows`. |
| `version`                                                                           | *Optional\<String>*                                                                 | :heavy_check_mark:                                                                  | The version of the operating system.                                                |