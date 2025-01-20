package com.example.plantmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plantmanager.entity.GrowingLocation;

@Repository
public interface GrowingLocationRepository extends JpaRepository<GrowingLocation, Long> {
}
