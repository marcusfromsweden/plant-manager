package com.example.plantmanager.controller;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.plantmanager.entity.GrowingLocation;
import com.example.plantmanager.service.GrowingLocationService;

@SpringBootTest
@AutoConfigureMockMvc
public class GrowingLocationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GrowingLocationService growingLocationService;

    private GrowingLocation growingLocation;

    @BeforeEach
    public void setup() {
        growingLocation = new GrowingLocation();
        growingLocation.setId(1L);
        growingLocation.setLocationName("Garden");
        growingLocation.setOccupied(true);
    }

    @Test
    public void testGetAllGrowingLocations() throws Exception {
        Mockito.when(growingLocationService.getAllGrowingLocations()).thenReturn(Arrays.asList(growingLocation));

        mockMvc.perform(get("/api/growing-locations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(growingLocation.getId().intValue())))
                .andExpect(jsonPath("$[0].locationName", is(growingLocation.getLocationName())))
                .andExpect(jsonPath("$[0].occupied", is(growingLocation.isOccupied())));
    }

    @Test
    public void testGetGrowingLocationById() throws Exception {
        Mockito.when(growingLocationService.getGrowingLocationById(growingLocation.getId())).thenReturn(Optional.of(growingLocation));

        mockMvc.perform(get("/api/growing-locations/{id}", growingLocation.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(growingLocation.getId().intValue())))
                .andExpect(jsonPath("$.locationName", is(growingLocation.getLocationName())))
                .andExpect(jsonPath("$.occupied", is(growingLocation.isOccupied())));
    }

    @Test
    public void testCreateGrowingLocation() throws Exception {
        Mockito.when(growingLocationService.createGrowingLocation(Mockito.any(GrowingLocation.class))).thenReturn(growingLocation);

        String growingLocationJson = String.format(
                "{\"locationName\":\"%s\",\"occupied\":%b}",
                growingLocation.getLocationName(),
                growingLocation.isOccupied()
        );

        mockMvc.perform(post("/api/growing-locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(growingLocationJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(growingLocation.getId().intValue())))
                .andExpect(jsonPath("$.locationName", is(growingLocation.getLocationName())))
                .andExpect(jsonPath("$.occupied", is(growingLocation.isOccupied())));
    }

    @Test
    public void testUpdateGrowingLocation() throws Exception {
        Mockito.when(growingLocationService.updateGrowingLocation(Mockito.eq(growingLocation.getId()), Mockito.any(GrowingLocation.class))).thenReturn(growingLocation);

        String growingLocationJson = String.format(
                "{\"locationName\":\"%s\",\"occupied\":%b}",
                growingLocation.getLocationName(),
                growingLocation.isOccupied()
        );

        mockMvc.perform(put("/api/growing-locations/{id}", growingLocation.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(growingLocationJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(growingLocation.getId().intValue())))
                .andExpect(jsonPath("$.locationName", is(growingLocation.getLocationName())))
                .andExpect(jsonPath("$.occupied", is(growingLocation.isOccupied())));
    }

    @Test
    public void testDeleteGrowingLocation() throws Exception {
        Mockito.doNothing().when(growingLocationService).deleteGrowingLocation(growingLocation.getId());

        mockMvc.perform(delete("/api/growing-locations/{id}", growingLocation.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
