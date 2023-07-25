package com.app.SpringSucurityJWT.controllers.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedUserDTO {
    @Email
    @NotBlank
    @Size(max = 20)
    private String email;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    private String password;
    private Set<String> roles;
}
