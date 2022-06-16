package com.example.restaurants.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class MemoryDbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer index;
}
