package it.prova.gestionepermessi.service;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioService {
	
	public void inserisciNuovo(Messaggio messaggio);
	
	public Messaggio caricaPerIdRichiesta(Long id);
	
	public void rimuovi(Messaggio messaggio);

}
