package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.dto.UtenteSearchDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;


public interface UtenteService {
	public List<Utente> listAllUtenti() ;

	public Utente caricaSingoloUtente(Long id);
	
	public Utente caricaSingoloUtenteConRuoli(Long id);

	public void aggiorna(Utente utenteInstance);

	public void inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Utente utenteInstance);

	public List<Utente> findByExample(Utente example);
	
	public Utente findByUsernameAndPassword(String username, String password);
	
	public Utente eseguiAccesso(String username, String password);
	
	public void changeUserAbilitation(Long utenteInstanceId);
	
	public Utente findByUsername(String username);
	
	public void resetPasswordService(Long idUtente);
	
	public int modificaPassword(Long idUtente, String vecchiaPassword, String nuovaPassword, String nuovaPasswordConferma);

	public Page<Utente> findByExample(UtenteSearchDTO example, Integer pageNo, Integer pageSize, String sortBy) ;
	
	public void inserisciUtenteEDipendenteCollegandoli(Utente utente, Dipendente dipendente);
	
	public Utente caricaUtenteEager(Long id);
	
	public void aggiornaPerEdit(Utente utente);

}

























