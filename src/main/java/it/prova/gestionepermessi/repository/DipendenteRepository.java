package it.prova.gestionepermessi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;


public interface DipendenteRepository extends CrudRepository<Dipendente, Long>{
	
	@Query("select d from Dipendente d join fetch d.utente where d.id = ?1")
	Optional<Dipendente> findByIdWithUtente(Long id);

	Page<Dipendente> findAll(Specification<Dipendente> specificationCriteria, Pageable paging);
	
	Dipendente findByUtente_username(String username);

}
