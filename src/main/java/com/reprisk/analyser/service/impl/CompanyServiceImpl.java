package com.reprisk.analyser.service.impl;

import com.reprisk.analyser.config.CsvConfig;
import com.reprisk.analyser.config.FilePathConfig;
import com.reprisk.analyser.model.Company;
import com.reprisk.analyser.model.CompanyEntity;
import com.reprisk.analyser.model.CompanyFoundEvent;
import com.reprisk.analyser.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final FilePathConfig.FilePathProperties filePathProperties;
    private final Map<CompanyEntity, String> inMemoryDataStore;
    private final CsvConfig.CsvProperties csvProperties;

    @Override
    public List<Company> loadCompanies() throws IOException {
        log.info("Loading companies...");
        try (final BufferedReader csvReader = Files.newBufferedReader(Paths.get(filePathProperties.companyListCsvFile()))) {
            return csvReader
                    .lines()
                    .skip(1)
                    .parallel()
                    .map(line -> {
                        String[] idNamePair = line.split(csvProperties.delimiter());
                        return new Company(Integer.valueOf(idNamePair[0]), idNamePair[1]);
                    })
                    .toList();
        }
    }

    @Override
    public List<CompanyEntity> getAllIdentifiedCompanies() {
        return inMemoryDataStore.keySet().stream().toList();
    }

    @Override
    @EventListener
    @Async
    public void processIdentifiedCompanies(CompanyFoundEvent event) {
        CompanyEntity companyEntity = CompanyEntity.builder()
                .companyId(event.getCompanyId())
                .companyName(event.getCompanyName())
                .build();
        inMemoryDataStore.put(companyEntity, companyEntity.getCompanyName());
    }
}
