package com.ecomerce.projecte.controllers.dtos.request;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
}
