package com.example.plantmanager.controller;

import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PlantControllerTests.class);

    private static final String PLANT_NAME = "Rose";
    private static final String PLANT_DESCRIPTION = "A beautiful red rose";
    private static final String PLANT_TYPE = "Flower";
    private static final String PLANT_JSON = String.format(
            "{\"name\": \"%s\", \"description\": \"%s\", \"type\": \"%s\"}",
            PLANT_NAME, PLANT_DESCRIPTION, PLANT_TYPE);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddVerifyDeletePlant() throws Exception {
        logger.info("Starting test: testAddVerifyDeletePlant");

        // 1. Add a plant
        logger.info("Adding a new plant with JSON: {}", PLANT_JSON);
        MvcResult result = mockMvc.perform(post("/api/plants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(PLANT_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(PLANT_NAME)))
                .andExpect(jsonPath("$.description", is(PLANT_DESCRIPTION)))
                .andExpect(jsonPath("$.type", is(PLANT_TYPE)))
                .andReturn();

        // Extract the ID of the created plant
        String response = result.getResponse().getContentAsString();
        logger.error("Response from adding plant: {}", response);
        Integer plantIdInt = JsonPath.read(response, "$.id");
        Long plantId = plantIdInt.longValue();
        logger.info("Created plant with ID: {}", plantId);

        // 2. Verify that the plant exists and has correct property values
        logger.info("Verifying that the plant exists with ID: {}", plantId);
        mockMvc.perform(get("/api/plants/" + plantId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(PLANT_NAME)))
                .andExpect(jsonPath("$.description", is(PLANT_DESCRIPTION)))
                .andExpect(jsonPath("$.type", is(PLANT_TYPE)));

        // 3. Delete the plant
        logger.info("Deleting the plant with ID: {}", plantId);
        mockMvc.perform(delete("/api/plants/" + plantId))
                .andExpect(status().isNoContent());

        // 4. Verify that the plant no longer exists
        logger.info("Verifying that the plant no longer exists with ID: {}", plantId);
        mockMvc.perform(get("/api/plants/" + plantId))
                .andExpect(status().isNotFound());

        logger.info("Completed test: testAddVerifyDeletePlant");
    }
}
