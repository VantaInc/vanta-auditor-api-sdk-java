/* 
 * Code generated by Speakeasy (https://speakeasy.com). DO NOT EDIT.
 */

package com.vanta.vanta_auditor_api.models.components;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum AuditEvidenceType {
    EVIDENCE_REQUEST("Evidence Request"),
    POLICY("Policy"),
    TEST("Test");

    @JsonValue
    private final String value;

    private AuditEvidenceType(String value) {
        this.value = value;
    }
    
    public String value() {
        return value;
    }
    
    public static Optional<AuditEvidenceType> fromValue(String value) {
        for (AuditEvidenceType o: AuditEvidenceType.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
