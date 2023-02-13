package com.ecomerce.projecte.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateConnectionRequest {
    private String name;
    private String urlConnection;
}
