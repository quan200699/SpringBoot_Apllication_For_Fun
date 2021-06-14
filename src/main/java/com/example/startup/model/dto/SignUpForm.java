package com.example.startup.model.dto;

import com.example.startup.exception.UniqueUsername;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignUpForm {
    @UniqueUsername
    @NotNull
    @Size(min = 6, max = 20)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 30)
    private String password;

    @NotNull
    @Size(min = 6, max = 30)
    private String rePassword;
}
