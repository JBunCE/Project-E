package com.ecomerce.projecte.security.user;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}
