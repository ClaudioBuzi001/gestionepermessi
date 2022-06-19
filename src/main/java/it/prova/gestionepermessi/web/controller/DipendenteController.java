package it.prova.gestionepermessi.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String listDipendente(DipendenteSearchDTO dipendenteExample,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy, ModelMap model) {

		List<Dipendente> dipendenti = dipendenteService.findByExample(dipendenteExample, pageNo, pageSize, sortBy)
				.getContent();

		model.addAttribute("dipendente_list_attribute", DipendenteDTO.createDipendenteDTOListFromModelList(dipendenti));
		return "dipendente/list";
	}

	@GetMapping("/show/{idDipendente}")
	public String show(@PathVariable(required = true) Long idDipendente, Model model) {
		model.addAttribute("show_dipendente_attr", dipendenteService.caricaSingoloDipendente(idDipendente));
		return "dipendente/show";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("insert_dipendente_attr", new DipendenteDTO());
		return "dipendente/insert";
	}

	// per la validazione devo usare i groups in quanto nella insert devo validare
	// la pwd, nella edit no
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_dipendente_attr") DipendenteDTO dipendenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (dipendenteDTO.getDataNascita() != null && dipendenteDTO.getDataAssunzione() != null
				&& dipendenteDTO.getDataNascita().after(dipendenteDTO.getDataAssunzione()))
			result.rejectValue("dataNascita", null, "Errore, la data di Nascita è dopo la data Assunzione");

		if (dipendenteDTO.getSesso() == null)
			result.rejectValue("sesso", null, "Errore, Il sesso non è stato inserito.");

		if (result.hasErrors()) {
			return "dipendente/insert";
		}

		// Mi creo il model dal dto Che
		Dipendente dipendenteDaInserire = dipendenteDTO.buildModelFromDTO();

		// Chiamiamo il metodo del service che mi costruisce l utente e mi inserisce le
		// cazzate
		dipendenteService.inserisciDipendenteEUtente(dipendenteDaInserire);

		// TODO CONTROLLARE SE GIUSTO

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/dipendente";
	}

//	1)edit(idDipendente) COMPLETO
//	   1.a)Carico il dipendente, lo converto in dto e lo mando alla pagina jsp
//
//	2)edit.jsp  //QUI
//	   2.a)Meetto i vari campi da modificare
//
//	3)update
//	   3.a)Validazione!
//
//	   3.b)Modificare i dati del dipendente
//
//	   3.c)Modificare opportunamente i dati dell utente(NON DEVI CREARTENE UNO NUOVO!) IMPORTANTE

	@GetMapping("/edit/{idDipendente}")
	public String edit(@PathVariable(required = true) Long idDipendente, Model model) {
		model.addAttribute("edit_dipendente_attr",
				DipendenteDTO.buildDipendenteDTOFromModel(dipendenteService.caricaSingoloDipendente(idDipendente)));

		return "dipendente/edit";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("edit_dipendente_attr") DipendenteDTO dipendenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (dipendenteDTO.getDataNascita() != null && dipendenteDTO.getDataAssunzione() != null
				&& dipendenteDTO.getDataNascita().after(dipendenteDTO.getDataAssunzione()))
			result.rejectValue("dataNascita", null, "Errore, la data di Nascita è dopo la data Assunzione");

		if (dipendenteDTO.getSesso() == null)
			result.rejectValue("sesso", null, "Errore, Il sesso non è stato inserito.");

		if (result.hasErrors()) {
			return "dipendente/edit";
		}

		Dipendente dipendenteDaInserire = dipendenteDTO.buildModelFromDTO();

		dipendenteService.modificaDipendente(dipendenteDaInserire);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/dipendente";
	}

}
