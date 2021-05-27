package com.example.startup.repository;

import com.example.startup.model.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDebtRepository extends JpaRepository<Debt, Long> {
}
