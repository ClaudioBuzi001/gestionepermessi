package it.prova.gestionepermessi.service;

import java.util.List;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteService {
	public List<Dipendente> listAllUtenti() ;

	public Dipendente caricaSingoloDipendente(Long id);
	
	public Dipendente caricaSingoloDipendenteConUtente(Long id);

	public void aggiorna(Dipendente dipendenteInstance);

	public void inserisciNuovo(Dipendente dipendenteInstance);

	public void rimuovi(Dipendente dipendenteInstance);
	
	//TODO 
	//metodo eager con richieste Di Permess
	


}
