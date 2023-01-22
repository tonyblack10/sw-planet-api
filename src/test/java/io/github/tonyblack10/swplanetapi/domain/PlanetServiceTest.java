package io.github.tonyblack10.swplanetapi.domain;

import static io.github.tonyblack10.swplanetapi.common.PlanetConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

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

    @Test
    public void createPlanet_WithInvalidData_ThrowsException() {
        Mockito.when(planetRepository.save(INVALID_PLANET))
                .thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> planetService.create(INVALID_PLANET))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getPlanet_ByExistingId_ReturnsPlanet() {
        Mockito.when(planetRepository.findById(1L))
                .thenReturn(Optional.of(PLANET));

        var sut = planetService.get(1L);

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANET);
    }

    @Test
    public void getPlanet_ByUnexistingId_ReturnsEmpty() {
        Mockito.when(planetRepository.findById(1L))
                .thenReturn(Optional.empty());

        var sut = planetService.get(1L);

        assertThat(sut).isEmpty();
    }

}
