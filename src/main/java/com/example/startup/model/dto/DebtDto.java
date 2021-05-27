package com.example.startup.model.dto;

import com.example.startup.model.entity.Debtor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebtDto {
    @NotNull
    private Date createdDate;

    @NotNull
    private Debtor debtor;

    @NotNull
    private Long amount;
}
