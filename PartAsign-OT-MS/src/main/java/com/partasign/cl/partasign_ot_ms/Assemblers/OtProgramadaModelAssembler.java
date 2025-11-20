package com.partasign.cl.partasign_ot_ms.Assemblers;

import com.partasign.cl.partasign_ot_ms.Controller.OtProgramadaControllerV2;
import com.partasign.cl.partasign_ot_ms.Model.OrdenTrabajoProgramada;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OtProgramadaModelAssembler implements RepresentationModelAssembler<OrdenTrabajoProgramada, EntityModel<OrdenTrabajoProgramada>> {
    @Override
    public EntityModel<OrdenTrabajoProgramada> toModel(OrdenTrabajoProgramada ot) {
        return EntityModel.of(
                ot,
                linkTo(methodOn(OtProgramadaControllerV2.class).getById(ot.getId())).withSelfRel(),
                linkTo(methodOn(OtProgramadaControllerV2.class).getAll()).withRel("ot-programadas")
        );
    }
}
