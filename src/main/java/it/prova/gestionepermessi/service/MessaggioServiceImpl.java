package it.prova.gestionepermessi.service;

import java.util.Date;
import java.util.List;

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

	@Override
	@Transactional(readOnly = true)
	public List<Messaggio> listaTutti() {
		return (List<Messaggio>) messaggioRepository.findAll();
	}

	@Override
	@Transactional
	public void settaDataLettura(Long id) {
		messaggioRepository.setDataLettura(new Date(), id);
	}

	@Override
	@Transactional(readOnly = true)
	public Messaggio caricaMessaggioEager(Long id) {
		return messaggioRepository.findByIdEager(id);
	}
	
}




































