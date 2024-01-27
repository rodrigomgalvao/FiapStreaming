package com.bezkoder.spring.r2dbc.h2.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorBean {
    @Bean
    Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
