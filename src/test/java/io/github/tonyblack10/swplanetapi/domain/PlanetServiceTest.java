package io.github.tonyblack10.swplanetapi.domain;

import static io.github.tonyblack10.swplanetapi.common.PlanetConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PlanetService.class)
public class PlanetServiceTest {

    private PlanetService planetService;

    @Test
    public void createPlanet_WithValidData_ReturnsPLanet() {
        var sut = planetService.create(PLANET);

        assertThat(sut).isEqualTo(PLANET);
    }

}
