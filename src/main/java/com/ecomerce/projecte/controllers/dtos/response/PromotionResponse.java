package com.ecomerce.projecte.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PromotionResponse {
    private Long id;

    private String title;

    private String description;

    private Integer price;

}
