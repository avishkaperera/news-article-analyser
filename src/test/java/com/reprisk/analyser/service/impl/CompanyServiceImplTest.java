package com.reprisk.analyser.service.impl;

import com.reprisk.analyser.model.CompanyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static com.reprisk.analyser.util.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private Map<CompanyEntity, Object> inMemoryDataStore;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    void testGetAllIdentifiedCompanies() {
        doReturn(aSetOfCompanyEntities()).when(inMemoryDataStore).keySet();
        List<CompanyEntity> allIdentifiedCompanies = companyService.getAllIdentifiedCompanies();
        assertThat(allIdentifiedCompanies).hasSize(10);
    }
}