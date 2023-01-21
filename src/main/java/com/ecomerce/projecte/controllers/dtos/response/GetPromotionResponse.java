package com.ecomerce.projecte.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class GetPromotionResponse {

    private Long id;

    private String title;

    private String description;

    private Integer price;

}
