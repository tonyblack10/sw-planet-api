package io.github.tonyblack10.swplanetapi.domain;

import static io.github.tonyblack10.swplanetapi.common.PlanetConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

// @SpringBootTest(classes = PlanetService.class)
@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;

    @Mock
    private PlanetRepository planetRepository;

    @Test
    public void createPlanet_WithValidData_ReturnsPLanet() {
        Mockito.when(planetRepository.save(PLANET))
                .thenReturn(PLANET);

        var sut = planetService.create(PLANET);

        assertThat(sut).isEqualTo(PLANET);
    }

}
