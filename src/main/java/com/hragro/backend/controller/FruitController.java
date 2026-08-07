package com.hragro.backend.controller;

import com.hragro.backend.dto.ResponseMessage;
import com.hragro.backend.model.Fruit;
import com.hragro.backend.service.FruitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fruits")
@CrossOrigin(origins = "*")
public class FruitController {
    
    @Autowired
    private FruitService fruitService;
    
    @GetMapping
    public ResponseEntity<List<Fruit>> getAllFruits() {
        return ResponseEntity.ok(fruitService.getAllFruits());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getFruitById(@PathVariable String id) {
        return fruitService.getFruitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Fruit> createFruit(@Valid @RequestBody Fruit fruit) {
        return ResponseEntity.ok(fruitService.createFruit(fruit));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Fruit> updateFruit(@PathVariable String id, @Valid @RequestBody Fruit fruit) {
        return ResponseEntity.ok(fruitService.updateFruit(id, fruit));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteFruit(@PathVariable String id) {
        fruitService.deleteFruit(id);
        return ResponseEntity.ok(new ResponseMessage("Fruit deleted successfully", true));
    }
}