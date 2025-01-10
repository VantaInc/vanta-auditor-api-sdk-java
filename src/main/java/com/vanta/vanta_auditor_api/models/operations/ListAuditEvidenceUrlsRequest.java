/* 
 * Code generated by Speakeasy (https://speakeasy.com). DO NOT EDIT.
 */

package com.vanta.vanta_auditor_api.models.operations;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.vanta.vanta_auditor_api.utils.LazySingletonValue;
import com.vanta.vanta_auditor_api.utils.SpeakeasyMetadata;
import com.vanta.vanta_auditor_api.utils.Utils;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;


public class ListAuditEvidenceUrlsRequest {

    @SpeakeasyMetadata("pathParam:style=simple,explode=false,name=auditId")
    private String auditId;

    @SpeakeasyMetadata("pathParam:style=simple,explode=false,name=auditEvidenceId")
    private String auditEvidenceId;

    @SpeakeasyMetadata("queryParam:style=form,explode=true,name=pageSize")
    private Optional<Integer> pageSize;

    @SpeakeasyMetadata("queryParam:style=form,explode=true,name=pageCursor")
    private Optional<String> pageCursor;

    @JsonCreator
    public ListAuditEvidenceUrlsRequest(
            String auditId,
            String auditEvidenceId,
            Optional<Integer> pageSize,
            Optional<String> pageCursor) {
        Utils.checkNotNull(auditId, "auditId");
        Utils.checkNotNull(auditEvidenceId, "auditEvidenceId");
        Utils.checkNotNull(pageSize, "pageSize");
        Utils.checkNotNull(pageCursor, "pageCursor");
        this.auditId = auditId;
        this.auditEvidenceId = auditEvidenceId;
        this.pageSize = pageSize;
        this.pageCursor = pageCursor;
    }
    
    public ListAuditEvidenceUrlsRequest(
            String auditId,
            String auditEvidenceId) {
        this(auditId, auditEvidenceId, Optional.empty(), Optional.empty());
    }

    @JsonIgnore
    public String auditId() {
        return auditId;
    }

    @JsonIgnore
    public String auditEvidenceId() {
        return auditEvidenceId;
    }

    @JsonIgnore
    public Optional<Integer> pageSize() {
        return pageSize;
    }

    @JsonIgnore
    public Optional<String> pageCursor() {
        return pageCursor;
    }

    public final static Builder builder() {
        return new Builder();
    }

    public ListAuditEvidenceUrlsRequest withAuditId(String auditId) {
        Utils.checkNotNull(auditId, "auditId");
        this.auditId = auditId;
        return this;
    }

    public ListAuditEvidenceUrlsRequest withAuditEvidenceId(String auditEvidenceId) {
        Utils.checkNotNull(auditEvidenceId, "auditEvidenceId");
        this.auditEvidenceId = auditEvidenceId;
        return this;
    }

    public ListAuditEvidenceUrlsRequest withPageSize(int pageSize) {
        Utils.checkNotNull(pageSize, "pageSize");
        this.pageSize = Optional.ofNullable(pageSize);
        return this;
    }

    public ListAuditEvidenceUrlsRequest withPageSize(Optional<Integer> pageSize) {
        Utils.checkNotNull(pageSize, "pageSize");
        this.pageSize = pageSize;
        return this;
    }

    public ListAuditEvidenceUrlsRequest withPageCursor(String pageCursor) {
        Utils.checkNotNull(pageCursor, "pageCursor");
        this.pageCursor = Optional.ofNullable(pageCursor);
        return this;
    }

    public ListAuditEvidenceUrlsRequest withPageCursor(Optional<String> pageCursor) {
        Utils.checkNotNull(pageCursor, "pageCursor");
        this.pageCursor = pageCursor;
        return this;
    }
    
    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListAuditEvidenceUrlsRequest other = (ListAuditEvidenceUrlsRequest) o;
        return 
            Objects.deepEquals(this.auditId, other.auditId) &&
            Objects.deepEquals(this.auditEvidenceId, other.auditEvidenceId) &&
            Objects.deepEquals(this.pageSize, other.pageSize) &&
            Objects.deepEquals(this.pageCursor, other.pageCursor);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
            auditId,
            auditEvidenceId,
            pageSize,
            pageCursor);
    }
    
    @Override
    public String toString() {
        return Utils.toString(ListAuditEvidenceUrlsRequest.class,
                "auditId", auditId,
                "auditEvidenceId", auditEvidenceId,
                "pageSize", pageSize,
                "pageCursor", pageCursor);
    }
    
    public final static class Builder {
 
        private String auditId;
 
        private String auditEvidenceId;
 
        private Optional<Integer> pageSize;
 
        private Optional<String> pageCursor = Optional.empty();  
        
        private Builder() {
          // force use of static builder() method
        }

        public Builder auditId(String auditId) {
            Utils.checkNotNull(auditId, "auditId");
            this.auditId = auditId;
            return this;
        }

        public Builder auditEvidenceId(String auditEvidenceId) {
            Utils.checkNotNull(auditEvidenceId, "auditEvidenceId");
            this.auditEvidenceId = auditEvidenceId;
            return this;
        }

        public Builder pageSize(int pageSize) {
            Utils.checkNotNull(pageSize, "pageSize");
            this.pageSize = Optional.ofNullable(pageSize);
            return this;
        }

        public Builder pageSize(Optional<Integer> pageSize) {
            Utils.checkNotNull(pageSize, "pageSize");
            this.pageSize = pageSize;
            return this;
        }

        public Builder pageCursor(String pageCursor) {
            Utils.checkNotNull(pageCursor, "pageCursor");
            this.pageCursor = Optional.ofNullable(pageCursor);
            return this;
        }

        public Builder pageCursor(Optional<String> pageCursor) {
            Utils.checkNotNull(pageCursor, "pageCursor");
            this.pageCursor = pageCursor;
            return this;
        }
        
        public ListAuditEvidenceUrlsRequest build() {
            if (pageSize == null) {
                pageSize = _SINGLETON_VALUE_PageSize.value();
            }            return new ListAuditEvidenceUrlsRequest(
                auditId,
                auditEvidenceId,
                pageSize,
                pageCursor);
        }

        private static final LazySingletonValue<Optional<Integer>> _SINGLETON_VALUE_PageSize =
                new LazySingletonValue<>(
                        "pageSize",
                        "10",
                        new TypeReference<Optional<Integer>>() {});
    }
}

