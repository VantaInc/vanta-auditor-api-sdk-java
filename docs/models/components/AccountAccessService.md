# AccountAccessService

An account access service (identity provider, access credential, or external application)
connected to the organization.

These represent credentials for integrations like Okta, Azure AD,
Google Workspace, etc. that provide identity and access management
data for personnel, as well as external applications that provide
received user accounts.


## Fields

| Field                                                                             | Type                                                                              | Required                                                                          | Description                                                                       | Example                                                                           |
| --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- |
| `id`                                                                              | *String*                                                                          | :heavy_check_mark:                                                                | Unique identifier for the service.                                                | credential_abc123                                                                 |
| `credentialDisplayName`                                                           | *String*                                                                          | :heavy_check_mark:                                                                | Display name of the service shown to users.                                       | Okta - Production                                                                 |
| `service`                                                                         | *String*                                                                          | :heavy_check_mark:                                                                | The service name or integration ID.                                               | okta                                                                              |
| `subAccountId`                                                                    | *Optional\<String>*                                                               | :heavy_check_mark:                                                                | Sub-account identifier for multi-account integrations, or null if not applicable. | sub_account_456                                                                   |