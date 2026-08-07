package com.hragro.backend.service;

import com.hragro.backend.model.Fruit;
import com.hragro.backend.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitService {
    
    @Autowired
    private FruitRepository fruitRepository;
    
    public List<Fruit> getAllFruits() {
        return fruitRepository.findAll();
    }
    
    public Optional<Fruit> getFruitById(String id) {
        return fruitRepository.findById(id);
    }
    
    public Fruit createFruit(Fruit fruit) {
        return fruitRepository.save(fruit);
    }
    
    public Fruit updateFruit(String id, Fruit fruit) {
        fruit.setId(id);
        return fruitRepository.save(fruit);
    }
    
    public void deleteFruit(String id) {
        fruitRepository.deleteById(id);
    }
}