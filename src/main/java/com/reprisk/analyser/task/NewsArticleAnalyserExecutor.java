package com.reprisk.analyser.task;

import com.reprisk.analyser.config.ExecutorConfig;
import com.reprisk.analyser.model.Company;
import com.reprisk.analyser.service.CompanyService;
import com.reprisk.analyser.service.NewsArticleService;
import com.reprisk.analyser.util.BatchUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Component
@RequiredArgsConstructor
@Slf4j
public class NewsArticleAnalyserExecutor {

    private final CompanyService companyService;
    private final NewsArticleService newsArticleService;
    private final ExecutorConfig.ExecutorProperties executorProperties;

    /*
    This could be improved to be called from an API endpoint, so it can be triggered manually
     */
    @EventListener(ApplicationReadyEvent.class)
    public void triggerAnalysis() throws IOException {
        log.info("Start analysing at {}", System.currentTimeMillis());
        List<Company> companies = companyService.loadCompanies();
        List<String> newsArticleNames = newsArticleService.listNewsArticleNames();

        try (ForkJoinPool forkJoinPool = new ForkJoinPool(4)) {
            forkJoinPool.submit(() ->
                    BatchUtil.stream(executorProperties.batchSize(), newsArticleNames.iterator())
                            .parallel()
                            .forEach(articleNames -> newsArticleService.readFromXml(articleNames)
                                    .thenAcceptAsync(articles -> newsArticleService.startParse(companies, articles))));
        }
    }
}
