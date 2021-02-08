package com.vizsgafeladat.vizsgafeladat.controller;

import com.vizsgafeladat.vizsgafeladat.entity.SuperHero;
import com.vizsgafeladat.vizsgafeladat.entity.Team;
import com.vizsgafeladat.vizsgafeladat.service.SuperHeroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/super-hero")
public class SuperHeroController {

    private static Logger logger = LoggerFactory.getLogger(SuperHeroService.class);

    private final SuperHeroService superHeroService;

    @Autowired
    public SuperHeroController(SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }

    @PostMapping("/new-super-hero")
    @ResponseStatus(HttpStatus.CREATED)
    public SuperHero createSuperHero(@RequestBody SuperHero superHero){
        logger.info("Received Post request to create superHero: {}", superHero);
        return superHeroService.createSuperHero(superHero);
    }

    @GetMapping("/all-super-heros")
    public List<SuperHero> getSuperHero(){
        logger.info("Getting all SuperHero..");
        return superHeroService.getSuperHero();
    }

    @GetMapping("/super-hero/{id}")
    public SuperHero getSuperHeroById(@PathVariable(name = "id") String id){
        logger.info("Getting id {}", id);
        Optional<SuperHero> superHero = superHeroService.getSuperHeroById(id);
        if(superHero.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No SuperHero found on " + id);
        }
        return superHero.get();
    }
}
