package com.cpaums.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Platform {
    ANDROID,
    WINDOWS,
    LINUX,
    IOS,
    MACOS,
    WEB;

    @JsonCreator
    public static Platform fromString(String value) {
        if (value == null) {
            return null;
        }

        try {
            return Platform.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unknown platform: " + value);
        }
    }

    @JsonValue
    public String toValue() {
        return this.name().toLowerCase();
    }
}