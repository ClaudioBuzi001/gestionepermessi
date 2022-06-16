package it.prova.gestionepermessi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {
	
	@Autowired
	private DipendenteRepository repository;

	@Override
	public List<Dipendente> listAllUtenti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dipendente caricaSingoloDipendente(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dipendente caricaSingoloDipendenteConUtente(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		repository.save(dipendenteInstance);

	}

	@Override
	public void rimuovi(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub

	}

}
