package com.example.plantmanager.controller;

import com.example.plantmanager.entity.GrowingLocation;
import com.example.plantmanager.service.GrowingLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/growing-locations")
public class GrowingLocationController {

    @Autowired
    private GrowingLocationService growingLocationService;

    @GetMapping
    public ResponseEntity<List<GrowingLocation>> getAllGrowingLocations() {
        List<GrowingLocation> growingLocationList = growingLocationService.getAllGrowingLocations();
        return new ResponseEntity<>(growingLocationList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrowingLocation> getGrowingLocationById(@PathVariable Long id) {
        Optional<GrowingLocation> growingLocation = growingLocationService.getGrowingLocationById(id);
        return growingLocation.map(location -> new ResponseEntity<>(location, HttpStatus.OK))
                              .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<GrowingLocation> createGrowingLocation(@RequestBody GrowingLocation growingLocation) {
        GrowingLocation createdGrowingLocation = growingLocationService.createGrowingLocation(growingLocation);
        return new ResponseEntity<>(createdGrowingLocation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrowingLocation> updateGrowingLocation(@PathVariable Long id, @RequestBody GrowingLocation growingLocation) {
        GrowingLocation updatedGrowingLocation = growingLocationService.updateGrowingLocation(id, growingLocation);
        return new ResponseEntity<>(updatedGrowingLocation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrowingLocation(@PathVariable Long id) {
        growingLocationService.deleteGrowingLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}