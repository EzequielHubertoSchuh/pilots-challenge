package com.br.empresa.pilots.laptimes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LapTimesRepository extends JpaRepository<LapTimes, Long> {
    LapTimes findById(long id);
}
