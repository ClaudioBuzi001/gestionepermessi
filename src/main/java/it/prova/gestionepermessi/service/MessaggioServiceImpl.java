package it.prova.gestionepermessi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.repository.MessaggioRepository;

@Service
public class MessaggioServiceImpl implements MessaggioService {

	@Autowired
	private MessaggioRepository messaggioRepository;

	@Override
	@Transactional
	public void inserisciNuovo(Messaggio messaggio) {
		messaggioRepository.save(messaggio);
	}

	@Override
	@Transactional
	public Messaggio caricaPerIdRichiesta(Long id) {
		return messaggioRepository.findByRichiestaPermesso_id(id);
	}

	@Override
	@Transactional
	public void rimuovi(Messaggio messaggio) {
		messaggioRepository.delete(messaggio);
	}
	
}




































