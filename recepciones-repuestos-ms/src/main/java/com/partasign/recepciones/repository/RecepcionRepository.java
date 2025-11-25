package com.partasign.recepciones.repository;

import com.partasign.recepciones.model.Recepcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecepcionRepository extends JpaRepository<Recepcion, Integer> {
}

