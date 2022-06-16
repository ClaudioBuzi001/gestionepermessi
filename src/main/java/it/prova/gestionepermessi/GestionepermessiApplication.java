package it.prova.gestionepermessi;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.SessoDipendente;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;

@SpringBootApplication
public class GestionepermessiApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	
	@Autowired
	private UtenteService utenteServiceInstance;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(GestionepermessiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Back Office User", "ROLE_BO_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Back Office User", "ROLE_BO_USER"));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Dipendente User", "ROLE_DIPENDENTE_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Dipendente User", "ROLE_DIPENDENTE_USER"));
		}
		
		
		//admin
		
		if (utenteServiceInstance.findByUsername("admin") == null) {
			//metodo che inserisci 
			Utente utente = new Utente( "admin", "admin");
			utente.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
			utente.setPassword(passwordEncoder.encode(utente.getPassword()));
			utente.setStato(StatoUtente.ATTIVO);
			
			
			Dipendente dipendentAdmin = new Dipendente("Ivan", "Bendotti", "BNDIVN02P05H501E", "admin@prova.it",
					new SimpleDateFormat("dd/MM/yyyy").parse("05/02/1990"),
					new SimpleDateFormat("dd/MM/yyyy").parse("25/07/2005"), SessoDipendente.MASCHIO);
			
			
			utente.setDipendente(dipendentAdmin);
			dipendentAdmin.setUtente(utente);
			utenteServiceInstance.inserisciUtenteEDipendenteCollegandoli(utente, dipendentAdmin);
			
		}
		
		//backOffice
		if (utenteServiceInstance.findByUsername("backOffice") == null) {
			//metodo che inserisci 
			Utente utente = new Utente( "backOffice", "backOffice");
			utente.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Back Office User", "ROLE_BO_USER"));
			utente.setPassword(passwordEncoder.encode(utente.getPassword()));
			utente.setStato(StatoUtente.ATTIVO);
			
			
			Dipendente dipendentAdmin = new Dipendente("Giorgio", "Marcellotti", "BNDIVN02P05H501E", "backOffice@prova.it",
					new SimpleDateFormat("dd/MM/yyyy").parse("05/02/1990"),
					new SimpleDateFormat("dd/MM/yyyy").parse("25/07/2005"), SessoDipendente.MASCHIO);
			
			
			utente.setDipendente(dipendentAdmin);
			dipendentAdmin.setUtente(utente);
			utenteServiceInstance.inserisciUtenteEDipendenteCollegandoli(utente, dipendentAdmin);
			
		}
		
		//DIPENDNET
		
		
		
	}

}



















