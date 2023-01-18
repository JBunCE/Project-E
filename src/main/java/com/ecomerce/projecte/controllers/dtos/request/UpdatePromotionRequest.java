package com.ecomerce.projecte.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UpdatePromotionRequest {
    
    private String title;
    
    private String description;

    private Integer price;
}
