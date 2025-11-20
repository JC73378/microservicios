package com.partasign.cl.partasign_ot_ms.Repository;

import com.partasign.cl.partasign_ot_ms.Model.OrdenTrabajoProgramada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtProgramadaRepository extends JpaRepository<OrdenTrabajoProgramada, Integer> {
}
