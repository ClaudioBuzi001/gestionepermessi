package it.prova.gestionepermessi.service;

import java.util.Date;
import java.util.List;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioService {
	
	public List<Messaggio> listaTutti();
	
	public void inserisciNuovo(Messaggio messaggio);
	
	public Messaggio caricaPerIdRichiesta(Long id);
	
	public void rimuovi(Messaggio messaggio);
	
	public void settaDataLettura(Long id);
	
	public Messaggio caricaMessaggioEager(Long id);

}
