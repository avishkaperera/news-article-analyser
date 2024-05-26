package com.reprisk.analyser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableConfigurationProperties(value = ExecutorConfig.ExecutorProperties.class)
@EnableAsync
public class ExecutorConfig {

    @Value("${analyser.init.core-thread-pool-size}")
    private Integer coreThreadPoolSize;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreThreadPoolSize);
        executor.setThreadNamePrefix("analyser-");
        return executor;
    }

    @ConfigurationProperties(prefix = "analyser.init")
    public record ExecutorProperties(
            int batchSize
    ) {
    }
}
