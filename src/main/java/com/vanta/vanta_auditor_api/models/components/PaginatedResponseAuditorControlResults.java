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
import java.util.List;
import java.util.Objects;

public class PaginatedResponseAuditorControlResults {

    @JsonProperty("data")
    private List<AuditorControl> data;

    /**
     * Provides information about the pagination of a dataset.
     */
    @JsonProperty("pageInfo")
    private PageInfo pageInfo;

    @JsonCreator
    public PaginatedResponseAuditorControlResults(
            @JsonProperty("data") List<AuditorControl> data,
            @JsonProperty("pageInfo") PageInfo pageInfo) {
        Utils.checkNotNull(data, "data");
        Utils.checkNotNull(pageInfo, "pageInfo");
        this.data = data;
        this.pageInfo = pageInfo;
    }

    @JsonIgnore
    public List<AuditorControl> data() {
        return data;
    }

    /**
     * Provides information about the pagination of a dataset.
     */
    @JsonIgnore
    public PageInfo pageInfo() {
        return pageInfo;
    }

    public final static Builder builder() {
        return new Builder();
    }    

    public PaginatedResponseAuditorControlResults withData(List<AuditorControl> data) {
        Utils.checkNotNull(data, "data");
        this.data = data;
        return this;
    }

    /**
     * Provides information about the pagination of a dataset.
     */
    public PaginatedResponseAuditorControlResults withPageInfo(PageInfo pageInfo) {
        Utils.checkNotNull(pageInfo, "pageInfo");
        this.pageInfo = pageInfo;
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
        PaginatedResponseAuditorControlResults other = (PaginatedResponseAuditorControlResults) o;
        return 
            Objects.deepEquals(this.data, other.data) &&
            Objects.deepEquals(this.pageInfo, other.pageInfo);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
            data,
            pageInfo);
    }
    
    @Override
    public String toString() {
        return Utils.toString(PaginatedResponseAuditorControlResults.class,
                "data", data,
                "pageInfo", pageInfo);
    }
    
    public final static class Builder {
 
        private List<AuditorControl> data;
 
        private PageInfo pageInfo;
        
        private Builder() {
          // force use of static builder() method
        }

        public Builder data(List<AuditorControl> data) {
            Utils.checkNotNull(data, "data");
            this.data = data;
            return this;
        }

        /**
         * Provides information about the pagination of a dataset.
         */
        public Builder pageInfo(PageInfo pageInfo) {
            Utils.checkNotNull(pageInfo, "pageInfo");
            this.pageInfo = pageInfo;
            return this;
        }
        
        public PaginatedResponseAuditorControlResults build() {
            return new PaginatedResponseAuditorControlResults(
                data,
                pageInfo);
        }
    }
}
