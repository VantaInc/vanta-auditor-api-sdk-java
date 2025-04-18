/* 
 * Code generated by Speakeasy (https://speakeasy.com). DO NOT EDIT.
 */
package com.vanta.vanta_auditor_api.models.operations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vanta.vanta_auditor_api.utils.LazySingletonValue;
import com.vanta.vanta_auditor_api.utils.Utils;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.Optional;

public class ListAuditCommentsRequestBuilder {

    private String auditId;
    private Optional<Integer> pageSize = Utils.readDefaultOrConstValue(
                            "pageSize",
                            "10",
                            new TypeReference<Optional<Integer>>() {});
    private Optional<String> pageCursor = Optional.empty();
    private Optional<OffsetDateTime> changedSinceDate = Optional.empty();
    private final SDKMethodInterfaces.MethodCallListAuditComments sdk;

    public ListAuditCommentsRequestBuilder(SDKMethodInterfaces.MethodCallListAuditComments sdk) {
        this.sdk = sdk;
    }

    public ListAuditCommentsRequestBuilder auditId(String auditId) {
        Utils.checkNotNull(auditId, "auditId");
        this.auditId = auditId;
        return this;
    }
                
    public ListAuditCommentsRequestBuilder pageSize(int pageSize) {
        Utils.checkNotNull(pageSize, "pageSize");
        this.pageSize = Optional.of(pageSize);
        return this;
    }

    public ListAuditCommentsRequestBuilder pageSize(Optional<Integer> pageSize) {
        Utils.checkNotNull(pageSize, "pageSize");
        this.pageSize = pageSize;
        return this;
    }
                
    public ListAuditCommentsRequestBuilder pageCursor(String pageCursor) {
        Utils.checkNotNull(pageCursor, "pageCursor");
        this.pageCursor = Optional.of(pageCursor);
        return this;
    }

    public ListAuditCommentsRequestBuilder pageCursor(Optional<String> pageCursor) {
        Utils.checkNotNull(pageCursor, "pageCursor");
        this.pageCursor = pageCursor;
        return this;
    }
                
    public ListAuditCommentsRequestBuilder changedSinceDate(OffsetDateTime changedSinceDate) {
        Utils.checkNotNull(changedSinceDate, "changedSinceDate");
        this.changedSinceDate = Optional.of(changedSinceDate);
        return this;
    }

    public ListAuditCommentsRequestBuilder changedSinceDate(Optional<OffsetDateTime> changedSinceDate) {
        Utils.checkNotNull(changedSinceDate, "changedSinceDate");
        this.changedSinceDate = changedSinceDate;
        return this;
    }

    public ListAuditCommentsResponse call() throws Exception {
        if (pageSize == null) {
            pageSize = _SINGLETON_VALUE_PageSize.value();
        }
        return sdk.listComments(
            auditId,
            pageSize,
            pageCursor,
            changedSinceDate);
    }

    private static final LazySingletonValue<Optional<Integer>> _SINGLETON_VALUE_PageSize =
            new LazySingletonValue<>(
                    "pageSize",
                    "10",
                    new TypeReference<Optional<Integer>>() {});
}
