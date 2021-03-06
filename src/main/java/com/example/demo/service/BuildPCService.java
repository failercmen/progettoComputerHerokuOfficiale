package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BuildPC;

import com.example.demo.repository.BuildPCRepository;

@Service
public class BuildPCService {

	@Autowired
	private BuildPCRepository buildRepository; 
	

	@Transactional
	public BuildPC inserisci(BuildPC build) {
		return buildRepository.save(build);
	}

	@Transactional
	public List<BuildPC> tutti() {
		return (List<BuildPC>) buildRepository.findAll();
	}

	@Transactional
	public BuildPC buildPerId(Long id) {
		Optional<BuildPC> optional = buildRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExistsByNome(BuildPC build) {
		List<BuildPC> builds = this.buildRepository.findByNome(build.getNome());
		if (builds.size() > 0)
			return true;
		else 
			return false;
	}
	

	@Transactional
	public void deleteById(Long id) {
		buildRepository.deleteById(id);
	}
	
	
	@Transactional
	public void updateBuild(Long idBuildPC, BuildPC buildNuovo) {
		// prova a inserire un buffet e tutte le sue variabili,
		// prendendo di riferimento queste setti tutte le variabili come quello nuovo
		BuildPC buildPC = this.buildPerId(idBuildPC);
		
		buildPC.setNome(buildNuovo.getNome());
		buildPC.setDescrizione(buildNuovo.getDescrizione());		
//		for(Componente c: buildPC.getComponenti()) {
//			c.getBuildsComponenti().add(buildPC);
//		}
//		
//		for(Periferica p: buildPC.getPeriferiche()) {
//			p.getBuildsPeriferiche().add(buildPC);
//		}
		
		buildRepository.save(buildPC);
	}
	

}
