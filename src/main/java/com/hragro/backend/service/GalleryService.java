package com.hragro.backend.service;

import com.hragro.backend.model.GalleryItem;
import com.hragro.backend.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GalleryService {
    
    @Autowired
    private GalleryRepository galleryRepository;
    
    public List<GalleryItem> getAllItems() {
        return galleryRepository.findAll();
    }
    
    public Optional<GalleryItem> getItemById(String id) {
        return galleryRepository.findById(id);
    }
    
    public GalleryItem createItem(GalleryItem item) {
        return galleryRepository.save(item);
    }
    
    public GalleryItem updateItem(String id, GalleryItem item) {
        item.setId(id);
        return galleryRepository.save(item);
    }
    
    public void deleteItem(String id) {
        galleryRepository.deleteById(id);
    }
}