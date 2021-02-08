package com.vizsgafeladat.vizsgafeladat.repository;

import com.vizsgafeladat.vizsgafeladat.entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SuperHeroRepository extends JpaRepository<SuperHero, String> {
}
