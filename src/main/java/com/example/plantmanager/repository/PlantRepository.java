package com.example.plantmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plantmanager.entity.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
}