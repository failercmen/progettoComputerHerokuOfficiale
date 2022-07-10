package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.BuildPC;

public interface BuildPCRepository extends CrudRepository<BuildPC, Long>{

	public List<BuildPC> findByNome(String nome);
}
