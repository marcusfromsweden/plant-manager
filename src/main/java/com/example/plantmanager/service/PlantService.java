package com.example.plantmanager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plantmanager.entity.GrowingLocation;
import com.example.plantmanager.entity.Plant;
import com.example.plantmanager.entity.PlantSpecies;
import com.example.plantmanager.repository.GrowingLocationRepository;
import com.example.plantmanager.repository.PlantRepository;
import com.example.plantmanager.repository.PlantSpeciesRepository;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private PlantSpeciesRepository plantSpeciesRepository;

    @Autowired
    private GrowingLocationRepository growingLocationRepository;

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Optional<Plant> getPlantById(Long id) {
        return plantRepository.findById(id);
    }

    public Plant addPlant(Long plantSpeciesId, Long growingLocationId, LocalDate plantingDate, LocalDate germinationDate, String comment) {
        Plant plant = new Plant();

        // Set plant species
        Optional<PlantSpecies> optionalPlantSpecies = plantSpeciesRepository.findById(plantSpeciesId);
        optionalPlantSpecies.ifPresent(plant::setPlantSpecies);

        // Set growing location
        Optional<GrowingLocation> optionalGrowingLocation = growingLocationRepository.findById(growingLocationId);
        optionalGrowingLocation.ifPresent(plant::setGrowingLocation);

        // Set other fields
        plant.setPlantingDate(plantingDate);
        plant.setGerminationDate(germinationDate);
        plant.setComment(comment);

        return plantRepository.save(plant);
    }

    public Plant updatePlant(Long plantId, Long plantSpeciesId, Long growingLocationId, LocalDate plantingDate, LocalDate germinationDate, String comment) {
        Optional<Plant> optionalPlant = plantRepository.findById(plantId);
        if (optionalPlant.isPresent()) {
            Plant plant = optionalPlant.get();

            // Update plant species
            Optional<PlantSpecies> optionalPlantSpecies = plantSpeciesRepository.findById(plantSpeciesId);
            optionalPlantSpecies.ifPresent(plant::setPlantSpecies);

            // Update growing location
            Optional<GrowingLocation> optionalGrowingLocation = growingLocationRepository.findById(growingLocationId);
            optionalGrowingLocation.ifPresent(plant::setGrowingLocation);

            // Update other fields
            plant.setPlantingDate(plantingDate);
            plant.setGerminationDate(germinationDate);
            plant.setComment(comment);

            return plantRepository.save(plant);
        } else {
            throw new RuntimeException("Plant not found with id " + plantId);
        }
    }

    public void deletePlant(Long id) {
        plantRepository.deleteById(id);
    }
}
