package com.evilla.test.solicitud.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SolicitudValidator implements Validator {

	@Autowired
	@Qualifier("defaultValidator")
	private Validator validator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		validator.validate(target, errors);
	}

}
