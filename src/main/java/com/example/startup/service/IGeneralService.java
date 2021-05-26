package com.example.startup.service;

import com.example.startup.model.entity.Debtor;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);

    Iterable<T> findAll(int page, int size);
}
