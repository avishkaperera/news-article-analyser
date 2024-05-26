package com.reprisk.analyser.util;

import com.reprisk.analyser.model.Company;
import com.reprisk.analyser.model.CompanyEntity;
import com.reprisk.analyser.model.CompanyFoundEvent;
import com.reprisk.analyser.model.NewsArticle;
import lombok.experimental.UtilityClass;
import org.instancio.Instancio;

import java.util.List;
import java.util.Set;

@UtilityClass
public class TestData {

    public static Set<CompanyEntity> aSetOfCompanyEntities() {
        return Instancio.ofSet(CompanyEntity.class).size(10).create();
    }

    public static CompanyFoundEvent aCompanyFoundEvent() {
        return CompanyFoundEvent.builder().withCompanyId(100).withCompanyName("ABC PLC").build();
    }

    public static List<NewsArticle> aListOfNewsArticles(){
        return List.of(
                new NewsArticle(
                        "100",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                                "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                                "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                                "Associated Ventures International Ltd." +
                                "remaining essentially unchanged. It was popularised in the 1960s with the release of " +
                                "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing " +
                                "software like Aldus PageMaker including versions of Lorem Ipsum."
                ),
                new NewsArticle(
                        "101",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                                "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                                "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                                "Clearview Investment Ltd dummy text dummy text Devas USA." +
                                "remaining essentially unchanged. It was popularised in the 1960s with the release of " +
                                "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing " +
                                "software like Aldus PageMaker including versions of Lorem Ipsum."
                ),
                new NewsArticle(
                        "101",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                                "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                                "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                                "eToro (Europe) dummy text dummy Kainos Group Plc." +
                                "remaining essentially unchanged. It was popularised in the 1960s with the release of " +
                                "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing " +
                                "software like Aldus PageMaker including versions of Lorem Ipsum."
                )
        );
    }

    public static List<Company> aListOfCompanies(){
        return List.of(
                new Company(
                        100,
                        "Sherwood Ltd"
                ),
                new Company(
                        101,
                        "Deutsche Handelsbank"
                ),
                new Company(
                        102,
                        "Associated Ventures International Ltd"
                ),
                new Company(
                        103,
                        "Clearview Investment Ltd"
                ),
                new Company(
                        104,
                        "Devas USA"
                ),
                new Company(
                        105,
                        "Kainos Group Plc"
                ),
                new Company(
                        105,
                        "eToro (Europe) Ltd"
                )
        );
    }
}
