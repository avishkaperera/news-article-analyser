package com.reprisk.analyser.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import static com.reprisk.analyser.util.TestData.aCompanyFoundEvent;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = CompanyServiceImpl.class)
class CompanyServiceImplIT {

    @Autowired
    private ApplicationEventPublisher publisher;

    @MockBean
    private CompanyServiceImpl companyService;

    @Test
    void testProcessIdentifiedCompanies() {
        publisher.publishEvent(aCompanyFoundEvent());
        verify(companyService, times(1)).processIdentifiedCompanies(any());
    }
}