package com.pioneer.dips.symptom.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pioneer.dips.symptom.controller.symptomController;

@Component
public class SymptomModelAssembler implements RepresentationModelAssembler<Symptom, EntityModel<Symptom>> {

	@Override
	public EntityModel<Symptom> toModel (Symptom symptom){
	
		return EntityModel.of(symptom, 
				linkTo(methodOn(symptomController.class).one(symptom.getSYMPTOM_ID())).withSelfRel(),
				linkTo(methodOn(symptomController.class).all()).withRel("symptom"));
	}
}
