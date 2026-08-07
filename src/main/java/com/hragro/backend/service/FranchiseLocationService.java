package com.hragro.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hragro.backend.model.FranchiseLocation;
import com.hragro.backend.repository.FranchiseLocationRepository;

@Service
public class FranchiseLocationService {
    
    @Autowired
    private FranchiseLocationRepository franchiseLocationRepository;
    
    public List<FranchiseLocation> getAllLocations() {
        return franchiseLocationRepository.findAll();
    }
    
    public Optional<FranchiseLocation> getLocationById(String id) {
        return franchiseLocationRepository.findById(id);
    }
    
    public List<FranchiseLocation> getLocationsByState(String state) {
        return franchiseLocationRepository.findByState(state);
    }
    
    public FranchiseLocation createLocation(FranchiseLocation location) {
        // Ensure each place has an ID
        location.getPlaces().forEach(place -> {
            if (place.getId() == null || place.getId().isEmpty()) {
                place.setId(UUID.randomUUID().toString());
            }
        });
        return franchiseLocationRepository.save(location);
    }
    
    public FranchiseLocation updateLocation(String id, FranchiseLocation location) {
        location.setId(id);
        // Preserve existing place IDs
        Optional<FranchiseLocation> existing = franchiseLocationRepository.findById(id);
        if (existing.isPresent()) {
            for (int i = 0; i < location.getPlaces().size(); i++) {
                FranchiseLocation.Place place = location.getPlaces().get(i);
                if (place.getId() == null || place.getId().isEmpty()) {
                    place.setId(UUID.randomUUID().toString());
                }
            }
        }
        return franchiseLocationRepository.save(location);
    }
    
    public void deleteLocation(String id) {
        franchiseLocationRepository.deleteById(id);
    }
    
    // Add a place to an existing location
    public FranchiseLocation addPlace(String locationId, FranchiseLocation.Place place) {
        Optional<FranchiseLocation> opt = franchiseLocationRepository.findById(locationId);
        if (opt.isEmpty()) {
            throw new RuntimeException("Location not found");
        }
        FranchiseLocation location = opt.get();
        place.setId(UUID.randomUUID().toString());
        location.getPlaces().add(place);
        return franchiseLocationRepository.save(location);
    }
    
    // Remove a place from a location
    public FranchiseLocation removePlace(String locationId, String placeId) {
        Optional<FranchiseLocation> opt = franchiseLocationRepository.findById(locationId);
        if (opt.isEmpty()) {
            throw new RuntimeException("Location not found");
        }
        FranchiseLocation location = opt.get();
        location.getPlaces().removeIf(p -> p.getId().equals(placeId));
        return franchiseLocationRepository.save(location);
    }
    
    // Toggle place availability
    public FranchiseLocation togglePlaceAvailability(String locationId, String placeId) {
        Optional<FranchiseLocation> opt = franchiseLocationRepository.findById(locationId);
        if (opt.isEmpty()) {
            throw new RuntimeException("Location not found");
        }
        FranchiseLocation location = opt.get();
        location.getPlaces().forEach(place -> {
            if (place.getId().equals(placeId)) {
                place.setAvailable(!place.isAvailable());
            }
        });
        return franchiseLocationRepository.save(location);
    }
}