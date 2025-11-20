package com.partasign.cl.partsasign_ms.Repository;

import com.partasign.cl.partsasign_ms.Model.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepuestosRepository extends JpaRepository<Repuesto, Integer> {
    Optional<Repuesto> findByCodigo365(String codigo365);
    Optional<Repuesto> findByCodigoBarras(String codigoBarras);
}
