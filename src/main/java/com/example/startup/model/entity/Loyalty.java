package com.example.startup.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Loyalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
