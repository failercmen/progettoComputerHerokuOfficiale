package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Azienda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank //no spazi vuoti o bianchi
	private String nome;
	
	@OneToMany
	private List<BuildPC> builds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<BuildPC> getBuilds() {
		return builds;
	}

	public void setBuilds(List<BuildPC> builds) {
		this.builds = builds;
	}
	
	
	
}
