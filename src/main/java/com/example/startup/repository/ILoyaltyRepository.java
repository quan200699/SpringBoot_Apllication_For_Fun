package com.example.startup.repository;

import com.example.startup.model.entity.Loyalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoyaltyRepository extends JpaRepository<Loyalty, Long> {
}
