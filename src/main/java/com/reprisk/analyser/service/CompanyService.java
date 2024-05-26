package com.reprisk.analyser.service;

import com.reprisk.analyser.model.Company;
import com.reprisk.analyser.model.CompanyEntity;
import com.reprisk.analyser.model.CompanyFoundEvent;

import java.io.IOException;
import java.util.List;

public interface CompanyService {

    /**
     * This method will read the CSV file and load the list f companies to the memory for processing
     *
     * @return List of Companies
     * @throws IOException Throws exception if an error occurs in reading the CSV file
     */
    List<Company> loadCompanies() throws IOException;

    /**
     * This method will fetch all the companies identified to be present in the news articles
     *
     * @return List of Company Entities
     */
    List<CompanyEntity> getAllIdentifiedCompanies();

    /**
     * This method will listen to CompanyFoundEvent emitted from the article analysing process and
     * proceed to store the identified company in the data store
     *
     * @param event CompanyFoundEvent
     */
    void processIdentifiedCompanies(CompanyFoundEvent event);
}
