package it.prova.gestionepermessi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.RichiestePermesso;


public interface RichiestePermessoService {
	
	//TODO
	public List<RichiestePermesso> listAllRichiestePermesso() ;

	public RichiestePermesso caricaSingolaRichiestaPermesso(Long id);
	
	//TODO metodoEager

	public void aggiorna(RichiestePermesso richiestePermessoInstance);

	public void inserisciNuovo(RichiestePermesso richiestePermessoInstance);

	public void rimuovi(RichiestePermesso richiestePermessoInstance);
	
	public Page<RichiestePermesso> findByExample(RichiestaPermessoDTO example, Integer pageNo, Integer pageSize, String sortBy);
	
	public List<RichiestePermesso> findMyRichiestePermesso(String username);
	
	public void inserisciRichiestaEGeneraMessaggio(RichiestePermesso richiesta);
	
	public RichiestePermesso caricaSingolaEager(Long id);
}
