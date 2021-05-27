package com.example.startup.model.dto;

import com.example.startup.model.entity.Loyalty;
import com.example.startup.model.entity.User;
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
    @Size(max = 10)
    private String phone;

    private String hobbies;

    private String avatar;

    private User user;

    private Loyalty loyalty;
}
