package com.partasign.cl.partsasign_ms.Controller;

import com.partasign.cl.partsasign_ms.Assemblers.RepuestoModelAssembler;
import com.partasign.cl.partsasign_ms.Model.Repuesto;
import com.partasign.cl.partsasign_ms.Service.RepuestosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/repuestos")
public class RepuestosControllerV2 {

    @Autowired
    private RepuestosService repuestosService;

    @Autowired
    private RepuestoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Repuesto>> getAll() {
        List<EntityModel<Repuesto>> repuestos = repuestosService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                repuestos,
                linkTo(methodOn(RepuestosControllerV2.class).getAll()).withSelfRel()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Repuesto>> getById(@PathVariable Integer id) {
        Optional<Repuesto> repuesto = repuestosService.findById(id);
        return repuesto.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Repuesto>> create(@RequestBody Repuesto repuesto) {
        Repuesto creado = repuestosService.save(repuesto);
        return ResponseEntity
                .created(linkTo(methodOn(RepuestosControllerV2.class).getById(creado.getId())).toUri())
                .body(assembler.toModel(creado));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Repuesto>> update(@PathVariable Integer id, @RequestBody Repuesto repuesto) {
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
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            repuestosService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
