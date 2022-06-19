package it.prova.gestionepermessi.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioRepository extends CrudRepository<Messaggio, Long> {

	
	Messaggio findByRichiestaPermesso_id(Long id);
	
	@Modifying
	@Query("update Messaggio m set m.dataLettura = ?1 where m.id = ?2")
	void setDataLettura(Date dataLettura, Long id);
	
	
	@Query("select m from Messaggio m join fetch m.richiestaPermesso r join fetch r.dipendente where m.id = ?1")
	Messaggio findByIdEager(Long id);
	
}

