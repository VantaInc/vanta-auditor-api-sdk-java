/* 
 * Code generated by Speakeasy (https://speakeasy.com). DO NOT EDIT.
 */
package com.vanta.vanta_auditor_api.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vanta.vanta_auditor_api.models.components.PaginatedResponseComment;
import com.vanta.vanta_auditor_api.utils.Response;
import com.vanta.vanta_auditor_api.utils.Utils;
import java.io.InputStream;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Optional;

public class ListAuditCommentsResponse implements Response {

    /**
     * HTTP response content type for this operation
     */
    private String contentType;

    /**
     * HTTP response status code for this operation
     */
    private int statusCode;

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    private HttpResponse<InputStream> rawResponse;

    /**
     * Ok
     */
    private Optional<? extends PaginatedResponseComment> paginatedResponseComment;

    @JsonCreator
    public ListAuditCommentsResponse(
            String contentType,
            int statusCode,
            HttpResponse<InputStream> rawResponse,
            Optional<? extends PaginatedResponseComment> paginatedResponseComment) {
        Utils.checkNotNull(contentType, "contentType");
        Utils.checkNotNull(statusCode, "statusCode");
        Utils.checkNotNull(rawResponse, "rawResponse");
        Utils.checkNotNull(paginatedResponseComment, "paginatedResponseComment");
        this.contentType = contentType;
        this.statusCode = statusCode;
        this.rawResponse = rawResponse;
        this.paginatedResponseComment = paginatedResponseComment;
    }
    
    public ListAuditCommentsResponse(
            String contentType,
            int statusCode,
            HttpResponse<InputStream> rawResponse) {
        this(contentType, statusCode, rawResponse, Optional.empty());
    }

    /**
     * HTTP response content type for this operation
     */
    @JsonIgnore
    public String contentType() {
        return contentType;
    }

    /**
     * HTTP response status code for this operation
     */
    @JsonIgnore
    public int statusCode() {
        return statusCode;
    }

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    @JsonIgnore
    public HttpResponse<InputStream> rawResponse() {
        return rawResponse;
    }

    /**
     * Ok
     */
    @SuppressWarnings("unchecked")
    @JsonIgnore
    public Optional<PaginatedResponseComment> paginatedResponseComment() {
        return (Optional<PaginatedResponseComment>) paginatedResponseComment;
    }

    public final static Builder builder() {
        return new Builder();
    }    

    /**
     * HTTP response content type for this operation
     */
    public ListAuditCommentsResponse withContentType(String contentType) {
        Utils.checkNotNull(contentType, "contentType");
        this.contentType = contentType;
        return this;
    }

    /**
     * HTTP response status code for this operation
     */
    public ListAuditCommentsResponse withStatusCode(int statusCode) {
        Utils.checkNotNull(statusCode, "statusCode");
        this.statusCode = statusCode;
        return this;
    }

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    public ListAuditCommentsResponse withRawResponse(HttpResponse<InputStream> rawResponse) {
        Utils.checkNotNull(rawResponse, "rawResponse");
        this.rawResponse = rawResponse;
        return this;
    }

    /**
     * Ok
     */
    public ListAuditCommentsResponse withPaginatedResponseComment(PaginatedResponseComment paginatedResponseComment) {
        Utils.checkNotNull(paginatedResponseComment, "paginatedResponseComment");
        this.paginatedResponseComment = Optional.ofNullable(paginatedResponseComment);
        return this;
    }

    /**
     * Ok
     */
    public ListAuditCommentsResponse withPaginatedResponseComment(Optional<? extends PaginatedResponseComment> paginatedResponseComment) {
        Utils.checkNotNull(paginatedResponseComment, "paginatedResponseComment");
        this.paginatedResponseComment = paginatedResponseComment;
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
        ListAuditCommentsResponse other = (ListAuditCommentsResponse) o;
        return 
            Objects.deepEquals(this.contentType, other.contentType) &&
            Objects.deepEquals(this.statusCode, other.statusCode) &&
            Objects.deepEquals(this.rawResponse, other.rawResponse) &&
            Objects.deepEquals(this.paginatedResponseComment, other.paginatedResponseComment);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
            contentType,
            statusCode,
            rawResponse,
            paginatedResponseComment);
    }
    
    @Override
    public String toString() {
        return Utils.toString(ListAuditCommentsResponse.class,
                "contentType", contentType,
                "statusCode", statusCode,
                "rawResponse", rawResponse,
                "paginatedResponseComment", paginatedResponseComment);
    }
    
    public final static class Builder {
 
        private String contentType;
 
        private Integer statusCode;
 
        private HttpResponse<InputStream> rawResponse;
 
        private Optional<? extends PaginatedResponseComment> paginatedResponseComment = Optional.empty();
        
        private Builder() {
          // force use of static builder() method
        }

        /**
         * HTTP response content type for this operation
         */
        public Builder contentType(String contentType) {
            Utils.checkNotNull(contentType, "contentType");
            this.contentType = contentType;
            return this;
        }

        /**
         * HTTP response status code for this operation
         */
        public Builder statusCode(int statusCode) {
            Utils.checkNotNull(statusCode, "statusCode");
            this.statusCode = statusCode;
            return this;
        }

        /**
         * Raw HTTP response; suitable for custom response parsing
         */
        public Builder rawResponse(HttpResponse<InputStream> rawResponse) {
            Utils.checkNotNull(rawResponse, "rawResponse");
            this.rawResponse = rawResponse;
            return this;
        }

        /**
         * Ok
         */
        public Builder paginatedResponseComment(PaginatedResponseComment paginatedResponseComment) {
            Utils.checkNotNull(paginatedResponseComment, "paginatedResponseComment");
            this.paginatedResponseComment = Optional.ofNullable(paginatedResponseComment);
            return this;
        }

        /**
         * Ok
         */
        public Builder paginatedResponseComment(Optional<? extends PaginatedResponseComment> paginatedResponseComment) {
            Utils.checkNotNull(paginatedResponseComment, "paginatedResponseComment");
            this.paginatedResponseComment = paginatedResponseComment;
            return this;
        }
        
        public ListAuditCommentsResponse build() {
            return new ListAuditCommentsResponse(
                contentType,
                statusCode,
                rawResponse,
                paginatedResponseComment);
        }
    }
}
