package com.example.startup.model.dto;

import com.example.startup.validator.annotated.PasswordConfirm;
import com.example.startup.validator.annotated.UniqueEmail;
import com.example.startup.validator.annotated.UniqueUsername;
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

    @UniqueEmail
    @NotNull
    @Email
    private String email;

    @PasswordConfirm
    private PasswordForm passwordForm;
}
