# openapi

Developer-friendly & type-safe Java SDK specifically catered to leverage *openapi* API.

<div align="left">
    <a href="https://www.speakeasy.com/?utm_source=openapi&utm_campaign=java"><img src="https://custom-icon-badges.demolab.com/badge/-Built%20By%20Speakeasy-212015?style=for-the-badge&logoColor=FBE331&logo=speakeasy&labelColor=545454" /></a>
    <a href="https://mit-license.org/">
        <img src="https://img.shields.io/badge/License-MIT-blue.svg" style="width: 100px; height: 28px;" />
    </a>
</div>


<br /><br />
> [!IMPORTANT]
> This SDK is not yet ready for production use. To complete setup please follow the steps outlined in your [workspace](https://app.speakeasy.com/org/vanta/vanta). Delete this section before > publishing to a package manager.

<!-- Start Summary [summary] -->
## Summary

Conduct an audit: The Auditor API lets audit firms conduct audits from a tool outside of Vanta. Unlock data syncing with Vanta through this API.
<!-- End Summary [summary] -->

<!-- Start Table of Contents [toc] -->
## Table of Contents
<!-- $toc-max-depth=2 -->
* [openapi](#openapi)
  * [SDK Installation](#sdk-installation)
  * [SDK Example Usage](#sdk-example-usage)
  * [Authentication](#authentication)
  * [Available Resources and Operations](#available-resources-and-operations)
  * [Error Handling](#error-handling)
  * [Server Selection](#server-selection)
* [Development](#development)
  * [Maturity](#maturity)
  * [Contributions](#contributions)

<!-- End Table of Contents [toc] -->

<!-- Start SDK Installation [installation] -->
## SDK Installation

### Getting started

JDK 11 or later is required.

The samples below show how a published SDK artifact is used:

Gradle:
```groovy
implementation 'com.vanta:vanta-auditor-api:0.1.1'
```

Maven:
```xml
<dependency>
    <groupId>com.vanta</groupId>
    <artifactId>vanta-auditor-api</artifactId>
    <version>0.1.1</version>
</dependency>
```

### How to build
After cloning the git repository to your file system you can build the SDK artifact from source to the `build` directory by running `./gradlew build` on *nix systems or `gradlew.bat` on Windows systems.

If you wish to build from source and publish the SDK artifact to your local Maven repository (on your filesystem) then use the following command (after cloning the git repo locally):

On *nix:
```bash
./gradlew publishToMavenLocal -Pskip.signing
```
On Windows:
```bash
gradlew.bat publishToMavenLocal -Pskip.signing
```
<!-- End SDK Installation [installation] -->

<!-- Start SDK Example Usage [usage] -->
## SDK Example Usage

### Example

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditsResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        ListAuditsResponse res = sdk.audits().list()
                .pageSize(10)
                .pageCursor("<value>")
                .changedSinceDate(OffsetDateTime.parse("2025-04-22T08:39:55.981Z"))
                .call();

        if (res.paginatedResponseAudit().isPresent()) {
            // handle response
        }
    }
}
```
<!-- End SDK Example Usage [usage] -->

<!-- Start Authentication [security] -->
## Authentication

### Per-Client Security Schemes

This SDK supports the following security scheme globally:

| Name         | Type | Scheme      |
| ------------ | ---- | ----------- |
| `bearerAuth` | http | HTTP Bearer |

To authenticate with the API the `bearerAuth` parameter must be set when initializing the SDK client instance. For example:
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditsResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        ListAuditsResponse res = sdk.audits().list()
                .pageSize(10)
                .pageCursor("<value>")
                .changedSinceDate(OffsetDateTime.parse("2025-04-22T08:39:55.981Z"))
                .call();

        if (res.paginatedResponseAudit().isPresent()) {
            // handle response
        }
    }
}
```
<!-- End Authentication [security] -->

<!-- Start Available Resources and Operations [operations] -->
## Available Resources and Operations

<details open>
<summary>Available methods</summary>

### [auditors()](docs/sdks/auditors/README.md)

* [create](docs/sdks/auditors/README.md#create) - Create an auditor

### [audits()](docs/sdks/audits/README.md)

* [list](docs/sdks/audits/README.md#list) - List audits
* [listEvidenceUrls](docs/sdks/audits/README.md#listevidenceurls) - List audit evidence url
* [listEvidence](docs/sdks/audits/README.md#listevidence) - List audit evidence
* [listComments](docs/sdks/audits/README.md#listcomments) - List audit comments
* [listControls](docs/sdks/audits/README.md#listcontrols) - List audit controls
* [createCommentForEvidence](docs/sdks/audits/README.md#createcommentforevidence) - Create a comment for audit evidence
* [updateEvidence](docs/sdks/audits/README.md#updateevidence) - Update audit evidence
* [createCustomEvidenceRequest](docs/sdks/audits/README.md#createcustomevidencerequest) - Create a custom evidence request for an audit
* [createCustomControl](docs/sdks/audits/README.md#createcustomcontrol) - Create a custom control for an audit


</details>
<!-- End Available Resources and Operations [operations] -->

<!-- Start Error Handling [errors] -->
## Error Handling

Handling errors in this SDK should largely match your expectations. All operations return a response object or raise an exception.

By default, an API error will throw a `models/errors/APIException` exception. When custom error responses are specified for an operation, the SDK may also throw their associated exception. You can refer to respective *Errors* tables in SDK docs for more details on possible exception types for each operation. For example, the `list` method throws the following exceptions:

| Error Type                 | Status Code | Content Type |
| -------------------------- | ----------- | ------------ |
| models/errors/APIException | 4XX, 5XX    | \*/\*        |

### Example

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditsResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        ListAuditsResponse res = sdk.audits().list()
                .pageSize(10)
                .pageCursor("<value>")
                .changedSinceDate(OffsetDateTime.parse("2025-04-22T08:39:55.981Z"))
                .call();

        if (res.paginatedResponseAudit().isPresent()) {
            // handle response
        }
    }
}
```
<!-- End Error Handling [errors] -->

<!-- Start Server Selection [server] -->
## Server Selection

### Override Server URL Per-Client

The default server can also be overridden globally using the `.serverURL(String serverUrl)` builder method when initializing the SDK client instance. For example:
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.operations.ListAuditsResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .serverURL("https://api.vanta.com/v1")
                .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
            .build();

        ListAuditsResponse res = sdk.audits().list()
                .pageSize(10)
                .pageCursor("<value>")
                .changedSinceDate(OffsetDateTime.parse("2025-04-22T08:39:55.981Z"))
                .call();

        if (res.paginatedResponseAudit().isPresent()) {
            // handle response
        }
    }
}
```
<!-- End Server Selection [server] -->

<!-- Placeholder for Future Speakeasy SDK Sections -->

# Development

## Maturity

This SDK is in beta, and there may be breaking changes between versions without a major version update. Therefore, we recommend pinning usage
to a specific package version. This way, you can install the same version each time without breaking changes unless you are intentionally
looking for the latest version.

## Contributions

While we value open-source contributions to this SDK, this library is generated programmatically. Any manual changes added to internal files will be overwritten on the next generation. 
We look forward to hearing your feedback. Feel free to open a PR or an issue with a proof of concept and we'll do our best to include it in a future release. 

### SDK Created by [Speakeasy](https://www.speakeasy.com/?utm_source=openapi&utm_campaign=java)
# vanta-auditor-api-sdk-java
