package com.pioneer.dips.symptom.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pioneer.dips.symptom.model.Symptom;
import com.pioneer.dips.symptom.model.SymptomModelAssembler;
import com.pioneer.dips.symptom.repository.symptomRepository;

@RestController
public class symptomController {
	
	private final symptomRepository repository;
	private final SymptomModelAssembler assembler; 
	
	  symptomController(symptomRepository repository, SymptomModelAssembler assembler) {
		    this.repository = repository;
		    this.assembler = assembler;
		  }
	  
	  @GetMapping("/symptom")
	public
	  CollectionModel<EntityModel<Symptom>> all(){
		  List<EntityModel<Symptom>> symptoms = repository.findAll().stream()
				  .map(assembler :: toModel)
				  .collect(Collectors.toList());
		  return CollectionModel.of(symptoms,
				  linkTo(methodOn(symptomController.class).all()).withSelfRel());
	  }
	  
	  @PostMapping("/symptom")
	  ResponseEntity<?> newSymptom(@RequestBody Symptom newSymptom ) {
		EntityModel<Symptom> symptom = assembler.toModel(repository.save(newSymptom));
		  return ResponseEntity
				  .created(symptom.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(symptom);
	   
	  }
	  
	  @GetMapping("/symptom/{id}")
	public
	  EntityModel<Symptom> one(@PathVariable Long id) {
		  Symptom symptom = repository.findById(id)
				  .orElseThrow(() -> new SymptomNotFoundException(id));
		  return assembler.toModel(symptom);
	  }
	  
	  @PutMapping("symptom/{id}")
	  ResponseEntity<?> replaceSymptom(@RequestBody Symptom newSymptom, @PathVariable Long id) {
		  Symptom updatedSymptom = repository.findById(id)
				  .map(symptom ->{
					  symptom.setSYMPTOM_NAME(newSymptom.getSYMPTOM_NAME());
					  symptom.setSYMPTOM_CATEGORY_ID(newSymptom.getSYMPTOM_CATEGORY_ID());
					  return repository.save(symptom);
				  })
				  .orElseGet(() ->{
					  newSymptom.setSYMPTOM_ID(id);
					  return repository.save(newSymptom);
				  });
		  EntityModel<Symptom> symptoms = assembler.toModel(updatedSymptom);
		  
		  return ResponseEntity
				  .created(symptoms.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(symptoms);
					 
	  }
	  
	  @DeleteMapping("/symptom/{id}")
	  void deleteSymptom(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
	  

}
