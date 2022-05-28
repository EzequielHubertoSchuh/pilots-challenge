package com.br.empresa.pilots.driver;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findById(long id);
}
