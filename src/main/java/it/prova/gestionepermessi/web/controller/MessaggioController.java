package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	
	
	@GetMapping(value = "/presentiMessaggiNonLetti", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> checkPresenzaMessaggiNonLetti() {

		if (!messaggioService.lisAllMessaggiNonLetti().isEmpty())
			return new ResponseEntity<String>(HttpStatus.OK);
		else
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/search")
	public String searchDipendente(Model model) {
		return "messaggio/search";
	}

	@PostMapping("/list")
	public String listMessaggi(Messaggio example,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy, ModelMap model) {

		List<Messaggio> messaggi = messaggioService.findByExample(example, pageNo, pageSize, sortBy)
				.getContent();

		model.addAttribute("messaggio_list_attribute", messaggi);
		return "messaggio/list";
	}
	

}




















