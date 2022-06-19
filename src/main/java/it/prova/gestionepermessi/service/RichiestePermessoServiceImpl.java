package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.RichiestePermesso;
import it.prova.gestionepermessi.repository.DipendenteRepository;
import it.prova.gestionepermessi.repository.RichiestePermessoRepository;

@Service
public class RichiestePermessoServiceImpl implements RichiestePermessoService {

	@Autowired
	private DipendenteRepository dipendenteRepository;
	
	@Autowired
	private RichiestePermessoRepository repository;
	
	
	@Override
	public List<RichiestePermesso> listAllRichiestePermesso() {
		return (List<RichiestePermesso>) repository.findAll();
	}

	@Override
	public RichiestePermesso caricaSingolaRichiestaPermesso(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(RichiestePermesso richiestePermessoInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(RichiestePermesso richiestePermessoInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(RichiestePermesso richiestePermessoInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)
	public Page<RichiestePermesso> findByExample(RichiestaPermessoDTO example, Integer pageNo, Integer pageSize,
			String sortBy) {
		Specification<RichiestePermesso> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			root.fetch("dipendente", JoinType.INNER);

			if (example.getTipoPermesso() != null)
				predicates.add(cb.equal(cb.upper(root.get("tipoPermesso")), example.getTipoPermesso()));

			if (example.getDataInizio() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataInizio"), example.getDataInizio()));

			if (example.getDataFine() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataFine"), example.getDataFine()));
			
			if(example.isApprovato() == true || example.isApprovato() == false)
				predicates.add(cb.equal(root.get("approvato"), example.isApprovato()));

			if (StringUtils.isNotEmpty(example.getCodiceCertificato()))
				predicates.add(cb.like(cb.upper(root.get("codiceCertificato")),
						"%" + example.getCodiceCertificato().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getNote()))
				predicates.add(cb.like(cb.upper(root.get("note")), "%" + example.getNote().toUpperCase() + "%"));

			if (example.getDipendente() != null && example.getDipendente().getId() != null) {
				predicates.add(
						cb.equal(root.join("dipendente", JoinType.INNER).get("id"), example.getDipendente().getId()));
			}

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
	@Transactional(readOnly = true)
	public List<RichiestePermesso> findMyRichiestePermesso(String username) {
	    return repository.findByDipendente_id(dipendenteRepository.findByUtente_username(username).getId());
	}

}


























