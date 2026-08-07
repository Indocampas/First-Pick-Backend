package com.hragro.backend.repository;

import com.hragro.backend.model.GalleryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends MongoRepository<GalleryItem, String> {
}