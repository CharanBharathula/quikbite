package com.quikbite.app.auth_users.dtos;

import java.util.List;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private List<String> roles;
}
