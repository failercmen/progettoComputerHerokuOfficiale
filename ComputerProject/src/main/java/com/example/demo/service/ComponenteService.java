package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Componente;
import com.example.demo.repository.ComponenteRepository;

@Service
public class ComponenteService {

	@Autowired
	private ComponenteRepository componenteRepository; 
	
	@Transactional
	public Componente inserisci(Componente c) {
		return componenteRepository.save(c);
	}

	@Transactional
	public List<Componente> tutti() {
		return (List<Componente>) componenteRepository.findAll();
	}

	@Transactional
	public Componente componentePerId(Long id) {
		Optional<Componente> optional = componenteRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExistsByNome(Componente c) {
		List<Componente> componenti = this.componenteRepository.findByNome(c.getNome());
		if (componenti.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public boolean alreadyExistsByTipologia(Componente c) {
		List<Componente> componenti = this.componenteRepository.findByTipologia(c.getTipologia());
		if (componenti.size() > 0)
			return true;
		else 
			return false;
	}
				
		
		@Transactional
		public void deleteById(Long id) {
			componenteRepository.deleteById(id);
		}
		
		@Transactional
		public List<Componente> caseComponenti(Long id) {
			List<Componente> listaCase = this.tutti();
			
			for(Componente c : listaCase) {
				if(!c.getTipologia().equals("case"))
					listaCase.remove(c);
			}
			return listaCase;
		}
		
}
	