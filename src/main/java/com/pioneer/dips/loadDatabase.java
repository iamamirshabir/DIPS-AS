package com.pioneer.dips;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pioneer.dips.symptom.model.Symptom;
import com.pioneer.dips.symptom.repository.symptomRepository;

@Configuration
public class loadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(loadDatabase.class);
	
	  @Bean
	  CommandLineRunner initDatabase(symptomRepository repository) {

	    return args -> {
	      log.info("Preloading " + repository.save(new Symptom("Sneezing", 1)));
	      log.info("Preloading " + repository.save(new Symptom("Headache", 2)));
	    };
	  }

}
