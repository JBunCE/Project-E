package com.ecomerce.projecte.controllers.dtos.response;

import com.ecomerce.projecte.entities.enums.ColorType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetTagResponse {
    private Long id;

    private String name;

    private String description;

    private String iconUrl;

    private String color;
}
