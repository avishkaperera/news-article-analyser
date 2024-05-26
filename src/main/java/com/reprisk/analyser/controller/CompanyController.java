package com.reprisk.analyser.controller;

import com.reprisk.analyser.model.CompanyEntity;
import com.reprisk.analyser.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/companies", produces = "application/json")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyEntity>> getAllIdentifiedCompanies() {
        return ResponseEntity.ok(companyService.getAllIdentifiedCompanies());
    }
}
