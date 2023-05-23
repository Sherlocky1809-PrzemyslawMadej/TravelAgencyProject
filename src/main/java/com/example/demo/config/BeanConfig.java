package com.example.demo.config;

import com.example.demo.enums.TripType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BeanConfig {

    @Bean
    public ModelMapper createModelMapperInstance() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, TripType> toTripType = new AbstractConverter<String, TripType>() {
            @Override
            protected TripType convert(String s) {
                try {
                    return TripType.valueOf(s.toUpperCase());
                } catch (IllegalArgumentException e) {
                    return null;
                }
        }
        };

        modelMapper.addConverter(toTripType);

        return modelMapper;
    }
}
