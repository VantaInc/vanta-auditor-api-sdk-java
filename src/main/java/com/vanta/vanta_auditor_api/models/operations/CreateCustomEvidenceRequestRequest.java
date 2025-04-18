/* 
 * Code generated by Speakeasy (https://speakeasy.com). DO NOT EDIT.
 */
package com.vanta.vanta_auditor_api.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vanta.vanta_auditor_api.models.components.CreateCustomEvidenceRequestInput;
import com.vanta.vanta_auditor_api.utils.SpeakeasyMetadata;
import com.vanta.vanta_auditor_api.utils.Utils;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

public class CreateCustomEvidenceRequestRequest {

    @SpeakeasyMetadata("pathParam:style=simple,explode=false,name=auditId")
    private String auditId;

    @SpeakeasyMetadata("request:mediaType=application/json")
    private CreateCustomEvidenceRequestInput createCustomEvidenceRequestInput;

    @JsonCreator
    public CreateCustomEvidenceRequestRequest(
            String auditId,
            CreateCustomEvidenceRequestInput createCustomEvidenceRequestInput) {
        Utils.checkNotNull(auditId, "auditId");
        Utils.checkNotNull(createCustomEvidenceRequestInput, "createCustomEvidenceRequestInput");
        this.auditId = auditId;
        this.createCustomEvidenceRequestInput = createCustomEvidenceRequestInput;
    }

    @JsonIgnore
    public String auditId() {
        return auditId;
    }

    @JsonIgnore
    public CreateCustomEvidenceRequestInput createCustomEvidenceRequestInput() {
        return createCustomEvidenceRequestInput;
    }

    public final static Builder builder() {
        return new Builder();
    }    

    public CreateCustomEvidenceRequestRequest withAuditId(String auditId) {
        Utils.checkNotNull(auditId, "auditId");
        this.auditId = auditId;
        return this;
    }

    public CreateCustomEvidenceRequestRequest withCreateCustomEvidenceRequestInput(CreateCustomEvidenceRequestInput createCustomEvidenceRequestInput) {
        Utils.checkNotNull(createCustomEvidenceRequestInput, "createCustomEvidenceRequestInput");
        this.createCustomEvidenceRequestInput = createCustomEvidenceRequestInput;
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
        CreateCustomEvidenceRequestRequest other = (CreateCustomEvidenceRequestRequest) o;
        return 
            Objects.deepEquals(this.auditId, other.auditId) &&
            Objects.deepEquals(this.createCustomEvidenceRequestInput, other.createCustomEvidenceRequestInput);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
            auditId,
            createCustomEvidenceRequestInput);
    }
    
    @Override
    public String toString() {
        return Utils.toString(CreateCustomEvidenceRequestRequest.class,
                "auditId", auditId,
                "createCustomEvidenceRequestInput", createCustomEvidenceRequestInput);
    }
    
    public final static class Builder {
 
        private String auditId;
 
        private CreateCustomEvidenceRequestInput createCustomEvidenceRequestInput;
        
        private Builder() {
          // force use of static builder() method
        }

        public Builder auditId(String auditId) {
            Utils.checkNotNull(auditId, "auditId");
            this.auditId = auditId;
            return this;
        }

        public Builder createCustomEvidenceRequestInput(CreateCustomEvidenceRequestInput createCustomEvidenceRequestInput) {
            Utils.checkNotNull(createCustomEvidenceRequestInput, "createCustomEvidenceRequestInput");
            this.createCustomEvidenceRequestInput = createCustomEvidenceRequestInput;
            return this;
        }
        
        public CreateCustomEvidenceRequestRequest build() {
            return new CreateCustomEvidenceRequestRequest(
                auditId,
                createCustomEvidenceRequestInput);
        }
    }
}
