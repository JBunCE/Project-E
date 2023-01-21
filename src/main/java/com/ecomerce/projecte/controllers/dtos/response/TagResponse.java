package com.ecomerce.projecte.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TagResponse {
    private Long id;

    private String name;

    private String description;

    private String iconUrl;

    private String color;
}
