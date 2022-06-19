package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.dto.DipendenteSearchDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.repository.DipendenteRepository;
import it.prova.gestionepermessi.repository.UtenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	private DipendenteRepository repository;

	@Autowired
	private RuoloService ruoloService;

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<Dipendente> listAllDipendenti() {
		// TODO Auto-generated method stub
		return (List<Dipendente>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Dipendente caricaSingoloDipendente(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Dipendente caricaSingoloDipendenteConUtente(Long id) {
		return repository.findByIdWithUtente(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Dipendente dipendenteInstance) {
		repository.save(dipendenteInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		repository.save(dipendenteInstance);

	}

	@Override
	@Transactional
	public void rimuovi(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public Page<Dipendente> findByExample(DipendenteSearchDTO example, Integer pageNo, Integer pageSize,
			String sortBy) {
		Specification<Dipendente> specificationCriteria = (root, query, cb) -> {

//			Nome congome, codiceFiscale, email, data nascita data assunzinoe

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCodiceFiscale()))
				predicates.add(cb.like(cb.upper(root.get("codiceFiscale")),
						"%" + example.getCodiceFiscale().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getEmail()))
				predicates.add(cb.like(cb.upper(root.get("email")), "%" + example.getEmail().toUpperCase() + "%"));

			if (example.getDataNascita() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataNascita"), example.getDataNascita()));

			if (example.getDataAssunzione() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataAssunzione"), example.getDataAssunzione()));

			query.distinct(true);
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);

	}

	@Override
	@Transactional
	public void inserisciDipendenteEUtente(Dipendente dipendente) {
		// Come prima cosa chiamiamo il metodo create
		dipendente.setUtente(createUtenteFromDipendente(dipendente));

		utenteRepository.save(dipendente.getUtente());
		repository.save(dipendente);
	}

	private Utente createUtenteFromDipendente(Dipendente dipendente) {
		// crea utente
		Utente result = new Utente(dipendente);
		// Sertare settare username
		String username = dipendente.getNome().charAt(0) + "." + dipendente.getCognome();
		result.setUsername(username);
		// Setto password
		result.setPassword(passwordEncoder.encode("Password@01"));
		// DATA CRATEED
		result.setDateCreated(new Date());
		// statoUtente
		result.setStato(StatoUtente.CREATO);
		// Ruolo
		result.getRuoli().add(ruoloService.cercaPerDescrizioneECodice("Dipendente User", "ROLE_DIPENDENTE_USER"));

		return result;
	}

	@Override
	@Transactional
	public void modificaDipendente(Dipendente dipendenteInstance) {
		//FIXME non arriva l id
		System.out.println(dipendenteInstance.getId());
		Utente utenteDaModificare = repository.findByIdWithUtente(dipendenteInstance.getId()).orElse(null).getUtente();

		if (utenteDaModificare == null)
			throw new RuntimeException("Errore, Utente non trovato");

		// modifico lo username del utente
		utenteDaModificare.setUsername(dipendenteInstance.getNome().charAt(0) + "." + dipendenteInstance.getCognome());
		utenteRepository.save(utenteDaModificare);
		
		// modifico il dipendente
		dipendenteInstance.setUtente(utenteDaModificare);
		repository.save(dipendenteInstance);

		
		
	}

	@Override
	public Dipendente trovaPerUsername(String usernames) {
		return repository.findByUtente_username(usernames);
	}

}
