package com.agorohov.skyprocw2examservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
@ComponentScan(basePackages = "com.agorohov.skyprocw2examservice")
public class Config {

    @Bean
    public Random random() {
        return new Random();
    }
}
