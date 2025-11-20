package com.partasign.cl.partasign_ot_ms.Controller;

import com.partasign.cl.partasign_ot_ms.Assemblers.OtProgramadaModelAssembler;
import com.partasign.cl.partasign_ot_ms.Model.OrdenTrabajoProgramada;
import com.partasign.cl.partasign_ot_ms.Service.OtProgramadaService;
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
@RequestMapping("/api/v2/ot-programadas")
public class OtProgramadaControllerV2 {

    @Autowired
    private OtProgramadaService service;

    @Autowired
    private OtProgramadaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<OrdenTrabajoProgramada>> getAll() {
        List<EntityModel<OrdenTrabajoProgramada>> lista = service.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                lista,
                linkTo(methodOn(OtProgramadaControllerV2.class).getAll()).withSelfRel()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<OrdenTrabajoProgramada>> getById(@PathVariable Integer id) {
        Optional<OrdenTrabajoProgramada> ot = service.findById(id);
        return ot.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<OrdenTrabajoProgramada>> create(@RequestBody OrdenTrabajoProgramada ot) {
        OrdenTrabajoProgramada creada = service.save(ot);
        return ResponseEntity
                .created(linkTo(methodOn(OtProgramadaControllerV2.class).getById(creada.getId())).toUri())
                .body(assembler.toModel(creada));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<OrdenTrabajoProgramada>> update(@PathVariable Integer id, @RequestBody OrdenTrabajoProgramada ot) {
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
        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
