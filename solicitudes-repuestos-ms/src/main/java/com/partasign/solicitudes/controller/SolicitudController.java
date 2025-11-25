package com.partasign.solicitudes.controller;

import com.partasign.solicitudes.model.Solicitud;
import com.partasign.solicitudes.service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/solicitudes")
@Tag(name = "Solicitudes de repuestos", description = "CRUD de solicitudes de repuesto")
public class SolicitudController {

    private final SolicitudService service;

    public SolicitudController(SolicitudService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar solicitudes")
    public ResponseEntity<List<Solicitud>> listar() {
        List<Solicitud> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Operation(summary = "Crear solicitud")
    public ResponseEntity<Solicitud> crear(@RequestBody Solicitud solicitud) {
        Solicitud creada = service.save(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar solicitud por id")
    public ResponseEntity<Solicitud> buscar(@PathVariable Integer id) {
        Optional<Solicitud> encontrada = service.findById(id);
        return encontrada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar solicitud")
    public ResponseEntity<Solicitud> actualizar(@PathVariable Integer id, @RequestBody Solicitud solicitud) {
        Optional<Solicitud> existenteOpt = service.findById(id);
        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Solicitud existente = existenteOpt.get();
        existente.setCodigo(solicitud.getCodigo());
        existente.setTipo(solicitud.getTipo());
        existente.setCantidad(solicitud.getCantidad());
        existente.setDescripcion(solicitud.getDescripcion());
        existente.setNombreTecnico(solicitud.getNombreTecnico());
        existente.setFecha(solicitud.getFecha());
        Solicitud actualizada = service.save(existente);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar solicitud")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

