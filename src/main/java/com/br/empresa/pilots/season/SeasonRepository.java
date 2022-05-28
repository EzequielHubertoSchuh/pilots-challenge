package com.br.empresa.pilots.season;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    Season findById(long id);
}
