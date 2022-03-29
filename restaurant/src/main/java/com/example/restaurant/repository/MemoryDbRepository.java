package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepository<T>{

    Optional<T> findById(int index);
    T save(T entity);
    void deleteById(int index);
    List<T> listAll();
}
