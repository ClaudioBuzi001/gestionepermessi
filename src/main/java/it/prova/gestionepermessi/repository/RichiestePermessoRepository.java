package it.prova.gestionepermessi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.RichiestePermesso;
 
public interface RichiestePermessoRepository extends CrudRepository<RichiestePermesso, Long>{

	Page<RichiestePermesso> findAll(Specification<RichiestePermesso> specificationCriteria, Pageable paging);
	
	List<RichiestePermesso> findByDipendente_id(Long id);

}
