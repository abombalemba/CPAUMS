package com.cpaums.config;

import com.cpaums.model.Platform;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StringToPlatformConverter implements Converter<String, Platform> {
    @Override
    public Platform convert(@NonNull String source) {
        try {
            return Platform.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}