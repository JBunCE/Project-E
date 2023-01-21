package com.ecomerce.projecte.controllers.dtos.request;

import io.micrometer.common.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateTagRequest {
    
    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private String iconUrl;

    @NonNull
    private String color;
}
