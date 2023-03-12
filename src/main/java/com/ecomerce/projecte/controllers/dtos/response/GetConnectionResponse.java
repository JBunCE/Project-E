package com.ecomerce.projecte.controllers.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class GetConnectionResponse {
    private Long id;
    private String name;
    private String urlConnection;
    private GetProviderResponse provider;
}
