/* 
 * Code generated by Speakeasy (https://speakeasy.com). DO NOT EDIT.
 */
package com.vanta.vanta_auditor_api.models.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vanta.vanta_auditor_api.utils.Utils;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

public class PaginatedResponseComment {

    @JsonProperty("results")
    private PaginatedResponseCommentResults results;

    @JsonCreator
    public PaginatedResponseComment(
            @JsonProperty("results") PaginatedResponseCommentResults results) {
        Utils.checkNotNull(results, "results");
        this.results = results;
    }

    @JsonIgnore
    public PaginatedResponseCommentResults results() {
        return results;
    }

    public final static Builder builder() {
        return new Builder();
    }    

    public PaginatedResponseComment withResults(PaginatedResponseCommentResults results) {
        Utils.checkNotNull(results, "results");
        this.results = results;
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
        PaginatedResponseComment other = (PaginatedResponseComment) o;
        return 
            Objects.deepEquals(this.results, other.results);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
            results);
    }
    
    @Override
    public String toString() {
        return Utils.toString(PaginatedResponseComment.class,
                "results", results);
    }
    
    public final static class Builder {
 
        private PaginatedResponseCommentResults results;
        
        private Builder() {
          // force use of static builder() method
        }

        public Builder results(PaginatedResponseCommentResults results) {
            Utils.checkNotNull(results, "results");
            this.results = results;
            return this;
        }
        
        public PaginatedResponseComment build() {
            return new PaginatedResponseComment(
                results);
        }
    }
}
