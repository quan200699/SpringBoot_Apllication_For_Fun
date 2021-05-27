package com.example.startup.service.loyalty;

import com.example.startup.model.entity.Loyalty;
import com.example.startup.repository.ILoyaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoyaltyService implements ILoyaltyService {
    @Autowired
    private ILoyaltyRepository loyaltyRepository;

    @Override
    public Iterable<Loyalty> findAll() {
        return loyaltyRepository.findAll();
    }

    @Override
    public Optional<Loyalty> findById(Long id) {
        return loyaltyRepository.findById(id);
    }

    @Override
    public Loyalty save(Loyalty loyalty) {
        return loyaltyRepository.save(loyalty);
    }

    @Override
    public void remove(Long id) {
        loyaltyRepository.deleteById(id);
    }

    @Override
    public Iterable<Loyalty> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Loyalty> loyalties = loyaltyRepository.findAll(pageRequest);
        return loyalties.getContent();
    }
}
