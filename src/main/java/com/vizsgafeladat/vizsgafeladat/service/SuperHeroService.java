package com.vizsgafeladat.vizsgafeladat.service;

import com.vizsgafeladat.vizsgafeladat.entity.SuperHero;
import com.vizsgafeladat.vizsgafeladat.entity.Team;
import com.vizsgafeladat.vizsgafeladat.repository.SuperHeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperHeroService {

    private static Logger logger = LoggerFactory.getLogger(SuperHeroService.class);

    private final SuperHeroRepository superHeroRepository;

    @Autowired
    public SuperHeroService(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    public SuperHero createSuperHero(SuperHero superHero) {
        logger.info("Creating superHero from input data: {}", superHero);
        SuperHero created = superHeroRepository.save(superHero);
        logger.debug("Created superHero: {}", created);
        return created;
    }

    public List<SuperHero> getSuperHero() {
        logger.info("Listing all teams...");
        List<SuperHero> teams = superHeroRepository.findAll();
        logger.debug("Found {} teams", teams.size());
        return teams;
    }

    public Optional<SuperHero> getSuperHeroById(String id) {
        logger.info("Getting superHero with id {} ", id);
        Optional<SuperHero> superHero = superHeroRepository.findById(id);
        logger.debug("Found superHero with id {}", superHero.isPresent() ? superHero.get() : "n.a.");
        return superHero;
    }
}
