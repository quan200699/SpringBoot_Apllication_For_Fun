package com.example.startup.repository;

import com.example.startup.model.entity.Debtor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDebtorRepository extends JpaRepository<Debtor, Long> {
    Optional<Debtor> findByUsername(String username);
    Optional<Debtor> findByEmail(String email);
}
