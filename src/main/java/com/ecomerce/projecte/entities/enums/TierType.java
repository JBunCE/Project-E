package com.ecomerce.projecte.entities.enums;

import lombok.Getter;

@Getter
public enum TierType {
    FREE("free-tier"),
    PROVIDER("provider-tier"),
    BUSINESS("business-man-tier");

    private final String tierCode;

    TierType(String code){
        this.tierCode=code;
    }

}



