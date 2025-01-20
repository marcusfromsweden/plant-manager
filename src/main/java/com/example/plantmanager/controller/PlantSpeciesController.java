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

import com.example.plantmanager.entity.PlantSpecies;
import com.example.plantmanager.service.PlantSpeciesService;

@RestController
@RequestMapping("/api/plant-species")
public class PlantSpeciesController {

    @Autowired
    private PlantSpeciesService plantSpeciesService;

    @GetMapping
    public ResponseEntity<List<PlantSpecies>> getAllPlantSpecies() {
        List<PlantSpecies> plantSpeciesList = plantSpeciesService.getAllPlantSpecies();
        return new ResponseEntity<>(plantSpeciesList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantSpecies> getPlantSpeciesById(@PathVariable Long id) {
        Optional<PlantSpecies> plantSpecies = plantSpeciesService.getPlantSpeciesById(id);
        return plantSpecies.map(species -> new ResponseEntity<>(species, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PlantSpecies> createPlantSpecies(@RequestBody PlantSpecies plantSpecies) {
        PlantSpecies createdPlantSpecies = plantSpeciesService.createPlantSpecies(plantSpecies);
        return new ResponseEntity<>(createdPlantSpecies, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantSpecies> updatePlantSpecies(@PathVariable Long id, @RequestBody PlantSpecies plantSpecies) {
        PlantSpecies updatedPlantSpecies = plantSpeciesService.updatePlantSpecies(id, plantSpecies);
        return new ResponseEntity<>(updatedPlantSpecies, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlantSpecies(@PathVariable Long id) {
        plantSpeciesService.deletePlantSpecies(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
