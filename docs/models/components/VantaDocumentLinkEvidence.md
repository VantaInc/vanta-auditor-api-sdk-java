# VantaDocumentLinkEvidence

Represents an external link in a Vanta Document.
The link points to documentation hosted outside of Vanta's system.


## Fields

| Field                                                                                     | Type                                                                                      | Required                                                                                  | Description                                                                               |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `type`                                                                                    | [VantaDocumentLinkEvidenceType](../../models/components/VantaDocumentLinkEvidenceType.md) | :heavy_check_mark:                                                                        | Discriminator to identify this as an external link.                                       |
| `id`                                                                                      | *String*                                                                                  | :heavy_check_mark:                                                                        | The unique identifier for the file version in Vanta's system.                             |
| `url`                                                                                     | *String*                                                                                  | :heavy_check_mark:                                                                        | The URL pointing to the external documentation or resource.                               |