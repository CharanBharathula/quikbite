package com.quikbite.app.auth_users.dtos;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quikbite.app.role.dtos.RoleDto;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null fields during serialization
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields during deserialization
public class UserDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String profileUrl;

    // Write-only: Included when receiving data (deserialization) but excluded when sending data (serialization)
    // This is to ensure that the password is not exposed in API responses.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Password is write-only
    private String password;

    private boolean isActive;
    private String address;

    private List<RoleDto> roles;
    private MultipartFile profileImage;
}
