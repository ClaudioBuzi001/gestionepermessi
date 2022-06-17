package it.prova.gestionepermessi.dto;

import java.util.Date;

import it.prova.gestionepermessi.model.StatoUtente;

public class UtenteSearchDTO {

	private Long id;
//	uername, password, datecreated, stato, dipendente

	private String username;

	private String nome;

	private String cognome;

	private Date dateCreated;

	private StatoUtente stato;

	private Long[] ruoliIds;

	public UtenteSearchDTO() {
	}

	public UtenteSearchDTO(String username, String nome, String congome, Date dateCreated, StatoUtente stato,
			Long[] ruoliIds) {
		super();
		this.username = username;
		this.nome = nome;
		this.cognome = congome;
		this.dateCreated = dateCreated;
		this.stato = stato;
		this.ruoliIds = ruoliIds;
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

	public Long[] getRuoliIds() {
		return ruoliIds;
	}

	public void setRuoliIds(Long[] ruoliIds) {
		this.ruoliIds = ruoliIds;
	}

	// TODO IMPLEMENTARE I METODI
//	public Utente buildUtenteModel(boolean includeIdRoles) {
//		Utente result = new Utente(this.id, this.username, this.password, this.dateCreated, this.stato,
//				null); //null Dipendente
//
//		if (includeIdRoles && ruoliIds != null)
//			result.setRuoli(Arrays.asList(ruoliIds).stream().map(id -> new Ruolo(id)).collect(Collectors.toSet()));
//
//		return result;
//	}

//
	// niente password...
	// Costruisco un utenteDTO con ruoli e anche con dipendente
//	public static UtenteDTO buildUtenteDTOFromModel(Utente utenteModel) {
//		UtenteDTO result = new UtenteDTO(utenteModel.getId(), utenteModel.getUsername(), utenteModel.getStato(),
//				utenteModel.getDateCreated(), DipendenteDTO.buildDipendenteDTOFromModel(utenteModel.getDipendente()));
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
