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

public class PaginatedResponseAuditorControl {

    @JsonProperty("results")
    private PaginatedResponseAuditorControlResults results;

    @JsonCreator
    public PaginatedResponseAuditorControl(
            @JsonProperty("results") PaginatedResponseAuditorControlResults results) {
        Utils.checkNotNull(results, "results");
        this.results = results;
    }

    @JsonIgnore
    public PaginatedResponseAuditorControlResults results() {
        return results;
    }

    public final static Builder builder() {
        return new Builder();
    }    

    public PaginatedResponseAuditorControl withResults(PaginatedResponseAuditorControlResults results) {
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
        PaginatedResponseAuditorControl other = (PaginatedResponseAuditorControl) o;
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
        return Utils.toString(PaginatedResponseAuditorControl.class,
                "results", results);
    }
    
    public final static class Builder {
 
        private PaginatedResponseAuditorControlResults results;
        
        private Builder() {
          // force use of static builder() method
        }

        public Builder results(PaginatedResponseAuditorControlResults results) {
            Utils.checkNotNull(results, "results");
            this.results = results;
            return this;
        }
        
        public PaginatedResponseAuditorControl build() {
            return new PaginatedResponseAuditorControl(
                results);
        }
    }
}
