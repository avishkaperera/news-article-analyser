package com.reprisk.analyser.service;

import com.reprisk.analyser.model.Company;
import com.reprisk.analyser.model.NewsArticle;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface NewsArticleService {

    /**
     * This method will list all the names of the news article files present in the given directory
     *
     * @return List of news article file names
     * @throws IOException Throws exception if an error occurs in listing the XML file names
     */
    List<String> listNewsArticleNames() throws IOException;

    /**
     * This method will take in the list of companies and a batch of news articles and
     * proceed to analyse the news article text for company names
     *
     * @param companies    List of companies
     * @param newsArticles List of news articles loaded from files
     * @return Void
     */
    CompletableFuture<Void> startParse(List<Company> companies, List<NewsArticle> newsArticles);

    /**
     * This method will take a batch of news article file names and load the files' content to
     * the memory by deserializing them from XML to NewsArticle type
     *
     * @param newsArticleNames List of news article file names
     * @return List of news articles
     */
    CompletableFuture<List<NewsArticle>> readFromXml(List<String> newsArticleNames);
}
