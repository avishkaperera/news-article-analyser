package com.reprisk.analyser.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = CsvConfig.CsvProperties.class)
public class CsvConfig {

    @ConfigurationProperties(prefix = "analyser.csv")
    public record CsvProperties(
            String delimiter
    ) {
    }
}
