package com.marcusfromsweden.plantdoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.marcusfromsweden.plantdoctor.entity.PlantSpecies;

@Repository
public interface PlantSpeciesRepository extends JpaRepository<PlantSpecies, Long> {
}