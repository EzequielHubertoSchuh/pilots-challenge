package com.br.empresa.pilots.result;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Result findById(long id);
}
