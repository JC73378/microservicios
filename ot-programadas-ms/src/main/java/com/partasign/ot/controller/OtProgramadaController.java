package com.partasign.ot.controller;

import com.partasign.ot.model.OtProgramada;
import com.partasign.ot.service.OtProgramadaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ot-programadas")
@Tag(name = "OT Programadas", description = "CRUD de órdenes de trabajo programadas")
public class OtProgramadaController {

    private final OtProgramadaService service;

    public OtProgramadaController(OtProgramadaService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar OT programadas")
    public ResponseEntity<List<OtProgramada>> listar() {
        List<OtProgramada> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Operation(summary = "Crear OT programada")
    public ResponseEntity<OtProgramada> crear(@RequestBody OtProgramada ot) {
        OtProgramada creada = service.save(ot);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar OT por id")
    public ResponseEntity<OtProgramada> buscar(@PathVariable Integer id) {
        Optional<OtProgramada> encontrada = service.findById(id);
        return encontrada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar OT")
    public ResponseEntity<OtProgramada> actualizar(@PathVariable Integer id, @RequestBody OtProgramada ot) {
        Optional<OtProgramada> existenteOpt = service.findById(id);
        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        OtProgramada existente = existenteOpt.get();
        existente.setNombreTecnico(ot.getNombreTecnico());
        existente.setApellidoTecnico(ot.getApellidoTecnico());
        existente.setRutTecnico(ot.getRutTecnico());
        existente.setNumeroEquipo(ot.getNumeroEquipo());
        existente.setFechaFirma(ot.getFechaFirma());
        existente.setRepuestoId(ot.getRepuestoId());
        OtProgramada actualizada = service.save(existente);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar OT")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

