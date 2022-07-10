package com.example.demo.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Componente;

public interface ComponenteRepository extends CrudRepository<Componente, Long> {

	public List<Componente> findByNome(String nome);
	
	public List<Componente> findByTipologia(String tipologia);
	
}
