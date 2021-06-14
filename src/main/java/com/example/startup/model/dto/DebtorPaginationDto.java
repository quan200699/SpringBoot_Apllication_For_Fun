package com.example.startup.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebtorPaginationDto {
    private List<DebtorDto> debtorDtos;

    private long length;

    private long totalPage;
}
