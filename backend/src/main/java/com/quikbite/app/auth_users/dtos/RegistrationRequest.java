package com.quikbite.app.auth_users.dtos;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {
    
    @NotBlank( message = "Name cannot be blank" )
    private String name;

    @NotBlank( message = "Email cannot be blank" )
    @Email( message = "Invalid email format" )
    private String email;

    @NotBlank( message = "Password cannot be blank" )
    @Size( min = 4, message = "Password must be at least 4 characters long" )
    private String password;
    
    @NotBlank( message = "Phone number cannot be blank" )
    @Size( min = 10, max = 10, message = "Phone number must 10 characters long" )
    private String phoneNumber;

    @NotBlank( message = "Address cannot be blank" )
    private String address;

    private List<String> roles;
}
