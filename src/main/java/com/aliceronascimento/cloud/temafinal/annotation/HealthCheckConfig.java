package com.aliceronascimento.cloud.temafinal.annotation;

import com.aliceronascimento.cloud.temafinal.controller.HealthCheckResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthCheckConfig {

    @Bean
    public HealthCheckResource healthCheckHandler() {
        return new HealthCheckResource();
    }
}