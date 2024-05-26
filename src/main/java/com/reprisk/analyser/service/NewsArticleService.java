package com.reprisk.analyser.service;

import com.reprisk.analyser.model.Company;
import com.reprisk.analyser.model.NewsArticle;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface NewsArticleService {

    List<String> listNewsArticleNames();

    CompletableFuture<Void> startParse(List<Company> companies, List<NewsArticle> newsArticle);

    CompletableFuture<List<NewsArticle>> readFromXmls(List<String> newsArticleName);
}
