package com.example.plantmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plantmanager.entity.Plant;
import com.example.plantmanager.repository.PlantRepository;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Optional<Plant> getPlantById(Long id) {
        return plantRepository.findById(id);
    }

    public Plant addPlant(Plant plant) {
        return plantRepository.save(plant);
    }

    public Plant updatePlant(Long id, Plant plantDetails) {
        Plant plant = plantRepository.findById(id).orElseThrow(() -> new RuntimeException("Plant not found"));
        plant.setName(plantDetails.getName());
        plant.setType(plantDetails.getType());
        plant.setDescription(plantDetails.getDescription());
        return plantRepository.save(plant);
    }

    public void deletePlant(Long id) {
        plantRepository.deleteById(id);
    }
}