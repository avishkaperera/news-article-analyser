package com.reprisk.analyser.service.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.reprisk.analyser.config.FilePathConfig;
import com.reprisk.analyser.model.Company;
import com.reprisk.analyser.model.CompanyFoundEvent;
import com.reprisk.analyser.model.NewsArticle;
import com.reprisk.analyser.service.NewsArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsArticleServiceImpl implements NewsArticleService {

    public static final String DIRECTORY_SEPARATOR = "/";
    private static final String COMPANY_NAME_MATCHER_REGEX = ".*(?<!\\w)%s(?!\\w).*";

    private final FilePathConfig.FilePathProperties filePathProperties;
    private final XmlMapper xmlMapper;
    private final ApplicationEventPublisher publisher;

    @Override
    public List<String> listNewsArticleNames() throws IOException {
        log.info("Loading news article names...");
        try (final Stream<Path> newsArticleFileNames = Files.list(Paths.get(filePathProperties.articleListDir()))) {
            return newsArticleFileNames
                    .filter(file -> !Files.isDirectory(file))
                    .map(file -> file.getFileName().toString())
                    .toList();
        }
    }

    @Override
    public CompletableFuture<List<NewsArticle>> readFromXml(List<String> newsArticleNames) {
        return CompletableFuture.completedFuture(newsArticleNames.stream().map(article -> {
            try {
                return xmlMapper.readValue(
                        new File(filePathProperties.articleListDir() + DIRECTORY_SEPARATOR + article),
                        NewsArticle.class);
            } catch (Exception e) {
                log.error("Could not read/ parse news article xml file [{}] - [{}]", article, e.getMessage());
                return null;
            }
        }).toList());
    }

    @Override
    @Async
    public CompletableFuture<Void> startParse(List<Company> companies, List<NewsArticle> newsArticles) {
        for (NewsArticle article : newsArticles) {
            for (Company company : companies) {
                analyseNewsArticleText(article, company);
            }
        }
        log.info("Processing done for batch at [{}]", System.currentTimeMillis());
        return CompletableFuture.completedFuture(null);
    }

    private void analyseNewsArticleText(NewsArticle newsArticle, Company company) {
        if (Objects.nonNull(newsArticle) && isCompanyPresentInArticle(newsArticle.getText(), company.getCompanyName())) {
            publisher.publishEvent(
                    CompanyFoundEvent.builder()
                            .withCompanyId(company.getCompanyId())
                            .withCompanyName(company.getCompanyName())
                            .build());
        }

    }

    private boolean isCompanyPresentInArticle(String newsArticleText, String companyName) {
        if (!newsArticleText.contains(companyName)) {
            return false;
        }

        String sanitizedName = companyName.contains("(") ? companyName
                .replace("(", "\\(")
                .replace(")", "\\)") : companyName;
        String formattedRegex = String.format(COMPANY_NAME_MATCHER_REGEX, sanitizedName);
        Pattern companyNamePattern = Pattern.compile(formattedRegex, Pattern.DOTALL);
        Matcher companyNameMatcher = companyNamePattern.matcher(newsArticleText);
        return companyNameMatcher.find();
    }
}
