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
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Optional;


public class ListAuditCommentsRequest {

    @SpeakeasyMetadata("pathParam:style=simple,explode=false,name=auditId")
    private String auditId;

    @SpeakeasyMetadata("queryParam:style=form,explode=true,name=pageSize")
    private Optional<Integer> pageSize;

    @SpeakeasyMetadata("queryParam:style=form,explode=true,name=pageCursor")
    private Optional<String> pageCursor;

    /**
     * Includes all comments that have changed since changedSinceDate.
     */
    @SpeakeasyMetadata("queryParam:style=form,explode=true,name=changedSinceDate")
    private Optional<OffsetDateTime> changedSinceDate;

    @JsonCreator
    public ListAuditCommentsRequest(
            String auditId,
            Optional<Integer> pageSize,
            Optional<String> pageCursor,
            Optional<OffsetDateTime> changedSinceDate) {
        Utils.checkNotNull(auditId, "auditId");
        Utils.checkNotNull(pageSize, "pageSize");
        Utils.checkNotNull(pageCursor, "pageCursor");
        Utils.checkNotNull(changedSinceDate, "changedSinceDate");
        this.auditId = auditId;
        this.pageSize = pageSize;
        this.pageCursor = pageCursor;
        this.changedSinceDate = changedSinceDate;
    }
    
    public ListAuditCommentsRequest(
            String auditId) {
        this(auditId, Optional.empty(), Optional.empty(), Optional.empty());
    }

    @JsonIgnore
    public String auditId() {
        return auditId;
    }

    @JsonIgnore
    public Optional<Integer> pageSize() {
        return pageSize;
    }

    @JsonIgnore
    public Optional<String> pageCursor() {
        return pageCursor;
    }

    /**
     * Includes all comments that have changed since changedSinceDate.
     */
    @JsonIgnore
    public Optional<OffsetDateTime> changedSinceDate() {
        return changedSinceDate;
    }

    public final static Builder builder() {
        return new Builder();
    }

    public ListAuditCommentsRequest withAuditId(String auditId) {
        Utils.checkNotNull(auditId, "auditId");
        this.auditId = auditId;
        return this;
    }

    public ListAuditCommentsRequest withPageSize(int pageSize) {
        Utils.checkNotNull(pageSize, "pageSize");
        this.pageSize = Optional.ofNullable(pageSize);
        return this;
    }

    public ListAuditCommentsRequest withPageSize(Optional<Integer> pageSize) {
        Utils.checkNotNull(pageSize, "pageSize");
        this.pageSize = pageSize;
        return this;
    }

    public ListAuditCommentsRequest withPageCursor(String pageCursor) {
        Utils.checkNotNull(pageCursor, "pageCursor");
        this.pageCursor = Optional.ofNullable(pageCursor);
        return this;
    }

    public ListAuditCommentsRequest withPageCursor(Optional<String> pageCursor) {
        Utils.checkNotNull(pageCursor, "pageCursor");
        this.pageCursor = pageCursor;
        return this;
    }

    /**
     * Includes all comments that have changed since changedSinceDate.
     */
    public ListAuditCommentsRequest withChangedSinceDate(OffsetDateTime changedSinceDate) {
        Utils.checkNotNull(changedSinceDate, "changedSinceDate");
        this.changedSinceDate = Optional.ofNullable(changedSinceDate);
        return this;
    }

    /**
     * Includes all comments that have changed since changedSinceDate.
     */
    public ListAuditCommentsRequest withChangedSinceDate(Optional<OffsetDateTime> changedSinceDate) {
        Utils.checkNotNull(changedSinceDate, "changedSinceDate");
        this.changedSinceDate = changedSinceDate;
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
        ListAuditCommentsRequest other = (ListAuditCommentsRequest) o;
        return 
            Objects.deepEquals(this.auditId, other.auditId) &&
            Objects.deepEquals(this.pageSize, other.pageSize) &&
            Objects.deepEquals(this.pageCursor, other.pageCursor) &&
            Objects.deepEquals(this.changedSinceDate, other.changedSinceDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
            auditId,
            pageSize,
            pageCursor,
            changedSinceDate);
    }
    
    @Override
    public String toString() {
        return Utils.toString(ListAuditCommentsRequest.class,
                "auditId", auditId,
                "pageSize", pageSize,
                "pageCursor", pageCursor,
                "changedSinceDate", changedSinceDate);
    }
    
    public final static class Builder {
 
        private String auditId;
 
        private Optional<Integer> pageSize;
 
        private Optional<String> pageCursor = Optional.empty();
 
        private Optional<OffsetDateTime> changedSinceDate = Optional.empty();  
        
        private Builder() {
          // force use of static builder() method
        }

        public Builder auditId(String auditId) {
            Utils.checkNotNull(auditId, "auditId");
            this.auditId = auditId;
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

        /**
         * Includes all comments that have changed since changedSinceDate.
         */
        public Builder changedSinceDate(OffsetDateTime changedSinceDate) {
            Utils.checkNotNull(changedSinceDate, "changedSinceDate");
            this.changedSinceDate = Optional.ofNullable(changedSinceDate);
            return this;
        }

        /**
         * Includes all comments that have changed since changedSinceDate.
         */
        public Builder changedSinceDate(Optional<OffsetDateTime> changedSinceDate) {
            Utils.checkNotNull(changedSinceDate, "changedSinceDate");
            this.changedSinceDate = changedSinceDate;
            return this;
        }
        
        public ListAuditCommentsRequest build() {
            if (pageSize == null) {
                pageSize = _SINGLETON_VALUE_PageSize.value();
            }            return new ListAuditCommentsRequest(
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
}

