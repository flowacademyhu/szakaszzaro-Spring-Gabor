package com.vizsgafeladat.vizsgafeladat.repository;

import com.vizsgafeladat.vizsgafeladat.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRepository extends JpaRepository<Team, String> {
}
