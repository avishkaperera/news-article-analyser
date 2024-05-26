package com.reprisk.analyser.config;

import com.reprisk.analyser.persistence.CompanyEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class AppConfig {

    @Bean
    public Map<CompanyEntity, Object> inMemoryDataStore() {
        return new ConcurrentHashMap<>();
    }
}
