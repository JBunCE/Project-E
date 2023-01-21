package com.ecomerce.projecte.controllers.dtos.request;

import io.micrometer.common.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreatePromotionRequest {

    @NonNull
    private String title;
    
    @NonNull
    private String description;

    @NonNull
    private Integer price;
}
