package com.example.demo.config;

import com.example.demo.enums.TripType;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, TripType> {
    @Override
    public TripType convert(String source) {
        try {
            return TripType.valueOf(source.toUpperCase());
        } catch (Exception exception) {
            return null;
        }
    }
}
