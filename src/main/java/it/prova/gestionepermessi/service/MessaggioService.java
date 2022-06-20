package it.prova.gestionepermessi.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.dto.DipendenteSearchDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioService {
	
	public List<Messaggio> listaTutti();
	
	public void inserisciNuovo(Messaggio messaggio);
	
	public Messaggio caricaPerIdRichiesta(Long id);
	
	public void rimuovi(Messaggio messaggio);
	
	public void settaDataLettura(Long id);
	
	public Messaggio caricaMessaggioEager(Long id);
	
	public List<Messaggio> lisAllMessaggiNonLetti();
	
	public Page<Messaggio> findByExample(Messaggio example, Integer pageNo, Integer pageSize,
			String sortBy) ;

}
