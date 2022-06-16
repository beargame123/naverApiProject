package com.example.restaurants.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemoryDbService{

    private final MemoryDbRepository memoryDbRepository;

    public Optional<MemoryDbEntity> filterSee(Integer id){ return memoryDbRepository.findById(id);}

    public MemoryDbEntity save(MemoryDbEntity memoryDbEntity){
        memoryDbRepository.save(memoryDbEntity);
        return memoryDbEntity;
    }

    public List<MemoryDbEntity> findAlls(){
        return memoryDbRepository.findAll();
    }

}
