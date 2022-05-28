package com.br.empresa.pilots.constructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructorRepository extends JpaRepository<Constructor, Long> {
    Constructor findById(long id);
}
