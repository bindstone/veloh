package com.bindstone.veloh.mybatis.config;

import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JtsConfiguration {

    @Bean
    public JtsModule jtsModule() {
        // This module will provide a Serializer for geometries
        return new JtsModule();
    }
}