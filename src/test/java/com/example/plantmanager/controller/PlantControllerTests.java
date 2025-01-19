package com.example.plantmanager.controller;

import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class PlantControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddVerifyDeletePlant() throws Exception {
        // 1. Add a plant
        String plantJson = "{\"name\": \"Rose\", \"description\": \"A beautiful red rose\", \"type\": \"Flower\"}";
        MvcResult result = mockMvc.perform(post("/api/plants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(plantJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Rose")))
                .andExpect(jsonPath("$.description", is("A beautiful red rose")))
                .andExpect(jsonPath("$.type", is("Flower")))
                .andReturn();

        // Extract the ID of the created plant
        String response = result.getResponse().getContentAsString();
        logger.info("Created plant with ID: {}", plantId);
        Long plantId = JsonPath.read(response, "$.id");

        // 2. Verify that the plant exists and has correct property values
        mockMvc.perform(get("/api/plants/" + plantId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Rose")))
                .andExpect(jsonPath("$.description", is("A beautiful red rose")))
                .andExpect(jsonPath("$.type", is("Flower")));

        // 3. Delete the plant
        mockMvc.perform(delete("/api/plants/" + plantId))
                .andExpect(status().isNoContent());

        // 4. Verify that the plant no longer exists
        mockMvc.perform(get("/api/plants/" + plantId))
                .andExpect(status().isNotFound());
    }
}