package com.example.startup.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdDate;

    private Date confirmedDate;

    private boolean status;

    @ManyToOne
    private Debtor debtor;

    private Long amount;
}