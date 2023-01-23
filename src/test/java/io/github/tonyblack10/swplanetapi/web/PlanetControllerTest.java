package io.github.tonyblack10.swplanetapi.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static io.github.tonyblack10.swplanetapi.common.PlanetConstants.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.tonyblack10.swplanetapi.domain.Planet;
import io.github.tonyblack10.swplanetapi.domain.PlanetService;

@WebMvcTest(PlanetController.class)
public class PlanetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlanetService planetService;

    @Test
    public void createPlanet_WithValidData_ReturnsCreated() throws Exception {
        var planetJson = objectMapper.writeValueAsString(PLANET);

        Mockito.when(planetService.create(PLANET)).thenReturn(PLANET);

        mockMvc.perform(post("/planets").content(planetJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(PLANET));
    }

    @Test
    public void createPlanet_WithInvalidData_ReturnsBadRequest() throws Exception {
        var emptyPlanet = objectMapper.writeValueAsString(new Planet());
        var invalidPlanet = objectMapper.writeValueAsString(new Planet("", "", ""));

        mockMvc.perform(post("/planets").content(emptyPlanet).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(post("/planets").content(invalidPlanet).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

}
