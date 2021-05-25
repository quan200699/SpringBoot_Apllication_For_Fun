package com.example.startup.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebtorDto {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(max = 10)
    private String phone;

    @NotNull
    @Size(min = 6, max = 20)
    private String username;

    @NotNull
    @Size(min = 6, max = 30)
    private String password;

    private String hobbies;

    private String avatar;
}
