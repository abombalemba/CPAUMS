package com.cpaums.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UpdateType {
    MANDATORY,
    OPTIONAL,
    DEPRECATED;

    @JsonCreator
    public static UpdateType fromString(String value) {
        if (value == null) return null;
        try {
            return UpdateType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    @JsonValue
    public String toValue() {
        return this.name().toLowerCase();
    }
}