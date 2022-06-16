package it.prova.gestionepermessi.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.gestionepermessi.model.StatoUtente;




public class UtenteDTO {
	private Long id;

//	uername, password, datecreated, stato, dipendente
	
	@NotBlank(message = "{username.notblank}")
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String username;

	@NotBlank(message = "{password.notblank}")
	@Size(min = 8, max = 15, message = "Il valore inserito deve essere lungo tra {min} e {max} caratteri")
	private String password;

	private String confermaPassword;

	private Date dateCreated;

	private StatoUtente stato;

	private Long[] ruoliIds;

	@NotNull(message = "{dipendente.notnull}")
	private DipendenteDTO dipendenteDTO;

	public UtenteDTO() {
	}

	public UtenteDTO(Long id, String username, String nome, String cognome, StatoUtente stato, DipendenteDTO dipendenteDTO) {
		super();
		this.id = id;
		this.username = username;
		this.stato = stato;
		this.dipendenteDTO = dipendenteDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public String getConfermaPassword() {
		return confermaPassword;
	}

	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}

	public Long[] getRuoliIds() {
		return ruoliIds;
	}

	public void setRuoliIds(Long[] ruoliIds) {
		this.ruoliIds = ruoliIds;
	}

	public DipendenteDTO getDipendenteDTO() {
		return dipendenteDTO;
	}

	public void setDipendenteDTO(DipendenteDTO dipendenteDTO) {
		this.dipendenteDTO = dipendenteDTO;
	}
	
	
	
	
	//TODO IMPLEMENTARE I METODI
//	public Utente buildUtenteModel(boolean includeIdRoles) {
//		Utente result = new Utente(this.id, this.username, this.password, this.dateCreated,
//				this.stato, this.dipendenteDTO.buildModelFromDTO());
//		if (includeIdRoles && ruoliIds != null)
//			result.setRuoli(Arrays.asList(ruoliIds).stream().map(id -> new Ruolo(id)).collect(Collectors.toSet()));
//
//		return result;
//	}
//
//	// niente password...
//	public static UtenteDTO buildUtenteDTOFromModel(Utente utenteModel) {
//		UtenteDTO result = new UtenteDTO(utenteModel.getId(), utenteModel.getUsername(), utenteModel.getNome(),
//				utenteModel.getCognome(), utenteModel.getStato());
//
//		if (!utenteModel.getRuoli().isEmpty())
//			result.ruoliIds = utenteModel.getRuoli().stream().map(r -> r.getId()).collect(Collectors.toList())
//					.toArray(new Long[] {});
//
//		return result;
//	}
	
//	public static List<UtenteDTO> createUtenteDTOListFromModelList(List<Utente> modelListInput) {
//		return modelListInput.stream().map(utenteEntity -> {
//			return UtenteDTO.buildUtenteDTOFromModel(utenteEntity);
//		}).collect(Collectors.toList());
//	}


}