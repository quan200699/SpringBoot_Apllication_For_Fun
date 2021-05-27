package com.example.startup.repository;

import com.example.startup.model.entity.Debtor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDebtorRepository extends JpaRepository<Debtor, Long> {
}
