package com.hragro.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hragro.backend.dto.ResponseMessage;
import com.hragro.backend.model.FranchiseLocation;
import com.hragro.backend.service.FranchiseLocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/franchise/locations")
@CrossOrigin(origins = "*")
public class FranchiseLocationController {
    
    @Autowired
    private FranchiseLocationService franchiseLocationService;
    
    @GetMapping
    public ResponseEntity<List<FranchiseLocation>> getAllLocations() {
        return ResponseEntity.ok(franchiseLocationService.getAllLocations());
    }
    
    @GetMapping("/state/{state}")
    public ResponseEntity<List<FranchiseLocation>> getLocationsByState(@PathVariable String state) {
        return ResponseEntity.ok(franchiseLocationService.getLocationsByState(state));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable String id) {
        return franchiseLocationService.getLocationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<FranchiseLocation> createLocation(@Valid @RequestBody FranchiseLocation location) {
        return ResponseEntity.ok(franchiseLocationService.createLocation(location));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FranchiseLocation> updateLocation(@PathVariable String id, @Valid @RequestBody FranchiseLocation location) {
        return ResponseEntity.ok(franchiseLocationService.updateLocation(id, location));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteLocation(@PathVariable String id) {
        franchiseLocationService.deleteLocation(id);
        return ResponseEntity.ok(new ResponseMessage("Location deleted successfully", true));
    }
    
    @PostMapping("/{id}/places")
    public ResponseEntity<FranchiseLocation> addPlace(@PathVariable String id, @RequestBody FranchiseLocation.Place place) {
        return ResponseEntity.ok(franchiseLocationService.addPlace(id, place));
    }
    
    @DeleteMapping("/{locationId}/places/{placeId}")
    public ResponseEntity<ResponseMessage> removePlace(@PathVariable String locationId, @PathVariable String placeId) {
        franchiseLocationService.removePlace(locationId, placeId);
        return ResponseEntity.ok(new ResponseMessage("Place removed successfully", true));
    }
    
    @PatchMapping("/{locationId}/places/{placeId}/toggle")
    public ResponseEntity<FranchiseLocation> togglePlaceAvailability(@PathVariable String locationId, @PathVariable String placeId) {
        return ResponseEntity.ok(franchiseLocationService.togglePlaceAvailability(locationId, placeId));
    }
}