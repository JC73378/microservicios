package com.partasign.cl.partsasign_ms.Assemblers;

import com.partasign.cl.partsasign_ms.Controller.RepuestosControllerV2;
import com.partasign.cl.partsasign_ms.Model.Repuesto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RepuestoModelAssembler implements RepresentationModelAssembler<Repuesto, EntityModel<Repuesto>> {
    @Override
    public EntityModel<Repuesto> toModel(Repuesto repuesto) {
        return EntityModel.of(
                repuesto,
                linkTo(methodOn(RepuestosControllerV2.class).getById(repuesto.getId())).withSelfRel(),
                linkTo(methodOn(RepuestosControllerV2.class).getAll()).withRel("repuestos")
        );
    }
}
