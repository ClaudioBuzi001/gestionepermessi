package it.prova.gestionepermessi.model;

public enum SessoDipendente {
	MASCHIO("M"), FEMMINA("F");

	private String abbreviazione;

	SessoDipendente(String abbreviazione) {
		this.abbreviazione = abbreviazione;
	}

	public String getAbbreviazione() {
		return abbreviazione;
	}

}
