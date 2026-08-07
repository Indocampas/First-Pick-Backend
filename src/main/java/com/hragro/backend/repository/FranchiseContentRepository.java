package com.hragro.backend.repository;

import com.hragro.backend.model.FranchiseContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FranchiseContentRepository extends MongoRepository<FranchiseContent, String> {
    Optional<FranchiseContent> findByCategory(String category);
}