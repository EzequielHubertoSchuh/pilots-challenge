package com.br.empresa.pilots.circuit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircuitRepository extends JpaRepository<Circuit, Long>{
    Circuit findById(long id);
}
