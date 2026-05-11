# AuditorRequestListMetadata

Metadata about the auditor request list. This field is only present for IRL (Information
Request List) based audits and will be undefined for standard audits. Use the presence
of this field to differentiate between IRL and non-IRL audits.


## Fields

| Field                                                                                     | Type                                                                                      | Required                                                                                  | Description                                                                               |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `requestsSharedWithCustomer`                                                              | [OffsetDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html) | :heavy_check_mark:                                                                        | Timestamp when information requests were shared with the customer. Null if not shared.    |