package com.partasign.ot.repository;

import com.partasign.ot.model.OtProgramada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtProgramadaRepository extends JpaRepository<OtProgramada, Integer> {
}

