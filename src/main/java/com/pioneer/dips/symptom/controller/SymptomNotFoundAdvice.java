package com.pioneer.dips.symptom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SymptomNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(SymptomNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String symptomNotFoundHandler(SymptomNotFoundException ex) {
		return ex.getMessage();
	}
}
