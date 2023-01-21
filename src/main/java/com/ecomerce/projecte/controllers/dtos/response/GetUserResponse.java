package com.ecomerce.projecte.controllers.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class GetUserResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String profilePicture;
}
