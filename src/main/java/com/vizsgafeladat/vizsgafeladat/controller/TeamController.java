package com.vizsgafeladat.vizsgafeladat.controller;

import com.vizsgafeladat.vizsgafeladat.entity.Team;
import com.vizsgafeladat.vizsgafeladat.exceptions.ValidationException;
import com.vizsgafeladat.vizsgafeladat.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/teams")
public class TeamController {

    private static Logger logger = LoggerFactory.getLogger(TeamService.class);

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/newteam")
    @ResponseStatus(HttpStatus.CREATED)
    public Team createTeam(@RequestBody Team team){
        logger.info("Received Post request to create team: {}", team);
        return teamService.createTeam(team);
    }

    @GetMapping("/allteams")
    public List<Team> getTeams(){
        logger.info("Getting all food..");
        return teamService.getTeams();
    }

    @GetMapping("/team/{id}")
    public Team getTeamById(@PathVariable(name = "id") String id){
        logger.info("Getting id {}", id);
        Optional<Team> team = teamService.getTeamById(id);
        if(team.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No team found on " + id);
        }
        return team.get();
    }

    @PutMapping("/team/{id}")
    public Team updateTeam(@PathVariable("id") String id, @RequestBody Team team) throws ValidationException {
        logger.info("Received Address update request for id {} with Address: {}", id, team);
        try {
            Team updatedTeam = teamService.updateTeam(id, team);
            logger.debug("The updated team is: {}", updatedTeam);
            return updatedTeam;
        } catch (ValidationException e) {
            logger.error("Team with given id ({}) not found.", id);
            throw new ValidationException("No such id");
        }
        return updatedTeam;
    }
}
