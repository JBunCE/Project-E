package com.ecomerce.projecte.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserRequest {
    private String name;
    private String lastName;
    private String email;
}
