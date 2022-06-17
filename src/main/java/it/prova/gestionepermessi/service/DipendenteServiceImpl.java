package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.DipendenteSearchDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {
	
	@Autowired
	private DipendenteRepository repository;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void aggiorna(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub

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
	public Page<Dipendente> findByExample(DipendenteSearchDTO example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Utente> specificationCriteria = (root, query, cb) -> {

//			Nome congome, codiceFiscale, email, data nascita data assunzinoe
			
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates
						.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates
				.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCodiceFiscale()))
				predicates
				.add(cb.like(cb.upper(root.get("codiceFiscale")), "%" + example.getCodiceFiscale().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getEmail()))
				predicates
				.add(cb.like(cb.upper(root.get("email")), "%" + example.getEmail().toUpperCase() + "%"));
			
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

}
