package com.br.empresa.pilots.race;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long>{
    Race findById(long id);
}
