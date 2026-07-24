# openapi

Developer-friendly & type-safe Java SDK specifically catered to leverage *openapi* API.

<div align="left">
    <a href="https://www.speakeasy.com/?utm_source=openapi&utm_campaign=java"><img src="https://custom-icon-badges.demolab.com/badge/-Built%20By%20Speakeasy-212015?style=for-the-badge&logoColor=FBE331&logo=speakeasy&labelColor=545454" /></a>
    <a href="https://mit-license.org/">
        <img src="https://img.shields.io/badge/License-MIT-blue.svg" style="width: 100px; height: 28px;" />
    </a>
</div>

<!-- Start Summary [summary] -->
## Summary

Conduct an audit: The Auditor API lets audit firms conduct audits from a tool outside of Vanta. Unlock data syncing with Vanta through this API.

**Note for Vanta Gov (FedRAMP) customers:** Select `Vanta Gov (FedRAMP)` from the server dropdown to issue requests against `https://api.vanta-gov.com`. The OAuth token URL shown below defaults to the commercial host — replace it with `https://api.vanta-gov.com/oauth/token`.
<!-- End Summary [summary] -->

<!-- Start Table of Contents [toc] -->
## Table of Contents
<!-- $toc-max-depth=2 -->
* [openapi](#openapi)
  * [SDK Installation](#sdk-installation)
  * [SDK Example Usage](#sdk-example-usage)
  * [Asynchronous Support](#asynchronous-support)
  * [Authentication](#authentication)
  * [Available Resources and Operations](#available-resources-and-operations)
  * [Error Handling](#error-handling)
  * [Server Selection](#server-selection)
  * [Custom HTTP Client](#custom-http-client)
  * [Debugging](#debugging)
  * [Jackson Configuration](#jackson-configuration)
* [Development](#development)
  * [Maturity](#maturity)
  * [Contributions](#contributions)
* [vanta-auditor-api-sdk-java](#vanta-auditor-api-sdk-java)

<!-- End Table of Contents [toc] -->

<!-- Start SDK Installation [installation] -->
## SDK Installation

### Getting started

JDK 11 or later is required.

The samples below show how a published SDK artifact is used:

Gradle:
```groovy
implementation 'com.vanta:vanta-auditor-api:0.5.20'
```

Maven:
```xml
<dependency>
    <groupId>com.vanta</groupId>
    <artifactId>vanta-auditor-api</artifactId>
    <version>0.5.20</version>
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
import com.vanta.vanta_auditor_api.models.components.AddAuditorInput;
import com.vanta.vanta_auditor_api.models.operations.CreateAuditorResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        AddAuditorInput req = AddAuditorInput.builder()
                .email("Genesis_Kunze87@yahoo.com")
                .givenName("<value>")
                .familyName("<value>")
                .build();

        CreateAuditorResponse res = sdk.auditors().create()
                .request(req)
                .call();

        if (res.auditor().isPresent()) {
            System.out.println(res.auditor().get());
        }
    }
}
```
#### Asynchronous Call
An asynchronous SDK client is also available that returns a [`CompletableFuture<T>`][comp-fut]. See [Asynchronous Support](#asynchronous-support) for more details on async benefits and reactive library integration.
```java
package hello.world;

import com.vanta.vanta_auditor_api.AsyncVanta;
import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.AddAuditorInput;
import com.vanta.vanta_auditor_api.models.operations.async.CreateAuditorResponse;
import java.util.concurrent.CompletableFuture;

public class Application {

    public static void main(String[] args) {

        AsyncVanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build()
            .async();

        AddAuditorInput req = AddAuditorInput.builder()
                .email("Genesis_Kunze87@yahoo.com")
                .givenName("<value>")
                .familyName("<value>")
                .build();

        CompletableFuture<CreateAuditorResponse> resFut = sdk.auditors().create()
                .request(req)
                .call();

        resFut.thenAccept(res -> {
            if (res.auditor().isPresent()) {
                System.out.println(res.auditor().get());
            }
        });
    }
}
```

[comp-fut]: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html

#### Union Consumption Patterns

When a response field is a union model:

- Discriminated unions: branch on the discriminator (`switch`) and then narrow to the concrete type.
- Non-discriminated unions: use generated accessors (for example `string()`, `asLong()`, `simpleObject()`) to determine the active variant.

For full model-specific examples (including Java 11/16/21 variants), see each union model's **Supported Types** section in the generated model docs.
<!-- End SDK Example Usage [usage] -->

<!-- Start Asynchronous Support [async-support] -->
## Asynchronous Support

The SDK provides comprehensive asynchronous support using Java's [`CompletableFuture<T>`][comp-fut] and [Reactive Streams `Publisher<T>`][reactive-streams] APIs. This design makes no assumptions about your choice of reactive toolkit, allowing seamless integration with any reactive library.

<details>
<summary>Why Use Async?</summary>

Asynchronous operations provide several key benefits:

- **Non-blocking I/O**: Your threads stay free for other work while operations are in flight
- **Better resource utilization**: Handle more concurrent operations with fewer threads
- **Improved scalability**: Build highly responsive applications that can handle thousands of concurrent requests
- **Reactive integration**: Works seamlessly with reactive streams and backpressure handling

</details>

<details>
<summary>Reactive Library Integration</summary>

The SDK returns [Reactive Streams `Publisher<T>`][reactive-streams] instances for operations dealing with streams involving multiple I/O interactions. We use Reactive Streams instead of JDK Flow API to provide broader compatibility with the reactive ecosystem, as most reactive libraries natively support Reactive Streams.

**Why Reactive Streams over JDK Flow?**
- **Broader ecosystem compatibility**: Most reactive libraries (Project Reactor, RxJava, Akka Streams, etc.) natively support Reactive Streams
- **Industry standard**: Reactive Streams is the de facto standard for reactive programming in Java
- **Better interoperability**: Seamless integration without additional adapters for most use cases

**Integration with Popular Libraries:**
- **Project Reactor**: Use `Flux.from(publisher)` to convert to Reactor types
- **RxJava**: Use `Flowable.fromPublisher(publisher)` for RxJava integration
- **Akka Streams**: Use `Source.fromPublisher(publisher)` for Akka Streams integration
- **Vert.x**: Use `ReadStream.fromPublisher(vertx, publisher)` for Vert.x reactive streams
- **Mutiny**: Use `Multi.createFrom().publisher(publisher)` for Quarkus Mutiny integration

**For JDK Flow API Integration:**
If you need JDK Flow API compatibility (e.g., for Quarkus/Mutiny 2), you can use adapters:
```java
// Convert Reactive Streams Publisher to Flow Publisher
Flow.Publisher<T> flowPublisher = FlowAdapters.toFlowPublisher(reactiveStreamsPublisher);

// Convert Flow Publisher to Reactive Streams Publisher
Publisher<T> reactiveStreamsPublisher = FlowAdapters.toPublisher(flowPublisher);
```

For standard single-response operations, the SDK returns `CompletableFuture<T>` for straightforward async execution.

</details>

<details>
<summary>Supported Operations</summary>

Async support is available for:

- **[Server-sent Events](#server-sent-event-streaming)**: Stream real-time events with Reactive Streams `Publisher<T>`
- **[JSONL Streaming](#jsonl-streaming)**: Process streaming JSON lines asynchronously
- **[Pagination](#pagination)**: Iterate through paginated results using `callAsPublisher()` and `callAsPublisherUnwrapped()`
- **[File Uploads](#file-uploads)**: Upload files asynchronously with progress tracking
- **[File Downloads](#file-downloads)**: Download files asynchronously with streaming support
- **[Standard Operations](#example)**: All regular API calls return `CompletableFuture<T>` for async execution

</details>

[comp-fut]: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
[reactive-streams]: https://www.reactive-streams.org/
<!-- End Asynchronous Support [async-support] -->

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
import com.vanta.vanta_auditor_api.models.components.AddAuditorInput;
import com.vanta.vanta_auditor_api.models.operations.CreateAuditorResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        AddAuditorInput req = AddAuditorInput.builder()
                .email("Genesis_Kunze87@yahoo.com")
                .givenName("<value>")
                .familyName("<value>")
                .build();

        CreateAuditorResponse res = sdk.auditors().create()
                .request(req)
                .call();

        if (res.auditor().isPresent()) {
            System.out.println(res.auditor().get());
        }
    }
}
```
<!-- End Authentication [security] -->

<!-- Start Available Resources and Operations [operations] -->
## Available Resources and Operations

<details open>
<summary>Available methods</summary>

### [Auditors](docs/sdks/auditors/README.md)

* [create](docs/sdks/auditors/README.md#create) - Create an auditor

### [Audits](docs/sdks/audits/README.md)

* [list](docs/sdks/audits/README.md#list) - List audits
* [duplicate](docs/sdks/audits/README.md#duplicate) - Duplicate an IRL audit
* [getAudit](docs/sdks/audits/README.md#getaudit) - Get audit by ID
* [listCodeChanges](docs/sdks/audits/README.md#listcodechanges) - List code changes for an audit
* [listComments](docs/sdks/audits/README.md#listcomments) - List audit comments
* [listControls](docs/sdks/audits/README.md#listcontrols) - List audit controls
* [createCustomControl](docs/sdks/audits/README.md#createcustomcontrol) - Create a custom control for an audit
* [listCommentsForControl](docs/sdks/audits/README.md#listcommentsforcontrol) - List comments for a control within an audit
* [createCommentForControl](docs/sdks/audits/README.md#createcommentforcontrol) - Create a comment for a control within an audit
* [updateCommentForControl](docs/sdks/audits/README.md#updatecommentforcontrol) - Update a comment for a control within an audit
* [deleteCommentForControl](docs/sdks/audits/README.md#deletecommentforcontrol) - Delete a comment for a control within an audit
* [listInformationRequestsForControl](docs/sdks/audits/README.md#listinformationrequestsforcontrol) - List information requests linked to a control within an audit
* [listEvidence](docs/sdks/audits/README.md#listevidence) - List audit evidence
* [createCustomEvidenceRequest](docs/sdks/audits/README.md#createcustomevidencerequest) - Create a custom evidence request for an audit
* [updateEvidence](docs/sdks/audits/README.md#updateevidence) - Update audit evidence
* [createCommentForEvidence](docs/sdks/audits/README.md#createcommentforevidence) - Create a comment for audit evidence
* [getEvidenceUrls](docs/sdks/audits/README.md#getevidenceurls) - List audit evidence url
* [getFrameworkCodes](docs/sdks/audits/README.md#getframeworkcodes) - Get framework codes for an audit
* [listInformationRequests](docs/sdks/audits/README.md#listinformationrequests) - List information requests for an audit
* [createInformationRequest](docs/sdks/audits/README.md#createinformationrequest) - Create a new information request
* [getInformationRequest](docs/sdks/audits/README.md#getinformationrequest) - Get an information request by ID
* [updateInformationRequest](docs/sdks/audits/README.md#updateinformationrequest) - Update an information request for an audit
* [deleteInformationRequest](docs/sdks/audits/README.md#deleteinformationrequest) - Delete an information request for an audit
* [acceptInformationRequestEvidence](docs/sdks/audits/README.md#acceptinformationrequestevidence) - Accept evidence for an information request
* [listInformationRequestActivity](docs/sdks/audits/README.md#listinformationrequestactivity) - List information request activity
* [listCommentsForInformationRequest](docs/sdks/audits/README.md#listcommentsforinformationrequest) - List comments for an information request
* [createCommentForInformationRequest](docs/sdks/audits/README.md#createcommentforinformationrequest) - Create a comment for an information request
* [updateCommentForInformationRequest](docs/sdks/audits/README.md#updatecommentforinformationrequest) - Update a comment for an information request
* [deleteCommentForInformationRequest](docs/sdks/audits/README.md#deletecommentforinformationrequest) - Delete a comment for an information request
* [listInformationRequestEvidence](docs/sdks/audits/README.md#listinformationrequestevidence) - List evidence for an information request
* [getInformationRequestTestSnapshotEvidenceDetail](docs/sdks/audits/README.md#getinformationrequesttestsnapshotevidencedetail) - Get test snapshot detail for an evidence row
* [flagInformationRequestEvidence](docs/sdks/audits/README.md#flaginformationrequestevidence) - Flag evidence for an information request
* [listIntegrations](docs/sdks/audits/README.md#listintegrations) - List integrations for an audit
* [listAuditIssues](docs/sdks/audits/README.md#listauditissues) - List snapshotted issues for an audit
* [listAuditSnapshots](docs/sdks/audits/README.md#listauditsnapshots) - List snapshotted issues for an audit
* [listVendors](docs/sdks/audits/README.md#listvendors) - List vendors for an audit
* [~~listMonitoredComputersInAuditScope~~](docs/sdks/audits/README.md#listmonitoredcomputersinauditscope) - List monitored computers :warning: **Deprecated**
* [getOrganizationInformation](docs/sdks/audits/README.md#getorganizationinformation) - Get organization information for an audit
* [getOrganizationNotifications](docs/sdks/audits/README.md#getorganizationnotifications) - Get organization notification settings for an audit
* [~~listPeopleInAuditScope~~](docs/sdks/audits/README.md#listpeopleinauditscope) - List of people who are in scope for this audit :warning: **Deprecated**
* [listAccountAccessServices](docs/sdks/audits/README.md#listaccountaccessservices) - List account access services for an audit
* [listPersonnelAccountAccess](docs/sdks/audits/README.md#listpersonnelaccountaccess) - List account access records for an audit
* [listPersonnelGroups](docs/sdks/audits/README.md#listpersonnelgroups) - List groups for an audit
* [listPersonnelPeople](docs/sdks/audits/README.md#listpersonnelpeople) - List people for an audit
* [listRiskSnapshots](docs/sdks/audits/README.md#listrisksnapshots) - List risk snapshots for an audit
* [listAuditRisks](docs/sdks/audits/README.md#listauditrisks) - List risks for an audit
* [shareInformationRequestList](docs/sdks/audits/README.md#shareinformationrequestlist) - Share information request list with customer
* [~~listVendorsInAuditScope~~](docs/sdks/audits/README.md#listvendorsinauditscope) - List of vendors who are in scope for this audit :warning: **Deprecated**
* [~~listVulnerabilities~~](docs/sdks/audits/README.md#listvulnerabilities) - List vulnerabilities within the scope of a given audit :warning: **Deprecated**
* [~~listVulnerabilityRemediationsInAuditScope~~](docs/sdks/audits/README.md#listvulnerabilityremediationsinauditscope) - List vulnerability remediations that are in scope for this audit :warning: **Deprecated**
* [~~getVulnerableAssets~~](docs/sdks/audits/README.md#getvulnerableassets) - List assets associated with vulnerabilities :warning: **Deprecated**

</details>
<!-- End Available Resources and Operations [operations] -->

<!-- Start Error Handling [errors] -->
## Error Handling

Handling errors in this SDK should largely match your expectations. All operations return a response object or raise an exception.


[`VantaError`](./src/main/java/models/errors/VantaError.java) is the base class for all HTTP error responses. It has the following properties:

| Method           | Type                        | Description                                                              |
| ---------------- | --------------------------- | ------------------------------------------------------------------------ |
| `message()`      | `String`                    | Error message                                                            |
| `code()`         | `int`                       | HTTP response status code eg `404`                                       |
| `headers`        | `Map<String, List<String>>` | HTTP response headers                                                    |
| `body()`         | `byte[]`                    | HTTP body as a byte array. Can be empty array if no body is returned.    |
| `bodyAsString()` | `String`                    | HTTP body as a UTF-8 string. Can be empty string if no body is returned. |
| `rawResponse()`  | `HttpResponse<?>`           | Raw HTTP response (body already read and not available for re-read)      |

### Example
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.AddAuditorInput;
import com.vanta.vanta_auditor_api.models.errors.VantaError;
import com.vanta.vanta_auditor_api.models.operations.CreateAuditorResponse;
import java.io.UncheckedIOException;
import java.lang.Exception;
import java.util.Optional;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();
        try {

            AddAuditorInput req = AddAuditorInput.builder()
                    .email("Genesis_Kunze87@yahoo.com")
                    .givenName("<value>")
                    .familyName("<value>")
                    .build();

            CreateAuditorResponse res = sdk.auditors().create()
                    .request(req)
                    .call();

            if (res.auditor().isPresent()) {
                System.out.println(res.auditor().get());
            }
        } catch (VantaError ex) { // all SDK exceptions inherit from VantaError

            // ex.ToString() provides a detailed error message including
            // HTTP status code, headers, and error payload (if any)
            System.out.println(ex);

            // Base exception fields
            var rawResponse = ex.rawResponse();
            var headers = ex.headers();
            var contentType = headers.first("Content-Type");
            int statusCode = ex.code();
            Optional<byte[]> responseBody = ex.body();
        } catch (UncheckedIOException ex) {
            // handle IO error (connection, timeout, etc)
        }    }
}
```

### Error Classes
**Primary error:**
* [`VantaError`](./src/main/java/models/errors/VantaError.java): The base class for HTTP error responses.

<details><summary>Less common errors (6)</summary>

<br />

**Network errors:**
* `java.io.IOException` (always wrapped by `java.io.UncheckedIOException`). Commonly encountered subclasses of
`IOException` include `java.net.ConnectException`, `java.net.SocketTimeoutException`, `EOFException` (there are
many more subclasses in the JDK platform).

**Inherit from [`VantaError`](./src/main/java/models/errors/VantaError.java)**:


</details>
<!-- End Error Handling [errors] -->

<!-- Start Server Selection [server] -->
## Server Selection

### Select Server by Index

You can override the default server globally using the `.serverIndex(int serverIdx)` builder method when initializing the SDK client instance. The selected server will then be used as the default on the operations that use it. This table lists the indexes associated with the available servers:

| #   | Server                         | Description         |
| --- | ------------------------------ | ------------------- |
| 0   | `https://api.vanta.com/v1`     | Vanta (Commercial)  |
| 1   | `https://api.vanta-gov.com/v1` | Vanta Gov (FedRAMP) |

#### Example

```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.AddAuditorInput;
import com.vanta.vanta_auditor_api.models.operations.CreateAuditorResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .serverIndex(0)
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        AddAuditorInput req = AddAuditorInput.builder()
                .email("Genesis_Kunze87@yahoo.com")
                .givenName("<value>")
                .familyName("<value>")
                .build();

        CreateAuditorResponse res = sdk.auditors().create()
                .request(req)
                .call();

        if (res.auditor().isPresent()) {
            System.out.println(res.auditor().get());
        }
    }
}
```

### Override Server URL Per-Client

The default server can also be overridden globally using the `.serverURL(String serverUrl)` builder method when initializing the SDK client instance. For example:
```java
package hello.world;

import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.models.components.AddAuditorInput;
import com.vanta.vanta_auditor_api.models.operations.CreateAuditorResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

        Vanta sdk = Vanta.builder()
                .serverURL("https://api.vanta-gov.com/v1")
                .bearerAuth(System.getenv().getOrDefault("BEARER_AUTH", ""))
            .build();

        AddAuditorInput req = AddAuditorInput.builder()
                .email("Genesis_Kunze87@yahoo.com")
                .givenName("<value>")
                .familyName("<value>")
                .build();

        CreateAuditorResponse res = sdk.auditors().create()
                .request(req)
                .call();

        if (res.auditor().isPresent()) {
            System.out.println(res.auditor().get());
        }
    }
}
```
<!-- End Server Selection [server] -->

<!-- Start Custom HTTP Client [http-client] -->
## Custom HTTP Client

The Java SDK makes API calls using an `HTTPClient` that wraps the native
[HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html). This
client provides the ability to attach hooks around the request lifecycle that can be used to modify the request or handle
errors and response.

The `HTTPClient` interface allows you to either use the default `SpeakeasyHTTPClient` that comes with the SDK,
or provide your own custom implementation with customized configuration such as custom executors, SSL context,
connection pools, and other HTTP client settings.

The interface provides synchronous (`send`) methods and asynchronous (`sendAsync`) methods. The `sendAsync` method
is used to power the async SDK methods and returns a `CompletableFuture<HttpResponse<Blob>>` for non-blocking operations.

The following example shows how to add a custom header and handle errors:

```java
import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.utils.HTTPClient;
import com.vanta.vanta_auditor_api.utils.SpeakeasyHTTPClient;
import com.vanta.vanta_auditor_api.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.InputStream;
import java.time.Duration;

public class Application {
    public static void main(String[] args) {
        // Create a custom HTTP client with hooks
        HTTPClient httpClient = new HTTPClient() {
            private final HTTPClient defaultClient = new SpeakeasyHTTPClient();
            
            @Override
            public HttpResponse<InputStream> send(HttpRequest request) throws IOException, URISyntaxException, InterruptedException {
                // Add custom header and timeout using Utils.copy()
                HttpRequest modifiedRequest = Utils.copy(request)
                    .header("x-custom-header", "custom value")
                    .timeout(Duration.ofSeconds(30))
                    .build();
                    
                try {
                    HttpResponse<InputStream> response = defaultClient.send(modifiedRequest);
                    // Log successful response
                    System.out.println("Request successful: " + response.statusCode());
                    return response;
                } catch (Exception error) {
                    // Log error
                    System.err.println("Request failed: " + error.getMessage());
                    throw error;
                }
            }
        };

        Vanta sdk = Vanta.builder()
            .client(httpClient)
            .build();
    }
}
```

<details>
<summary>Custom HTTP Client Configuration</summary>

You can also provide a completely custom HTTP client with your own configuration:

```java
import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.utils.HTTPClient;
import com.vanta.vanta_auditor_api.utils.Blob;
import com.vanta.vanta_auditor_api.utils.ResponseWithBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.InputStream;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.CompletableFuture;

public class Application {
    public static void main(String[] args) {
        // Custom HTTP client with custom configuration
        HTTPClient customHttpClient = new HTTPClient() {
            private final HttpClient client = HttpClient.newBuilder()
                .executor(Executors.newFixedThreadPool(10))
                .connectTimeout(Duration.ofSeconds(30))
                // .sslContext(customSslContext) // Add custom SSL context if needed
                .build();

            @Override
            public HttpResponse<InputStream> send(HttpRequest request) throws IOException, URISyntaxException, InterruptedException {
                return client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            }

            @Override
            public CompletableFuture<HttpResponse<Blob>> sendAsync(HttpRequest request) {
                // Convert response to HttpResponse<Blob> for async operations
                return client.sendAsync(request, HttpResponse.BodyHandlers.ofPublisher())
                    .thenApply(resp -> new ResponseWithBody<>(resp, Blob::from));
            }
        };

        Vanta sdk = Vanta.builder()
            .client(customHttpClient)
            .build();
    }
}
```

</details>

You can also enable debug logging on the default `SpeakeasyHTTPClient`:

```java
import com.vanta.vanta_auditor_api.Vanta;
import com.vanta.vanta_auditor_api.utils.SpeakeasyHTTPClient;

public class Application {
    public static void main(String[] args) {
        SpeakeasyHTTPClient httpClient = new SpeakeasyHTTPClient();
        httpClient.enableDebugLogging(true);

        Vanta sdk = Vanta.builder()
            .client(httpClient)
            .build();
    }
}
```
<!-- End Custom HTTP Client [http-client] -->

<!-- Start Debugging [debug] -->
## Debugging

### Debug

You can setup your SDK to emit debug logs for SDK requests and responses.

For request and response logging (especially json bodies), call `enableHTTPDebugLogging(boolean)` on the SDK builder like so:

```java
SDK.builder()
    .enableHTTPDebugLogging(true)
    .build();
```
Example output:
```
Sending request: http://localhost:35123/bearer#global GET
Request headers: {Accept=[application/json], Authorization=[******], Client-Level-Header=[added by client], Idempotency-Key=[some-key], x-speakeasy-user-agent=[speakeasy-sdk/java 0.0.1 internal 0.1.0 org.openapis.openapi]}
Received response: (GET http://localhost:35123/bearer#global) 200
Response headers: {access-control-allow-credentials=[true], access-control-allow-origin=[*], connection=[keep-alive], content-length=[50], content-type=[application/json], date=[Wed, 09 Apr 2025 01:43:29 GMT], server=[gunicorn/19.9.0]}
Response body:
{
  "authenticated": true, 
  "token": "global"
}
```
__WARNING__: This logging should only be used for temporary debugging purposes. Leaving this option on in a production system could expose credentials/secrets in logs. <i>Authorization</i> headers are redacted by default and there is the ability to specify redacted header names via `SpeakeasyHTTPClient.setRedactedHeaders`.

__NOTE__: This is a convenience method that calls `HTTPClient.enableDebugLogging()`. The `SpeakeasyHTTPClient` honors this setting. If you are using a custom HTTP client, it is up to the custom client to honor this setting.


Another option is to set the System property `-Djdk.httpclient.HttpClient.log=all`. However, this second option does not log bodies.
<!-- End Debugging [debug] -->

<!-- Start Jackson Configuration [jackson] -->
## Jackson Configuration

The SDK ships with a pre-configured Jackson [`ObjectMapper`][jackson-databind] accessible via
`JSON.getMapper()`. It is set up with type modules, strict deserializers, and the feature flags
needed for full SDK compatibility (including ISO-8601 `OffsetDateTime` serialization):

```java
import com.vanta.vanta_auditor_api.utils.JSON;

String json = JSON.getMapper().writeValueAsString(response);
```

To compose with your own `ObjectMapper`, register the provided `VantaAuditorApiJacksonModule`, which
bundles all the same modules and feature flags as a single plug-and-play module:

```java
import com.vanta.vanta_auditor_api.utils.VantaAuditorApiJacksonModule;
import com.fasterxml.jackson.databind.ObjectMapper;

ObjectMapper myMapper = new ObjectMapper()
    .registerModule(new VantaAuditorApiJacksonModule());

String json = myMapper.writeValueAsString(response);
```

[jackson-databind]: https://github.com/FasterXML/jackson-databind
[jackson-jsr310]: https://github.com/FasterXML/jackson-modules-java8/tree/master/datetime
<!-- End Jackson Configuration [jackson] -->

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
