package com.example.personjpqlservice.repository;

import java.util.List;

public interface JpaRepository<T> {
    T findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
    void save(T t);
}
