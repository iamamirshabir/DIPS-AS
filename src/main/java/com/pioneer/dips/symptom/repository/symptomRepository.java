package com.pioneer.dips.symptom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pioneer.dips.symptom.model.Symptom;

public interface symptomRepository extends JpaRepository<Symptom, Long> {

}
