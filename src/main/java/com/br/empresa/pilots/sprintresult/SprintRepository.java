package com.br.empresa.pilots.sprintresult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<SprintResult, Long> {
    SprintResult findById(long id);
}
