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
import com.hragro.backend.model.AboutContent;
import com.hragro.backend.service.AboutContentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/about")
@CrossOrigin(origins = "*")
public class AboutContentController {
    
    @Autowired
    private AboutContentService aboutContentService;
    
    @GetMapping
    public ResponseEntity<AboutContent> getAboutContent() {
        return ResponseEntity.ok(aboutContentService.getFirstAboutContent());
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<AboutContent>> getAllAboutContent() {
        return ResponseEntity.ok(aboutContentService.getAllAboutContent());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAboutContentById(@PathVariable String id) {
        return aboutContentService.getAboutContentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<AboutContent> createAboutContent(@Valid @RequestBody AboutContent content) {
        return ResponseEntity.ok(aboutContentService.createAboutContent(content));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AboutContent> updateAboutContent(@PathVariable String id, @Valid @RequestBody AboutContent content) {
        return ResponseEntity.ok(aboutContentService.updateAboutContent(id, content));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteAboutContent(@PathVariable String id) {
        aboutContentService.deleteAboutContent(id);
        return ResponseEntity.ok(new ResponseMessage("About content deleted successfully", true));
    }
}