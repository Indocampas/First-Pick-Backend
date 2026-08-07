package com.hragro.backend.repository;

import com.hragro.backend.model.Fruit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends MongoRepository<Fruit, String> {
}