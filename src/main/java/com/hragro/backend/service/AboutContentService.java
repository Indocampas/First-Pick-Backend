package com.hragro.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hragro.backend.model.AboutContent;
import com.hragro.backend.repository.AboutContentRepository;

@Service
public class AboutContentService {
    
    @Autowired
    private AboutContentRepository aboutContentRepository;
    
    public List<AboutContent> getAllAboutContent() {
        return aboutContentRepository.findAll();
    }
    
    public Optional<AboutContent> getAboutContentById(String id) {
        return aboutContentRepository.findById(id);
    }
    
    public AboutContent getFirstAboutContent() {
        List<AboutContent> all = aboutContentRepository.findAll();
        if (all.isEmpty()) {
            // Create default if none exists
            AboutContent defaultContent = new AboutContent();
            defaultContent.setVision("To become India's most trusted farm-to-doorstep vegetable brand...");
            defaultContent.setMission("Our mission is to connect farmers directly with customers...");
            return aboutContentRepository.save(defaultContent);
        }
        return all.get(0);
    }
    
    public AboutContent createAboutContent(AboutContent content) {
        return aboutContentRepository.save(content);
    }
    
    public AboutContent updateAboutContent(String id, AboutContent content) {
        content.setId(id);
        return aboutContentRepository.save(content);
    }
    
    public void deleteAboutContent(String id) {
        aboutContentRepository.deleteById(id);
    }
}