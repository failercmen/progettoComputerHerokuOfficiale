package com.example.demo.controller.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.BuildPC;
import com.example.demo.service.BuildPCService;

@Component
public class BuildPCValidator implements Validator {
	
	@Autowired
	private BuildPCService  buildPCservice;

	final Integer MAX_NAME_LENGTH = 30;
	final Integer MIN_NAME_LENGTH = 2;
	final Integer MAX_DESC_LENGTH = 500;
	final Integer MIN_DESC_LENGTH = 2;
	
	@Override
	public boolean supports(Class<?> pclass) {
		return BuildPC.class.equals(pclass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BuildPC build = (BuildPC) target;
		String nome = build.getNome().trim();
		String desc = build.getDescrizione().trim();

		if (nome.isEmpty())
			errors.rejectValue("nome", "required");
		
		else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
			errors.rejectValue("nome", "size");
		
		else if (desc.isEmpty())
			errors.rejectValue("descrizione", "required");
	
		else if (nome.length() < MIN_DESC_LENGTH || nome.length() > MAX_DESC_LENGTH)
			errors.rejectValue("descrizione", "size");

		//TODO controlla se funziona bene
				else if(this.buildPCservice.alreadyExists(build))
					errors.rejectValue("nome","duplicate");
	}
	
}
