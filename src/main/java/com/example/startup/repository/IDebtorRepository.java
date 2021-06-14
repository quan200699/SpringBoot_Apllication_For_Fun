package com.example.startup.repository;

import com.example.startup.model.entity.Debtor;
import com.example.startup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDebtorRepository extends JpaRepository<Debtor, Long> {
    Optional<Debtor> findByUser(User user);
}
