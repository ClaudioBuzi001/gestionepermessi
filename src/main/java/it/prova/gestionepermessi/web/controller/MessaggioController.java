package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.service.MessaggioService;


@Controller
@RequestMapping(value = "/messaggio")
public class MessaggioController {
	
	@Autowired
	private MessaggioService messaggioService;
	
	
	@GetMapping
	public ModelAndView listAllMessaggi() {
		ModelAndView mv = new ModelAndView();
		List<Messaggio> messaggio = messaggioService.listaTutti();
		mv.addObject("messaggio_list_attribute", messaggio);
		mv.setViewName("messaggio/list");
		return mv;
	}
	
	@GetMapping("/show/{idMessaggio}")
	public String show(@PathVariable(required = true) Long idMessaggio, Model model) {
		//Setto data lettura
		messaggioService.settaDataLettura(idMessaggio);
		
		model.addAttribute("show_messaggio_attr", messaggioService.caricaMessaggioEager(idMessaggio));
		return "messaggio/show";
	}
	
	

}




















