package com.br.empresa.pilots.pitstop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PitStopRepository extends JpaRepository<PitStop, Long> {
    PitStop findById(long id);
}
