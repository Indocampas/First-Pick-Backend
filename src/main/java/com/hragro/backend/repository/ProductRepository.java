package com.hragro.backend.repository;

import com.hragro.backend.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategory(String category);
    
    // Remove extends ProductRepositoryCustom from here
    // We'll use ProductRepositoryCustom separately
}