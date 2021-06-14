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

    @Column(nullable = false)
    private Date createdDate;

    private Date confirmedDate;

    private boolean status;

    @ManyToOne
    @Column(nullable = false)
    private Debtor debtor;

    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long amount;
}
