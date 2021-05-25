package com.example.startup.service.debtor;

import com.example.startup.model.entity.Debtor;
import com.example.startup.repository.IDebtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DebtorService implements IDebtorService {
    @Autowired
    private IDebtorRepository debtorRepository;

    @Override
    public Iterable<Debtor> findAll() {
        return debtorRepository.findAll();
    }

    @Override
    public Optional<Debtor> findById(Long id) {
        return debtorRepository.findById(id);
    }

    @Override
    public Debtor save(Debtor debtor) {
        return debtorRepository.save(debtor);
    }

    @Override
    public void remove(Long id) {
        debtorRepository.deleteById(id);
    }

    @Override
    public Iterable<Debtor> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Debtor> debtors = debtorRepository.findAll(pageRequest);
        return debtors.getContent();
    }

    @Override
    public Optional<Debtor> findByUsername(String username) {
        return debtorRepository.findByUsername(username);
    }

    @Override
    public Optional<Debtor> findByEmail(String email) {
        return debtorRepository.findByEmail(email);
    }
}
