package com.partasign.repuestos.controller;

import com.partasign.repuestos.model.Repuesto;
import com.partasign.repuestos.service.RepuestoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repuestos")
@Tag(name = "Repuestos", description = "CRUD de inventario de repuestos")
public class RepuestoController {

    private final RepuestoService service;

    public RepuestoController(RepuestoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar repuestos")
    public ResponseEntity<List<Repuesto>> listar() {
        List<Repuesto> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Operation(summary = "Crear repuesto")
    public ResponseEntity<Repuesto> crear(@RequestBody Repuesto repuesto) {
        Repuesto creado = service.save(repuesto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar repuesto por id")
    public ResponseEntity<Repuesto> buscar(@PathVariable Integer id) {
        Optional<Repuesto> encontrado = service.findById(id);
        return encontrado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar repuesto")
    public ResponseEntity<Repuesto> actualizar(@PathVariable Integer id, @RequestBody Repuesto repuesto) {
        Optional<Repuesto> existenteOpt = service.findById(id);
        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Repuesto existente = existenteOpt.get();
        existente.setCodigo365(repuesto.getCodigo365());
        existente.setCodigoBarras(repuesto.getCodigoBarras());
        existente.setNombre(repuesto.getNombre());
        existente.setStockActual(repuesto.getStockActual());
        existente.setUbicacion(repuesto.getUbicacion());
        existente.setCategoria(repuesto.getCategoria());
        Repuesto actualizado = service.save(existente);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar repuesto")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

