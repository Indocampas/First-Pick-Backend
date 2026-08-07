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
import com.hragro.backend.model.FranchiseContent;
import com.hragro.backend.service.FranchiseContentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/franchise/content")
@CrossOrigin(origins = "*")
public class FranchiseContentController {
    
    @Autowired
    private FranchiseContentService franchiseContentService;
    
    @GetMapping
    public ResponseEntity<List<FranchiseContent>> getAllContent() {
        return ResponseEntity.ok(franchiseContentService.getAllContent());
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getContentByCategory(@PathVariable String category) {
        return franchiseContentService.getContentByCategory(category)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getContentById(@PathVariable String id) {
        return franchiseContentService.getContentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<FranchiseContent> createContent(@Valid @RequestBody FranchiseContent content) {
        return ResponseEntity.ok(franchiseContentService.createContent(content));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FranchiseContent> updateContent(@PathVariable String id, @Valid @RequestBody FranchiseContent content) {
        return ResponseEntity.ok(franchiseContentService.updateContent(id, content));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteContent(@PathVariable String id) {
        franchiseContentService.deleteContent(id);
        return ResponseEntity.ok(new ResponseMessage("Content deleted successfully", true));
    }
    
    @PostMapping("/{id}/trucks")
    public ResponseEntity<FranchiseContent> addTruck(@PathVariable String id, @RequestBody FranchiseContent.Truck truck) {
        return ResponseEntity.ok(franchiseContentService.addTruck(id, truck));
    }
    
    @DeleteMapping("/{contentId}/trucks/{truckId}")
    public ResponseEntity<ResponseMessage> removeTruck(@PathVariable String contentId, @PathVariable String truckId) {
        franchiseContentService.removeTruck(contentId, truckId);
        return ResponseEntity.ok(new ResponseMessage("Truck removed successfully", true));
    }
}