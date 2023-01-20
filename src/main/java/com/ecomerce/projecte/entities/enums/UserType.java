package com.ecomerce.projecte.entities.enums;

import lombok.Getter;

@Getter
public enum UserType {
    COMMON_USER("common"),
    PROVIDER_USER("provider");

    private final String type;

    UserType(String code){
        this.type = code;
    }
}
