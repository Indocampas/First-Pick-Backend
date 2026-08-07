package com.hragro.backend.repository;

import com.hragro.backend.model.FranchiseLocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FranchiseLocationRepository extends MongoRepository<FranchiseLocation, String> {
    List<FranchiseLocation> findByState(String state);
    List<FranchiseLocation> findByCityContainingIgnoreCase(String city);
}