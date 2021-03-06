package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.DipendenteSearchDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;

public interface DipendenteService {
	public List<Dipendente> listAllDipendenti() ;

	public Dipendente caricaSingoloDipendente(Long id);
	
	public Dipendente caricaSingoloDipendenteConUtente(Long id);

	public void aggiorna(Dipendente dipendenteInstance);

	public void inserisciNuovo(Dipendente dipendenteInstance);

	public void rimuovi(Dipendente dipendenteInstance);
	
	public Page<Dipendente> findByExample(DipendenteSearchDTO example, Integer pageNo, Integer pageSize, String sortBy);
	
	//TODO 
	//metodo eager con richieste Di Permess
	
	public void inserisciDipendenteEUtente(Dipendente dipendente);
	
	public void modificaDipendente(Dipendente dipendenteInstance);

	public Dipendente trovaPerUsername(String usernames);
}
