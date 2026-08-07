package com.hragro.backend.repository;

import com.hragro.backend.model.AboutContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutContentRepository extends MongoRepository<AboutContent, String> {
}