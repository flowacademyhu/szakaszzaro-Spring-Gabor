package com.vizsgafeladat.vizsgafeladat.service;

import com.vizsgafeladat.vizsgafeladat.entity.Team;
import com.vizsgafeladat.vizsgafeladat.exceptions.ValidationException;
import com.vizsgafeladat.vizsgafeladat.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private static Logger logger = LoggerFactory.getLogger(TeamService.class);

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team createTeam(Team team) {
        logger.info("Creating team from input data: {}", team);
        Team created = teamRepository.save(team);
        logger.debug("Created team: {}", created);
        return created;
    }

    public List<Team> getTeams() {
        logger.info("Listing all teams...");
        List<Team> teams = teamRepository.findAll();
        logger.debug("Found {} teams", teams.size());
        return teams;
    }

    public Optional<Team> getTeamById(String id) {
        logger.info("Getting team with id {} ", id);
        Optional<Team> teams = teamRepository.findById(id);
        logger.debug("Found team with id {}", teams.isPresent() ? teams.get() : "n.a.");
        return teams;
    }

    public Team updateTeam(String id, Team team) {
        logger.info("Canging team ..");
        Optional<Team> teams = teamRepository.findById(id);
        if(teams.isEmpty()) {
            throw new ValidationException("Not valid");
        }else {
            teams = teamRepository.save(team);
        }
        logger.debug("Updated team {}", teams);
        return teams;
    }
}
