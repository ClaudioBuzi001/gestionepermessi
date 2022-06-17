package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.JoinType;
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

import it.prova.gestionepermessi.dto.UtenteSearchDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.repository.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository repository;

	@Autowired
	private DipendenteService dipendenteService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RuoloService ruoloService;

	@Transactional(readOnly = true)
	public List<Utente> listAllUtenti() {
		return (List<Utente>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Utente caricaSingoloUtente(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Utente caricaSingoloUtenteConRuoli(Long id) {
		return repository.findByIdConRuoli(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Utente utenteInstance) {
		// deve aggiornare solo nome, cognome, username, ruoli
		Utente utenteReloaded = repository.findById(utenteInstance.getId()).orElse(null);
		if (utenteReloaded == null)
			throw new RuntimeException("Elemento non trovato");
		utenteReloaded.setUsername(utenteInstance.getUsername());
		utenteReloaded.setRuoli(utenteInstance.getRuoli());
		repository.save(utenteReloaded);
	}

	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		utenteInstance.setStato(StatoUtente.CREATO);
		utenteInstance.setPassword(passwordEncoder.encode(utenteInstance.getPassword()));
		utenteInstance.setDateCreated(new Date());
		repository.save(utenteInstance);
	}

	@Transactional
	public void rimuovi(Utente utenteInstance) {
		repository.delete(utenteInstance);
	}

	@Transactional(readOnly = true)
	public List<Utente> findByExample(Utente example) {
		// TODO Da implementare
		return listAllUtenti();
	}

	@Transactional(readOnly = true)
	public Utente eseguiAccesso(String username, String password) {
		return repository.findByUsernameAndPasswordAndStato(username, password, StatoUtente.ATTIVO);
	}

	@Override
	public Utente findByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}

	@Transactional
	public void changeUserAbilitation(Long utenteInstanceId) {
		Utente utenteInstance = caricaSingoloUtente(utenteInstanceId);
		if (utenteInstance == null)
			throw new RuntimeException("Elemento non trovato.");

		if (utenteInstance.getStato() == null || utenteInstance.getStato().equals(StatoUtente.CREATO))
			utenteInstance.setStato(StatoUtente.ATTIVO);
		else if (utenteInstance.getStato().equals(StatoUtente.ATTIVO))
			utenteInstance.setStato(StatoUtente.DISABILITATO);
		else if (utenteInstance.getStato().equals(StatoUtente.DISABILITATO))
			utenteInstance.setStato(StatoUtente.ATTIVO);
	}

	@Transactional
	public Utente findByUsername(String username) {
		return repository.findByUsername(username).orElse(null);
	}

	@Transactional
	public void resetPasswordService(Long idUtente) {
		repository.resetPasswordRepository(idUtente, passwordEncoder.encode("Password@01"));

	}

	@Override
	@Transactional
	public int modificaPassword(Long idUtente, String vecchiaPassword, String nuovaPassword,
			String nuovaPasswordConferma) {
		if (!passwordEncoder.matches(vecchiaPassword, repository.findById(idUtente).get().getPassword())) {
			return 0;

		}

		if (!nuovaPassword.equals(nuovaPasswordConferma)) {
			return 1;
		}

		repository.resetPasswordRepository(idUtente, passwordEncoder.encode(nuovaPassword));
		return 2;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Utente> findByExample(UtenteSearchDTO example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Utente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			// faccio fetch del dipendente e ruoli a prescindere
			root.fetch("dipendente", JoinType.INNER);
			root.fetch("ruoli", JoinType.LEFT);

			if (StringUtils.isNotEmpty(example.getUsername()))
				predicates
						.add(cb.like(cb.upper(root.get("username")), "%" + example.getUsername().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.join("dipendente", JoinType.INNER).get("nome")),
						"%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.join("dipendente", JoinType.INNER).get("cognome")),
						"%" + example.getCognome().toUpperCase() + "%"));
//
//			if (StringUtils.isNotEmpty(example.getCodFis()))
//				predicates.add(cb.like(cb.upper(root.join("dipendente", JoinType.INNER).get("codFis")),
//						"%" + example.getDipendente().getCodFis().toUpperCase() + "%"));

			if (example.getStato() != null)
				predicates.add(cb.equal(root.get("stato"), example.getStato()));

			if (example.getRuoliIds() != null && example.getRuoliIds().length > 0 )
				predicates.add(root.join("ruoli").in(Arrays.asList(example.getRuoliIds()).stream()
						.map(id -> new Ruolo(id)).collect(Collectors.toSet())));

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
	public void inserisciUtenteEDipendenteCollegandoli(Utente utente, Dipendente dipendente) {
		// setto la password ad utente
		repository.save(utente);
		dipendenteService.inserisciNuovo(dipendente);

	}

}
