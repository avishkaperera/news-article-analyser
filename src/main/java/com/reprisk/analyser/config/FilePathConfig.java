package com.reprisk.analyser.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = FilePathConfig.FilePathProperties.class)
public class FilePathConfig {

    @ConfigurationProperties(prefix = "analyser.path-to")
    public record FilePathProperties(
            String companyListCsvFile,
            String articleListDir
    ) {
    }
}
