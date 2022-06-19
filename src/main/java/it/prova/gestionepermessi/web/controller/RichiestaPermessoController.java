package it.prova.gestionepermessi.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestePermesso;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RichiestePermessoService;


@Controller
@RequestMapping(value = "/richiestaPermesso")
public class RichiestaPermessoController {
	
	@Autowired
	private RichiestePermessoService richiesteService;
	
	@Autowired
	private DipendenteService dipendenteService;
	
	
	@GetMapping
	public ModelAndView listAllDipendenti() {
		ModelAndView mv = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		List<RichiestePermesso> richieste = richiesteService.findMyRichiestePermesso(auth.getName());
		mv.addObject("richieste_list_attribute", richieste);
		mv.setViewName("richiestaPermesso/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchDipendente(Model model) {
		return "richiestaPermesso/search";
	}
	
	//Implemento il search di RichiestaPermesso

	@PostMapping("/list")
	public String listRichieste(RichiestaPermessoDTO richiestaExample,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy, ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		
		Long myId = dipendenteService.trovaPerUsername(auth.getName()).getId();
		
		//Prendo tutte le richieste che corrispondono al example, poi le filtro prendendo solo quelle con l id del mio dipendente Loggato
		List<RichiestePermesso> richieste = richiesteService.findByExample(richiestaExample, pageNo, pageSize, sortBy)
				.getContent().stream().filter(richiesta -> richiesta.getDipendente().getId() == myId).collect(Collectors.toList());

		model.addAttribute("richieste_list_attribute", RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModelList(richieste));
		return "richiestaPermesso/list";
	}

	
//	-metodo insert
//
//	1)gli passo un nuovo richiestapermessoDTO
//		Che dentro ha tutto quello che ci serve
//
//-Insert.jsp
//	Metto tutti i campi intererssati
//
//-save
//	Binding
//
//	Controlli
//
//	Salvo la richiesta
//	
//	Mi creo un messaggio, che conterra l id di tale richiesta


	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("insert_richiesta_attr", new RichiestaPermessoDTO());
		return "richiestaPermesso/insert";
	}

//	// per la validazione devo usare i groups in quanto nella insert devo validare
//	// la pwd, nella edit no
//	@PostMapping("/save")
//	public String save(@Valid @ModelAttribute("insert_dipendente_attr") DipendenteDTO dipendenteDTO,
//			BindingResult result, Model model, RedirectAttributes redirectAttrs) {
//
//		if (dipendenteDTO.getDataNascita() != null && dipendenteDTO.getDataAssunzione() != null
//				&& dipendenteDTO.getDataNascita().after(dipendenteDTO.getDataAssunzione()))
//			result.rejectValue("dataNascita", null, "Errore, la data di Nascita è dopo la data Assunzione");
//
//		if (dipendenteDTO.getSesso() == null)
//			result.rejectValue("sesso", null, "Errore, Il sesso non è stato inserito.");
//
//		if (result.hasErrors()) {
//			return "dipendente/insert";
//		}
//
//		// Mi creo il model dal dto Che
//		Dipendente dipendenteDaInserire = dipendenteDTO.buildModelFromDTO();
//
//		// Chiamiamo il metodo del service che mi costruisce l utente e mi inserisce le
//		// cazzate
//		dipendenteService.inserisciDipendenteEUtente(dipendenteDaInserire);
//
//		// TODO CONTROLLARE SE GIUSTO
//
//		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
//		return "redirect:/dipendente";
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
