package com.ecomerce.projecte.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UpdateTagRequest {
    
    private String name;

    
    private String description;

    
    private String iconUrl;

    
    private String color;
}
