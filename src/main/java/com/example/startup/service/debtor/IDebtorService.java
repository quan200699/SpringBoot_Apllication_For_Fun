package com.example.startup.service.debtor;

import com.example.startup.model.entity.Debtor;
import com.example.startup.service.IGeneralService;

import java.util.Optional;

public interface IDebtorService extends IGeneralService<Debtor> {
    Iterable<Debtor> findAll(int page, int size);
    Optional<Debtor> findByUsername(String username);
    Optional<Debtor> findByEmail(String email);
}
