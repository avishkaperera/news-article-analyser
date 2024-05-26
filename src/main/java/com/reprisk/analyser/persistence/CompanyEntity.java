package com.reprisk.analyser.persistence;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class CompanyEntity {

    private Integer companyId;
    private String companyName;
}
