package com.example.startup.service.debt;

import com.example.startup.model.entity.Debt;
import com.example.startup.repository.IDebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DebtService implements IDebtService {
    @Autowired
    private IDebtRepository debtRepository;

    @Override
    public Iterable<Debt> findAll() {
        return debtRepository.findAll();
    }

    @Override
    public Optional<Debt> findById(Long id) {
        return debtRepository.findById(id);
    }

    @Override
    public Debt save(Debt debt) {
        return debtRepository.save(debt);
    }

    @Override
    public void remove(Long id) {
        debtRepository.deleteById(id);
    }

    @Override
    public Iterable<Debt> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Debt> debts = debtRepository.findAll(pageRequest);
        return debts.getContent();
    }
}
