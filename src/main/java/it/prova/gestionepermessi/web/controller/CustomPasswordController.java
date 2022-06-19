package it.prova.gestionepermessi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.service.UtenteService;

@Controller
@RequestMapping(value = "/CustomPassword")
public class CustomPasswordController {

	@Autowired
	private UtenteService utenteService;

	// metodo ResetPasswordAd
	@GetMapping("/ReindirizzaCambioPassword/{idUtente}")
	public String reindirizzaCambioPassword(@PathVariable(required = true) Long idUtente, Model model) {
		UtenteDTO utenteConId = new UtenteDTO();
		utenteConId.setId(idUtente);
		model.addAttribute("utente_id_password", utenteConId);

		return "/resetPassword";

	}

	@PostMapping("ResetPasswordCustom")
	public String ResetPasswordCustom(@RequestParam(name = "idUtente", required = true) Long idUtente,
			@RequestParam(name = "vecchiaPassword", required = true) String vecchiaPassword,
			@RequestParam(name = "nuovaPassword", required = true) String nuovaPassword,
			@RequestParam(name = "nuovaPasswordConferma") String nuovaPasswordConferma,
			RedirectAttributes redirectAttrs) {

		int result = utenteService.modificaPassword(idUtente, vecchiaPassword, nuovaPassword, nuovaPasswordConferma);

		if (result == 0) {
			redirectAttrs.addFlashAttribute("errorMessage", "Errore, La vecchia password non è corretta");
			return "/resetPassword";
		} else if (result == 1) {
			redirectAttrs.addFlashAttribute("errorMessage",
					"Errore, La Conferma password non è uguale alla nuova password!");
			return "/resetPassword";
		}

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente!");
		return "/index";
	}
}
