package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Periferica {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank // no spazi vuoti o bianchi
	private String nome;

	@NotBlank
	private String descrizione;

	@NotBlank
	private String tipologia;

	private float prezzo;

	// nuovo
	@ManyToMany
	private List<BuildPC> buildsPeriferiche;

	public List<BuildPC> getBuildsPeriferiche() {
		return buildsPeriferiche;
	}

	public void setBuildsPeriferiche(List<BuildPC> buildsPeriferiche) {
		this.buildsPeriferiche = buildsPeriferiche;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

}
