package com.example.plantmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.plantmanager.entity.Plant;
import com.example.plantmanager.service.PlantService;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantService.getAllPlants();
        return new ResponseEntity<>(plants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable Long id) {
        Optional<Plant> plant = plantService.getPlantById(id);
        if (plant.isPresent()) {
            return new ResponseEntity<>(plant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<Plant> createPlant(@RequestBody Plant plant) {
        Plant createdPlant = plantService.addPlant(plant);
        return new ResponseEntity<>(createdPlant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable Long id, @RequestBody Plant plant) {
        Plant updatedPlant = plantService.updatePlant(id, plant);
        return updatedPlant != null ? new ResponseEntity<>(updatedPlant, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        if (plantService.getPlantById(id).isPresent()) {
            plantService.deletePlant(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}