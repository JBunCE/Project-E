package com.ecomerce.projecte.controllers.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class GetProviderResponse {
    private Long numberPhone;
    private String tier;
    private GetUserResponse user;
}