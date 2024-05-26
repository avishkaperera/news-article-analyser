package com.reprisk.analyser.model;

import lombok.Builder;
import lombok.Getter;

@Builder(setterPrefix = "with")
@Getter
public class CompanyFoundEvent {
    private int companyId;
    private String companyName;
}
