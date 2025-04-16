/* 
 * Code generated by Speakeasy (https://speakeasy.com). DO NOT EDIT.
 */
package com.vanta.vanta_auditor_api.models.components;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum ControlSource {
    VANTA("Vanta"),
    CUSTOM("Custom");

    @JsonValue
    private final String value;

    private ControlSource(String value) {
        this.value = value;
    }
    
    public String value() {
        return value;
    }
    
    public static Optional<ControlSource> fromValue(String value) {
        for (ControlSource o: ControlSource.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}

