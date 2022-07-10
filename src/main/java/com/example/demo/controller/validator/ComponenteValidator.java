package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Componente;
import com.example.demo.service.ComponenteService;

@Component
public class ComponenteValidator implements Validator {

	@Autowired
	private ComponenteService componenteService;

	final Integer MAX_NAME_LENGTH = 50;
	final Integer MIN_NAME_LENGTH = 2;
	final Integer MAX_DESC_LENGTH = 500;
	final Integer MIN_DESC_LENGTH = 2;
	final Integer MAX_TIP_LENGTH = 50;
	final Integer MIN_TIP_LENGTH = 2;

	@Override
	public boolean supports(Class<?> pclass) {
		return Componente.class.equals(pclass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Componente comp = (Componente) target;
		String nome = comp.getNome().trim();
		String desc = comp.getDescrizione().trim();
		String tip = comp.getTipologia();

		if (nome.isEmpty())
			errors.rejectValue("nome", "required");

		else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
			errors.rejectValue("nome", "size");

		else if (desc.isEmpty())
			errors.rejectValue("descrizione", "required");

		else if (desc.length() < MIN_DESC_LENGTH || nome.length() > MAX_DESC_LENGTH)
			errors.rejectValue("descrizione", "size");

		if (tip.isEmpty())
			errors.rejectValue("tipologia", "required");

		else if (nome.length() < MIN_TIP_LENGTH || nome.length() > MAX_TIP_LENGTH)
			errors.rejectValue("tipologia", "size");

		// verifico se presente un duplicato
		else if (this.componenteService.alreadyExistsByNome(comp)
				&& this.componenteService.alreadyExistsByTipologia(comp))
			errors.rejectValue("nome", "duplicate");
		
		//nuovo
		else if( !tip.equals("case") && !tip.equals("scheda madre") && !tip.equals("scheda video")
			&&	!tip.equals("cpu") && !tip.equals("ram") &&  !tip.equals("alimentatore") 
			&&	!tip.equals("cooling")&& !tip.equals("memoria"))
			errors.rejectValue("tipologia", "inesistente");
	}

}
