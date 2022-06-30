package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.validator.BuildPCValidator;
import com.example.demo.model.BuildPC;
import com.example.demo.service.BuildPCService;

@Controller
public class BuildPCController {

	@Autowired
	private BuildPCService buildService;
	
    @Autowired
    private BuildPCValidator buildValidator;
    
    
    @RequestMapping(value = "/buildPC", method = RequestMethod.GET)
    public String getListaBuild(Model model) {
    		model.addAttribute("listaBuild", this.buildService.tutti());
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
        	this.buildService.inserisci(buildPC);
            model.addAttribute("listaBuild", this.buildService.tutti());
            return "listaBuild.html";
        }
        return "buildPCForm.html";
    }
    
    
    @RequestMapping(value = "/buildPC/{id}", method = RequestMethod.GET)
    public String getChef(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("chef", this.buildService.buildPerId(id));
    	return "chef";
    }
    
    
}
