package com.ecomerce.projecte.controllers.dtos.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UpdateUserRequest {
    private String name;
    private String lastName;
    private String email;
    private String userType;
    private String profilePicture;
}
