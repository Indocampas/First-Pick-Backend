package com.hragro.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hragro.backend.dto.ResponseMessage;
import com.hragro.backend.model.GalleryItem;
import com.hragro.backend.service.GalleryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin(origins = "*")
public class GalleryController {
    
    @Autowired
    private GalleryService galleryService;
    
    @GetMapping
    public ResponseEntity<List<GalleryItem>> getAllItems() {
        return ResponseEntity.ok(galleryService.getAllItems());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable String id) {
        return galleryService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<GalleryItem> createItem(@Valid @RequestBody GalleryItem item) {
        return ResponseEntity.ok(galleryService.createItem(item));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<GalleryItem> updateItem(@PathVariable String id, @Valid @RequestBody GalleryItem item) {
        return ResponseEntity.ok(galleryService.updateItem(id, item));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteItem(@PathVariable String id) {
        galleryService.deleteItem(id);
        return ResponseEntity.ok(new ResponseMessage("Gallery item deleted successfully", true));
    }
}