package com.br.empresa.pilots.qualifying;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualifyingRepository extends JpaRepository<Qualifying, Long> {
    Qualifying findById(long id);
}
