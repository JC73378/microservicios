package com.partasign.cl.partasign_ot_ms.Controller;

import com.partasign.cl.partasign_ot_ms.Model.OrdenTrabajoProgramada;
import com.partasign.cl.partasign_ot_ms.Service.OtProgramadaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "OT Programadas", description = "CRUD de órdenes de trabajo programadas")
@RequestMapping("/api/ot-programadas")
public class OtProgramadaController {

    @Autowired
    private OtProgramadaService service;

    @GetMapping
    @Operation(summary = "Listar OT programadas")
    public ResponseEntity<List<OrdenTrabajoProgramada>> listar() {
        List<OrdenTrabajoProgramada> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/crear")
    @Operation(summary = "Crear OT programada")
    public ResponseEntity<OrdenTrabajoProgramada> crear(@RequestBody OrdenTrabajoProgramada ot) {
        OrdenTrabajoProgramada creada = service.save(ot);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar OT programada por id")
    public ResponseEntity<OrdenTrabajoProgramada> buscar(@PathVariable Integer id) {
        Optional<OrdenTrabajoProgramada> ot = service.findById(id);
        return ot.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar OT programada")
    public ResponseEntity<OrdenTrabajoProgramada> actualizar(@PathVariable Integer id, @RequestBody OrdenTrabajoProgramada ot) {
        Optional<OrdenTrabajoProgramada> existenteOpt = service.findById(id);
        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        OrdenTrabajoProgramada existente = existenteOpt.get();
        existente.setNombreTecnico(ot.getNombreTecnico());
        existente.setApellidoTecnico(ot.getApellidoTecnico());
        existente.setRutTecnico(ot.getRutTecnico());
        existente.setNumeroEquipo(ot.getNumeroEquipo());
        existente.setFechaFirma(ot.getFechaFirma());
        existente.setRepuestoId(ot.getRepuestoId());

        OrdenTrabajoProgramada actualizada = service.save(existente);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar OT programada")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
