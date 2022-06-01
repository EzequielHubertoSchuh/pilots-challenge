package com.br.empresa.pilots.driverstanding;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverStandingRepository extends JpaRepository<DriverStanding, Long> {
    DriverStanding findById(long id);
}
