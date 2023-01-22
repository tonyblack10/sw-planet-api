package io.github.tonyblack10.swplanetapi.domain;

import org.springframework.stereotype.Service;

@Service
public class PlanetService {

    private PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Planet create(Planet planet) {
        return this.planetRepository.save(planet);
    }

}
