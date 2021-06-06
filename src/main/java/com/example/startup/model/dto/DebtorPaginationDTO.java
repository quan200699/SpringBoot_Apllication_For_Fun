package com.example.startup.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebtorPaginationDTO {
    private List<DebtorDto> debtorDtos;

    private int length;
}