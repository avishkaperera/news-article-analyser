package com.reprisk.analyser.service;

import com.reprisk.analyser.model.Company;
import com.reprisk.analyser.model.CompanyFoundEvent;
import com.reprisk.analyser.persistence.CompanyEntity;

import java.util.List;

public interface CompanyService {

    List<Company> loadCompanies();

    List<CompanyEntity> getAllIdentifiedCompanies();

    void processIdentifiedCompanies(CompanyFoundEvent event);
}
