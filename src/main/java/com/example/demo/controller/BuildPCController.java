package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.validator.BuildPCValidator;
import com.example.demo.model.BuildPC;
import com.example.demo.model.Componente;
import com.example.demo.model.Periferica;
import com.example.demo.service.BuildPCService;
import com.example.demo.service.ComponenteService;
import com.example.demo.service.PerifericaService;

@Controller
public class BuildPCController {

	@Autowired
	private BuildPCService buildService;

	@Autowired
	private BuildPCValidator buildValidator;

	@Autowired
	private ComponenteService componenteService;

	@Autowired
	private PerifericaService perifericaService;

	@RequestMapping(value = "/buildPC", method = RequestMethod.GET)
	public String getListaBuild(Model model) {
		model.addAttribute("ListaBuild", this.buildService.tutti());
		return "listaBuild.html";
	}

	@RequestMapping(value = "/admin/buildPC", method = RequestMethod.GET)
	public String addBuidPC(Model model) {
		model.addAttribute("buildPC", new BuildPC());
		return "buildPCForm.html";
	}

	@RequestMapping(value = "/admin/buildPC", method = RequestMethod.POST)
	public String addBuildPC(@ModelAttribute("buildPC") BuildPC buildPC, Model model, BindingResult bindingResult) {
		this.buildValidator.validate(buildPC, bindingResult);
		if (!bindingResult.hasErrors()) {

			// nuovo
			// for (Componente c : buildPC.getComponenti()) {
			// c.getBuilds().add(buildPC);
			// }

			this.buildService.inserisci(buildPC);
			model.addAttribute("listaBuild", this.buildService.tutti());
			return "listaBuild.html";
		}
		return "buildPCForm.html";
	}

	@RequestMapping(value = "/buildPC/{id}", method = RequestMethod.GET)
	public String getChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("build", this.buildService.buildPerId(id));
		return "build";
	}

	@RequestMapping(value = "/buildForm", method = RequestMethod.GET)
	public String addBuffet(Model model) {
		model.addAttribute("buildPC", new BuildPC());
		return "buildForm.html";
	}

	@RequestMapping(value = "/listaBuild", method = RequestMethod.POST)
	public String addProdotto(@ModelAttribute("buildPC") BuildPC build, Model model, BindingResult BindingResult) {
		this.buildValidator.validate(build, BindingResult);

		if (!BindingResult.hasErrors()) {
			this.buildService.inserisci(build);
			model.addAttribute("ListaBuild", this.buildService.tutti());
			return "listaBuild.html";
		}
		
		return "buildForm.html";
	}

	@RequestMapping(value = "/build/{id}", method = RequestMethod.GET)
	public String addBuidPC(@PathVariable("id") Long id, Model model) {
		model.addAttribute("build", this.buildService.buildPerId(id));
		model.addAttribute("ListaComponenti", this.componenteService.tutti());
		model.addAttribute("ListaPeriferiche", this.perifericaService.tutti());
		model.addAttribute("Componenti", this.buildService.buildPerId(id).getComponenti());
		model.addAttribute("Periferiche", this.buildService.buildPerId(id).getPeriferiche());

		return "build.html";
	}

	
	@Transactional
	@GetMapping("/deleteBuild/{id}")
	public String deleteBuild(@PathVariable("id") Long id, Model model) {

		for (Componente c : buildService.buildPerId(id).getComponenti()) {
			c.getBuildsComponenti().remove(buildService.buildPerId(id));
		}
		
		for (Periferica p : buildService.buildPerId(id).getPeriferiche()) {
			p.getBuildsPeriferiche().remove(buildService.buildPerId(id));
		}
		
		buildService.buildPerId(id).setPrezzoTotale(0);

		buildService.deleteById(id);
		
		model.addAttribute("ListaBuild", this.buildService.tutti());

		return "listaBuild.html";
	}
	
	
	//nuovo
	@RequestMapping(value = "/updateBuild/{idBuildPC}", method = RequestMethod.GET)
	public String updateBuildEsistente(@PathVariable("idBuildPC") Long idBuildPC, Model model) {

		model.addAttribute("build", this.buildService.buildPerId(idBuildPC));
		
		return "buildUpdate.html";
	}
	
	
	
	//nuovo
	@RequestMapping(value = "/updateBuild/{idBuildPC}", method =
			RequestMethod.POST)
	public String updateBuildEsistente(@ModelAttribute BuildPC buildPC,@PathVariable("idBuildPC")
	Long idBuildPC,Model model) {
	//TODO ricorda di aggiungere il collegamento verso il buffet
							
		//lascialo
		this.buildService.updateBuild(idBuildPC, buildPC);
				
		
		model.addAttribute("ListaBuild", this.buildService.tutti());

		return "listaBuild.html";
	}
	

}
