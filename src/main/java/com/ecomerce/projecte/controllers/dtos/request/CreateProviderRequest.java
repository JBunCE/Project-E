package com.ecomerce.projecte.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateProviderRequest {
    private Long phoneNumber;
    private String tier;
    private String userEmail;
}
