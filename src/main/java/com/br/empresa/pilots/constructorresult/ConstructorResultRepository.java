package com.br.empresa.pilots.constructorresult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructorResultRepository extends JpaRepository<ConstructorResult, Long> {
    ConstructorResult findById(long id);
}
