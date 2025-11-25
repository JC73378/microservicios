package com.partasign.recepciones.controller;

import com.partasign.recepciones.model.Recepcion;
import com.partasign.recepciones.service.RecepcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recepciones")
@Tag(name = "Recepciones", description = "CRUD de recepciones de repuestos")
public class RecepcionController {

    private final RecepcionService service;

    public RecepcionController(RecepcionService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar recepciones")
    public ResponseEntity<List<Recepcion>> listar() {
        List<Recepcion> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Operation(summary = "Crear recepción")
    public ResponseEntity<Recepcion> crear(@RequestBody Recepcion recepcion) {
        Recepcion creada = service.save(recepcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar recepción por id")
    public ResponseEntity<Recepcion> buscar(@PathVariable Integer id) {
        Optional<Recepcion> encontrada = service.findById(id);
        return encontrada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar recepción")
    public ResponseEntity<Recepcion> actualizar(@PathVariable Integer id, @RequestBody Recepcion recepcion) {
        Optional<Recepcion> existenteOpt = service.findById(id);
        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Recepcion existente = existenteOpt.get();
        existente.setRepuestoId(recepcion.getRepuestoId());
        existente.setCantidadRecibida(recepcion.getCantidadRecibida());
        existente.setRecibidoPor(recepcion.getRecibidoPor());
        existente.setFechaRecepcion(recepcion.getFechaRecepcion());
        existente.setObservacion(recepcion.getObservacion());
        Recepcion actualizada = service.save(existente);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar recepción")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

