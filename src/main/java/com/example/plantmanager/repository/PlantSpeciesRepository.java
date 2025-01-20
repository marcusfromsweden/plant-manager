package com.example.plantmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plantmanager.entity.PlantSpecies;

@Repository
public interface PlantSpeciesRepository extends JpaRepository<PlantSpecies, Long> {
}