package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.DipendenteSearchDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.service.DipendenteService;


@Controller
@RequestMapping(value = "/dipendente")
public class DipendenteController {

	@Autowired
	private DipendenteService dipendenteService;
	

	@GetMapping
	public ModelAndView listAllDipendenti() {
		ModelAndView mv = new ModelAndView();
		List<Dipendente> dipendente = dipendenteService.listAllDipendenti();
		mv.addObject("dipendente_list_attribute", dipendente);
		mv.setViewName("dipendente/list");
		return mv;
	}

	
	@GetMapping("/search")
	public String searchDipendente(Model model) {
		return "dipendente/search";
	}

	@PostMapping("/list")
	public String listDipendente( DipendenteSearchDTO dipendenteExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {
		
		List<Dipendente> dipendenti = dipendenteService
				.findByExample(dipendenteExample, pageNo, pageSize, sortBy).getContent();

		model.addAttribute("dipendente_list_attribute", DipendenteDTO.createDipendenteDTOListFromModelList(dipendenti));
		return "dipendente/list";
	}

	@GetMapping("/show/{idDipendente}")
	public String show(@PathVariable(required = true) Long idDipendente, Model model) {
		model.addAttribute("show_dipendente_attr", dipendenteService.caricaSingoloDipendente(idDipendente));
		return "dipendente/show";
	}
}



































