package com.example.startup.service.debtor;

import com.example.startup.model.entity.Debtor;
import com.example.startup.service.IGeneralService;

public interface IDebtorService extends IGeneralService<Debtor> {
    Iterable<Debtor> findAll(int page, int size);
}
