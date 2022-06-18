package it.prova.gestionepermessi.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.SessoDipendente;

public class DipendenteDTO {

	// TODO
	private Long id;
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	@NotBlank(message = "{codiceFiscale.notblank}")
	@Size(min = 16, max = 16, message = "Errore, la lunghezza del codice fiscale deve essere di 16")
	private String codiceFiscale;
	private String email;
	@NotNull(message = "{dataNascita.notblank}")
	private Date dataNascita;
	private Date dataAssunzione;
	private SessoDipendente sesso;

	private Long[] richiestePermessiIds;

	private UtenteDTO utenteDTO;

	public DipendenteDTO() {
		super();
	}

	public DipendenteDTO(@NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") String codiceFiscale,
			@NotBlank(message = "{email.notblank}") String email,
			@NotBlank(message = "{dataNascita.notblank}") Date dataNascita, Date dataAssunzione, SessoDipendente sesso,
			UtenteDTO utenteDTO) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.sesso = sesso;
		this.utenteDTO = utenteDTO;
	}

	public DipendenteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") String codiceFiscale,
			@NotBlank(message = "{email.notblank}") String email,
			@NotBlank(message = "{dataNascita.notblank}") Date dataNascita, Date dataAssunzione, SessoDipendente sesso,
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
		this.utenteDTO = utenteDTO;
	}

	public DipendenteDTO(Long id, String nome, String cognome, String codiceFiscale, String email, Date dataNascita,
			Date dataAssunzione, SessoDipendente sesso) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.sesso = sesso;
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
		Dipendente model = new Dipendente(this.id,this.nome,this.cognome,this.codiceFiscale, this.dataNascita, this.dataAssunzione, this.sesso);
		
		//setto l email con le specifiche
		String email = this.getNome().charAt(0) + "."+ this.getCognome()+ "@prova.it";
		model.setEmail(email);
		
		return model;
	}

	public static DipendenteDTO buildDipendenteDTOFromModel(Dipendente dipendenteModel) {
		DipendenteDTO result = new DipendenteDTO(dipendenteModel.getId(), dipendenteModel.getNome(),
				dipendenteModel.getCognome(), dipendenteModel.getCodiceFiscale(), dipendenteModel.getEmail(),
				dipendenteModel.getDataNascita(), dipendenteModel.getDataAssunzione(), dipendenteModel.getSesso());

		if (!dipendenteModel.getRichiestePermesso().isEmpty())
			result.richiestePermessiIds = dipendenteModel.getRichiestePermesso().stream().map(r -> r.getId())
					.collect(Collectors.toList()).toArray(new Long[] {});

		return result;
	}


	public static List<DipendenteDTO> createDipendenteDTOListFromModelList(List<Dipendente> modelListInput) {
		return modelListInput.stream().map(dipendenteEntity -> {
			return DipendenteDTO.buildDipendenteDTOFromModel(dipendenteEntity);
		}).collect(Collectors.toList());
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
