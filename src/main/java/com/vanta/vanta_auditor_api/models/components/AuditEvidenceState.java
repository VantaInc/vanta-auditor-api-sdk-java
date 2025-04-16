/* 
 * Code generated by Speakeasy (https://speakeasy.com). DO NOT EDIT.
 */
package com.vanta.vanta_auditor_api.models.components;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum AuditEvidenceState {
    ACCEPTED("Accepted"),
    FLAGGED("Flagged"),
    INITIALIZED("Initialized"),
    NA("NA"),
    NOT_READY_FOR_AUDIT("Not ready for audit"),
    READY_FOR_AUDIT("Ready for audit");

    @JsonValue
    private final String value;

    private AuditEvidenceState(String value) {
        this.value = value;
    }
    
    public String value() {
        return value;
    }
    
    public static Optional<AuditEvidenceState> fromValue(String value) {
        for (AuditEvidenceState o: AuditEvidenceState.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}

