package com.pioneer.dips.symptom.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pioneer.dips.symptom.model.Symptom;
import com.pioneer.dips.symptom.repository.symptomRepository;

@RestController
public class symptomController {
	
	private final symptomRepository repository;
	
	  symptomController(symptomRepository repository) {
		    this.repository = repository;
		  }
	  
	  @GetMapping("/symptom")
	  CollectionModel<EntityModel<Symptom>> all(){
		  List<EntityModel<Symptom>> symptoms = repository.findAll().stream().map(symptom -> EntityModel.of(symptom, 
				  linkTo(methodOn(symptomController.class).one(symptom.getSYMPTOM_ID())).withSelfRel(),
				  linkTo(methodOn(symptomController.class).all()).withRel("symptom")))
				  .collect(Collectors.toList());
		  return CollectionModel.of(symptoms,
				  linkTo(methodOn(symptomController.class).all()).withSelfRel());
	  }
	  
	  @PostMapping("/symptom")
	  Symptom newSymptom(@RequestBody Symptom newSymptom ) {
		  return repository.save(newSymptom);
	   }
	  
	  @GetMapping("/symptom/{id}")
	  EntityModel<Symptom> one(@PathVariable Long id) {
		  Symptom symptom = repository.findById(id)
				  .orElseThrow(() -> new SymptomNotFoundException(id));
		  return EntityModel.of(symptom, 
				  linkTo(methodOn(symptomController.class).one(id)).withSelfRel(),
				  linkTo(methodOn(symptomController.class).all()).withRel("symptom"));
	  }
	  
	  @PutMapping("symptom/{id}")
	  Symptom replaceSymptom(@RequestBody Symptom newSymptom, @PathVariable Long id) {
		  return repository.findById(id)
				  .map(symptom ->{
					  symptom.setSYMPTOM_NAME(newSymptom.getSYMPTOM_NAME());
					  symptom.setSYMPTOM_CATEGORY_ID(newSymptom.getSYMPTOM_CATEGORY_ID());
					  return repository.save(symptom);
				  })
				  .orElseGet(() ->{
					  newSymptom.setSYMPTOM_ID(id);
					  return repository.save(newSymptom);
				  });
  
	  }
	  
	  @DeleteMapping("/symptom/{id}")
	  void deleteSymptom(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
	  

}
