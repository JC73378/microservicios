package com.partasign.cl.partsasign_ms.Controller;

import com.partasign.cl.partsasign_ms.Model.Repuesto;
import com.partasign.cl.partsasign_ms.Service.RepuestosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Repuestos", description = "Operaciones CRUD para repuestos")
@RequestMapping("/api/repuestos")
public class RepuestosController {

    @Autowired
    private RepuestosService repuestosService;

    @GetMapping
    @Operation(summary = "Listar repuestos", description = "Obtiene todos los repuestos.")
    public ResponseEntity<List<Repuesto>> listar() {
        List<Repuesto> repuestos = repuestosService.findAll();
        if (repuestos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(repuestos);
    }

    @PostMapping("/crear")
    @Operation(summary = "Crear repuesto", description = "Registra un nuevo repuesto.")
    public ResponseEntity<Repuesto> crear(@RequestBody Repuesto repuesto) {
        Repuesto creado = repuestosService.save(repuesto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar repuesto por id")
    public ResponseEntity<Repuesto> buscar(@PathVariable Integer id) {
        Optional<Repuesto> repuesto = repuestosService.findById(id);
        return repuesto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar repuesto")
    public ResponseEntity<Repuesto> actualizar(@PathVariable Integer id, @RequestBody Repuesto repuesto) {
        Optional<Repuesto> existenteOpt = repuestosService.findById(id);
        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Repuesto existente = existenteOpt.get();
        existente.setCodigo365(repuesto.getCodigo365());
        existente.setNombre(repuesto.getNombre());
        existente.setCodigoBarras(repuesto.getCodigoBarras());
        existente.setStockActual(repuesto.getStockActual());
        existente.setUbicacion(repuesto.getUbicacion());
        existente.setCategoria(repuesto.getCategoria());

        Repuesto actualizado = repuestosService.save(existente);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar repuesto")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            repuestosService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
