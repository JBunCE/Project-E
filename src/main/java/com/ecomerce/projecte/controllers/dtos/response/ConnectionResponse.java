package com.ecomerce.projecte.controllers.dtos.response;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class ConnectionResponse {
    private Long id;
    private String name;
    private String urlConnection;
    private GetProviderResponse provider;
}
