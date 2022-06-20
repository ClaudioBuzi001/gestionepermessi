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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.dto.DipendenteSearchDTO;
import it.prova.gestionepermessi.model.Dipendente;
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

	@Override
	@Transactional(readOnly = true)
	public List<Messaggio> lisAllMessaggiNonLetti() {
		return messaggioRepository.findAllMessaggiNonLetti();
	}

	@Override
	public Page<Messaggio> findByExample(Messaggio example, Integer pageNo, Integer pageSize,
			String sortBy) {
		Specification<Messaggio> specificationCriteria = (root, query, cb) -> {

//			Nome congome, codiceFiscale, email, data nascita data assunzinoe

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getTesto()))
				predicates.add(cb.like(cb.upper(root.get("testo")), "%" + example.getTesto().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getOggetto()))
				predicates.add(cb.like(cb.upper(root.get("oggetto")), "%" + example.getOggetto().toUpperCase() + "%"));


			if (example.getDataInserimento() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataInserimento"), example.getDataInserimento()));

			query.distinct(true);
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return messaggioRepository.findAll(specificationCriteria, paging);

	}
	
}




































