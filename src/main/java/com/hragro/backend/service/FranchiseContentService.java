package com.hragro.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hragro.backend.model.FranchiseContent;
import com.hragro.backend.repository.FranchiseContentRepository;

@Service
public class FranchiseContentService {
    
    @Autowired
    private FranchiseContentRepository franchiseContentRepository;
    
    public List<FranchiseContent> getAllContent() {
        return franchiseContentRepository.findAll();
    }
    
    public Optional<FranchiseContent> getContentById(String id) {
        return franchiseContentRepository.findById(id);
    }
    
    public Optional<FranchiseContent> getContentByCategory(String category) {
        return franchiseContentRepository.findByCategory(category);
    }
    
    public FranchiseContent createContent(FranchiseContent content) {
        // Ensure trucks have IDs
        content.getTrucks().forEach(truck -> {
            if (truck.getId() == null || truck.getId().isEmpty()) {
                truck.setId(UUID.randomUUID().toString());
            }
        });
        return franchiseContentRepository.save(content);
    }
    
    public FranchiseContent updateContent(String id, FranchiseContent content) {
        content.setId(id);
        // Preserve existing truck IDs
        Optional<FranchiseContent> existing = franchiseContentRepository.findById(id);
        if (existing.isPresent()) {
            for (int i = 0; i < content.getTrucks().size(); i++) {
                FranchiseContent.Truck truck = content.getTrucks().get(i);
                if (truck.getId() == null || truck.getId().isEmpty()) {
                    truck.setId(UUID.randomUUID().toString());
                }
            }
        }
        return franchiseContentRepository.save(content);
    }
    
    public void deleteContent(String id) {
        franchiseContentRepository.deleteById(id);
    }
    
    // Add a truck to existing content
    public FranchiseContent addTruck(String contentId, FranchiseContent.Truck truck) {
        Optional<FranchiseContent> opt = franchiseContentRepository.findById(contentId);
        if (opt.isEmpty()) {
            throw new RuntimeException("Content not found");
        }
        FranchiseContent content = opt.get();
        truck.setId(UUID.randomUUID().toString());
        content.getTrucks().add(truck);
        return franchiseContentRepository.save(content);
    }
    
    // Remove a truck
    public FranchiseContent removeTruck(String contentId, String truckId) {
        Optional<FranchiseContent> opt = franchiseContentRepository.findById(contentId);
        if (opt.isEmpty()) {
            throw new RuntimeException("Content not found");
        }
        FranchiseContent content = opt.get();
        content.getTrucks().removeIf(t -> t.getId().equals(truckId));
        return franchiseContentRepository.save(content);
    }
}