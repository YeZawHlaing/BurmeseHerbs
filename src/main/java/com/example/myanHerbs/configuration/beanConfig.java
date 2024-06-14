package com.example.myanHerbs.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class beanConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
