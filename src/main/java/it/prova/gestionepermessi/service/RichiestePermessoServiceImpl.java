package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.JoinType;
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

import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestePermesso;
import it.prova.gestionepermessi.model.TipoPermesso;
import it.prova.gestionepermessi.repository.AttachmentRepository;
import it.prova.gestionepermessi.repository.DipendenteRepository;
import it.prova.gestionepermessi.repository.MessaggioRepository;
import it.prova.gestionepermessi.repository.RichiestePermessoRepository;

@Service
public class RichiestePermessoServiceImpl implements RichiestePermessoService {

	@Autowired
	private DipendenteRepository dipendenteRepository;

	@Autowired
	private RichiestePermessoRepository repository;

	@Autowired
	private MessaggioRepository messaggioRepository;
	
	@Autowired
	private AttachmentRepository attachmentRepository;

	@Override
	@Transactional(readOnly = true)
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
	@Transactional
	public void inserisciNuovo(RichiestePermesso richiestePermessoInstance) {
		repository.save(richiestePermessoInstance);

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

	@Override
	@Transactional
	public void inserisciRichiestaEGeneraMessaggio(RichiestePermesso richiesta) {
		repository.save(richiesta);
		messaggioRepository.save(creaMessaggio(richiesta));
	}

	private Messaggio creaMessaggio(RichiestePermesso richiesta) {
		Messaggio result = new Messaggio(richiesta);

		String oggetto = "Richiesta Di Permesso da parte di " + richiesta.getDipendente().getNome().toUpperCase() + " "
				+ richiesta.getDipendente().getCognome().toUpperCase();

		String test = "Il dipendente " + richiesta.getDipendente().getNome() + " "
				+ richiesta.getDipendente().getCognome() + " ha richiesto un permesso per "
				+ richiesta.getTipoPermesso() + " ";

		if (richiesta.getDataFine() != null)
			test += "per i Giorni " + richiesta.getDataInizio().toString() + " - " + richiesta.getDataFine().toString();
		else
			test += "per il Giorno " + richiesta.getDataInizio().toString();

		if (richiesta.getTipoPermesso().equals(TipoPermesso.MALATTIA))
			test += " Codice Certificato: " + richiesta.getCodiceCertificato();

		test += " Note: " + richiesta.getNote();

		result.setOggetto(oggetto);
		result.setTesto(test);
		result.setDataInserimento(new Date());

		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public RichiestePermesso caricaSingolaEager(Long id) {
		return repository.findByIdEager(id);
	}

	@Override
	@Transactional
	public void rimuoviPerId(Long id) {
		//Mi carico la richiesta, prendo il dipendente e dissocio il dipendente e la richiesta caricata, dissocio la richiesta dal dipendente
		RichiestePermesso daEliminare = repository.findByIdEager(id);
		
		for(RichiestePermesso richiestaItem : daEliminare.getDipendente().getRichiestePermesso()) {
			if(richiestaItem.getId() == id) 
				daEliminare.getDipendente().getRichiestePermesso().remove(richiestaItem);
		}
		//Prendo l attachement dalla richiesta
		
		attachmentRepository.delete(daEliminare.getAttachment());
		
		//Prendo il messaggio dissocio la richiesta dal messaggio e elimino il messaggio
		Messaggio messaggio = messaggioRepository.findByRichiestaPermesso_id(id);
		
		messaggioRepository.delete(messaggio);
		
		//Elimino la richiesta
		repository.delete(daEliminare);
		
	}

	@Override
	@Transactional
	public void approva(Long id) {
		repository.approva(id);
	}

	@Override
	@Transactional
	public void rifuta(Long id) {
		repository.rifuta(id);
	}

}




























