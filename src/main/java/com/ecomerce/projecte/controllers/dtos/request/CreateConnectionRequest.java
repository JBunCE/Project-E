package com.ecomerce.projecte.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateConnectionRequest {
    private String name;
    private String urlConnection;
    private Long idProvider;
}
