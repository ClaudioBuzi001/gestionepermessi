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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestePermesso;
import it.prova.gestionepermessi.service.AttachmentService;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RichiestePermessoService;

@Controller
@RequestMapping(value = "/richiestaPermesso")
public class RichiestaPermessoController {

	@Autowired
	private RichiestePermessoService richiesteService;

	@Autowired
	private DipendenteService dipendenteService;

	@Autowired
	private AttachmentService attachmentService;

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

	// Implemento il search di RichiestaPermesso

	@PostMapping("/list")
	public String listRichieste(RichiestaPermessoDTO richiestaExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Long myId = dipendenteService.trovaPerUsername(auth.getName()).getId();

		// Prendo tutte le richieste che corrispondono al example, poi le filtro
		// prendendo solo quelle con l id del mio dipendente Loggato
		List<RichiestePermesso> richieste = richiesteService.findByExample(richiestaExample, pageNo, pageSize, sortBy)
				.getContent().stream().filter(richiesta -> richiesta.getDipendente().getId() == myId)
				.collect(Collectors.toList());

		model.addAttribute("richieste_list_attribute",
				RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModelList(richieste));
		return "richiestaPermesso/list";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("insert_richiesta_attr", new RichiestaPermessoDTO());
		return "richiestaPermesso/insert";
	}

	// per la validazione devo usare i groups in quanto nella insert devo validare
	// la pwd, nella edit no
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_richiesta_attr") RichiestaPermessoDTO richiestaDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (richiestaDTO.getDataFine() != null && richiestaDTO.getDataInizio().after(richiestaDTO.getDataFine()))
			result.rejectValue("dataInizio", null,
					"Errore, la data di Inzio del permesso ?? dopo la data Di Fine, CORREGGERE:");

		if (result.hasErrors() ) {
			if(richiestaDTO.getAttachment().getNomeFile() == null) {
			}else {
				return "richiestaPermesso/insert";
			}
		}

			
		Attachment myAttachment = null;

		if (richiestaDTO.getAttachment() != null && richiestaDTO.getAttachment().getNomeFile() != null && richiestaDTO.getAttachment().getNomeFile().length() > 3) {
			myAttachment = attachmentService.inserisciNuovo(richiestaDTO.getAttachment().buildAttachementFromDTO());
		}

		//Mi setto alla richiesta anche il dipendente che ?? l utente loggato
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Dipendente myDipendente = dipendenteService.trovaPerUsername(auth.getName());
		
		//Mi trasformo richiestaDTO in model, ci aggiungo il dipendente
		RichiestePermesso richiesta = richiestaDTO.buildRichiestaPermessoFromDTO();
		richiesta.setDipendente(myDipendente);
		if(myAttachment != null) {
			richiesta.setAttachment(myAttachment);
		}
		
		//Salvo nel db la richiesta
		richiesteService.inserisciRichiestaEGeneraMessaggio(richiesta);
	
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/richiestaPermesso";
	}
	
	
	
	@GetMapping("/show/{idRichiesta}")
	public String show(@PathVariable(required = true) Long idRichiesta, Model model) {
		model.addAttribute("show_richiesta_attr", richiesteService.caricaSingolaEager(idRichiesta));
		return "richiestaPermesso/show";
	}

	@GetMapping("/delete/{idRichiesta}")
	public String delete(@PathVariable(required = true) Long idRichiesta, Model model) {
		model.addAttribute("delete_richiesta_attr", richiesteService.caricaSingolaEager(idRichiesta));
		return "richiestaPermesso/delete";
	}
	
	
	@PostMapping("/remove")
	public String remove(@RequestParam(required = true) Long idRichiesta, RedirectAttributes redirectAttrs) {
		
		richiesteService.rimuoviPerId(idRichiesta);
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/richiestaPermesso";
	}
	
	
	@GetMapping("/searchBO")
	public String searchDipendenteBO(Model model) {
		model.addAttribute("search_richiestapermesso_dipendente_attr", DipendenteDTO.createDipendenteDTOListFromModelList(dipendenteService.listAllDipendenti()));
		return "richiestaPermesso/searchBO";
	}
	
	@GetMapping("/listBO")
	public ModelAndView listAllDipendentiBO() {
		ModelAndView mv = new ModelAndView();
		List<RichiestePermesso> richieste = richiesteService.listAllRichiestePermesso();
		mv.addObject("richieste_list_attribute", richieste);
		mv.setViewName("richiestaPermesso/list");
		return mv;
	}

	@GetMapping("/showBO/{idRichiesta}")
	public String showBO(@PathVariable(required = true) Long idRichiesta, Model model) {
		model.addAttribute("show_richiesta_attr", richiesteService.caricaSingolaEager(idRichiesta));
		return "richiestaPermesso/showBO";
	}
	
	
	@PostMapping("/approva")
	public String approva(@RequestParam(required = true) Long idRichiesta, RedirectAttributes redirectAttrs) {
		
		richiesteService.approva(idRichiesta);
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/richiestaPermesso/listBO";
	}
	
	@PostMapping("/rifiuta")
	public String rifuta(@RequestParam(required = true) Long idRichiesta, RedirectAttributes redirectAttrs) {
		
		richiesteService.rifuta(idRichiesta);
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/richiestaPermesso/listBO";
	}
	

}





















