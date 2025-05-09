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

public class PaginatedResponseAudit {

    @JsonProperty("results")
    private Results results;

    @JsonCreator
    public PaginatedResponseAudit(
            @JsonProperty("results") Results results) {
        Utils.checkNotNull(results, "results");
        this.results = results;
    }

    @JsonIgnore
    public Results results() {
        return results;
    }

    public final static Builder builder() {
        return new Builder();
    }    

    public PaginatedResponseAudit withResults(Results results) {
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
        PaginatedResponseAudit other = (PaginatedResponseAudit) o;
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
        return Utils.toString(PaginatedResponseAudit.class,
                "results", results);
    }
    
    public final static class Builder {
 
        private Results results;
        
        private Builder() {
          // force use of static builder() method
        }

        public Builder results(Results results) {
            Utils.checkNotNull(results, "results");
            this.results = results;
            return this;
        }
        
        public PaginatedResponseAudit build() {
            return new PaginatedResponseAudit(
                results);
        }
    }
}
