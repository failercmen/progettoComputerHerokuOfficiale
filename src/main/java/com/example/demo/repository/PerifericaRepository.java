package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Periferica;

public interface PerifericaRepository extends CrudRepository<Periferica, Long> {

	public List<Periferica> findByNome(String nome);
	
}
