package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BeanConfig {

    @Bean
    public ModelMapper createModelMapperInstance() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
