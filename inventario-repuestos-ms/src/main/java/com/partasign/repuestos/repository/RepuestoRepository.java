package com.partasign.repuestos.repository;

import com.partasign.repuestos.model.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Integer> {
    Optional<Repuesto> findByCodigo365(String codigo365);
    Optional<Repuesto> findByCodigoBarras(String codigoBarras);
}

