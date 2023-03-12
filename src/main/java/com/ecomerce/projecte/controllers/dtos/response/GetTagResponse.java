package com.ecomerce.projecte.controllers.dtos.response;
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
