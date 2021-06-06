package com.example.startup.service;

import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);

    Page<T> findAll(int page, int size);
}
