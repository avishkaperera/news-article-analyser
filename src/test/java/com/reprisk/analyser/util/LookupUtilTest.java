package com.reprisk.analyser.util;

import com.reprisk.analyser.model.Company;
import com.reprisk.analyser.model.NewsArticle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.reprisk.analyser.util.TestData.aListOfCompanies;
import static com.reprisk.analyser.util.TestData.aListOfNewsArticles;
import static org.assertj.core.api.Assertions.assertThat;

class LookupUtilTest {

    @Test
    void testIsCompanyPresentInArticle() {
        List<Company> companies = aListOfCompanies();
        List<NewsArticle> newsArticles = aListOfNewsArticles();
        List<String> identifiedCompanies = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles) {
            for (Company company : companies) {
                if(LookupUtil.isCompanyPresentInArticle(newsArticle.getText(), company.getCompanyName())){
                    identifiedCompanies.add(company.getCompanyName());
                }
            }
        }
        assertThat(identifiedCompanies).hasSize(4);
        assertThat(identifiedCompanies.getFirst()).isEqualTo("Associated Ventures International Ltd");
        assertThat(identifiedCompanies.get(1)).isEqualTo("Clearview Investment Ltd");
        assertThat(identifiedCompanies.get(2)).isEqualTo("Devas USA");
        assertThat(identifiedCompanies.getLast()).isEqualTo("Kainos Group Plc");
    }
}