/* 
 * Code generated by Speakeasy (https://speakeasy.com). DO NOT EDIT.
 */
package com.vanta.vanta_auditor_api.models.components;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vanta.vanta_auditor_api.utils.OneOfDeserializer;
import com.vanta.vanta_auditor_api.utils.TypedObject;
import com.vanta.vanta_auditor_api.utils.Utils.JsonShape;
import com.vanta.vanta_auditor_api.utils.Utils.TypeReferenceWithShape;
import com.vanta.vanta_auditor_api.utils.Utils;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(using = Value._Deserializer.class)
public class Value {

    @JsonValue
    private TypedObject value;
    
    private Value(TypedObject value) {
        this.value = value;
    }

    public static Value of(String value) {
        Utils.checkNotNull(value, "value");
        return new Value(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<String>(){}));
    }

    public static Value of(List<String> value) {
        Utils.checkNotNull(value, "value");
        return new Value(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<List<String>>(){}));
    }
    
    /**
     * Returns an instance of one of these types:
     * <ul>
     * <li>{@code java.lang.String}</li>
     * <li>{@code java.util.List<java.lang.String>}</li>
     * </ul>
     * 
     * <p>Use {@code instanceof} to determine what type is returned. For example:
     * 
     * <pre>
     * if (obj.value() instanceof String) {
     *     String answer = (String) obj.value();
     *     System.out.println("answer=" + answer);
     * }
     * </pre>
     * 
     * @return value of oneOf type
     **/ 
    public java.lang.Object value() {
        return value.value();
    }    
    
    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Value other = (Value) o;
        return Objects.deepEquals(this.value.value(), other.value.value()); 
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value.value());
    }
    
    @SuppressWarnings("serial")
    public static final class _Deserializer extends OneOfDeserializer<Value> {

        public _Deserializer() {
            super(Value.class, false,
                  TypeReferenceWithShape.of(new TypeReference<List<String>>() {}, JsonShape.DEFAULT),
                  TypeReferenceWithShape.of(new TypeReference<String>() {}, JsonShape.DEFAULT));
        }
    }
    
    @Override
    public String toString() {
        return Utils.toString(Value.class,
                "value", value);
    }
 
}

