package it.prova.gestionepermessi.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import it.prova.gestionepermessi.model.RichiestePermesso;

public class MessaggioDTO {
	private String testo;
	private String oggetto;
	private Boolean letto = false;
	private Date dataInserimento;
	private Date dataLettura;

	private RichiestePermesso richiestaPermesso;

	public MessaggioDTO(String testo, String oggetto, Boolean letto, Date dataInserimento, Date dataLettura,
			RichiestePermesso richiestaPermesso) {
		super();
		this.testo = testo;
		this.oggetto = oggetto;
		this.letto = letto;
		this.dataInserimento = dataInserimento;
		this.dataLettura = dataLettura;
		this.richiestaPermesso = richiestaPermesso;
	}
	
	   

}
