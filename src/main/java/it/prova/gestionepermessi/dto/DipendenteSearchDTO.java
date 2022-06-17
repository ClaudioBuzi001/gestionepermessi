package it.prova.gestionepermessi.dto;

import java.util.Date;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.SessoDipendente;

public class DipendenteSearchDTO {
	// TODO
		private Long id;

		private String nome;

		private String cognome;

		private String codiceFiscale;

		private String email;

		private Date dataNascita;
		
		private Date dataAssunzione;
		
		private SessoDipendente sesso;

		private Long[] richiestePermessiIds;

		private UtenteDTO utenteDTO;

		
		

		public DipendenteSearchDTO(Long id, String nome, String cognome, String codiceFiscale, String email,
				Date dataNascita, Date dataAssunzione, SessoDipendente sesso, Long[] richiestePermessiIds,
				UtenteDTO utenteDTO) {
			super();
			this.id = id;
			this.nome = nome;
			this.cognome = cognome;
			this.codiceFiscale = codiceFiscale;
			this.email = email;
			this.dataNascita = dataNascita;
			this.dataAssunzione = dataAssunzione;
			this.sesso = sesso;
			this.richiestePermessiIds = richiestePermessiIds;
			this.utenteDTO = utenteDTO;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCognome() {
			return cognome;
		}

		public void setCognome(String cognome) {
			this.cognome = cognome;
		}

		public String getCodiceFiscale() {
			return codiceFiscale;
		}

		public void setCodiceFiscale(String codiceFiscale) {
			this.codiceFiscale = codiceFiscale;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Date getDataNascita() {
			return dataNascita;
		}

		public void setDataNascita(Date dataNascita) {
			this.dataNascita = dataNascita;
		}

		public Date getDataAssunzione() {
			return dataAssunzione;
		}

		public void setDataAssunzione(Date dataAssunzione) {
			this.dataAssunzione = dataAssunzione;
		}

		public SessoDipendente getSesso() {
			return sesso;
		}

		public void setSesso(SessoDipendente sesso) {
			this.sesso = sesso;
		}

		public Long[] getRichiestePermessiIds() {
			return richiestePermessiIds;
		}

		public void setRichiestePermessiIds(Long[] richiestePermessiIds) {
			this.richiestePermessiIds = richiestePermessiIds;
		}

		public UtenteDTO getUtenteDTO() {
			return utenteDTO;
		}

		public void setUtenteDTO(UtenteDTO utenteDTO) {
			this.utenteDTO = utenteDTO;
		}

		public Dipendente buildModelFromDTO() {
			return null;
		}

//		public static DipendenteDTO buildDipendenteDTOFromModel(Dipendente dipendenteModel) {
//			DipendenteDTO result = new DipendenteDTO(dipendenteModel.getId(), dipendenteModel.getNome(),
//					dipendenteModel.getCognome(), dipendenteModel.getCodiceFiscale(), dipendenteModel.getEmail(),
//					dipendenteModel.getDataNascita(), dipendenteModel.getDataAssunzione(), dipendenteModel.getSesso());
//
//			if (!dipendenteModel.getRichiestePermesso().isEmpty())
//				result.richiestePermessiIds = dipendenteModel.getRichiestePermesso().stream().map(r -> r.getId())
//						.collect(Collectors.toList()).toArray(new Long[] {});
//
//			return result;
//		}

}
