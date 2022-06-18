package it.prova.gestionepermessi.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dipendente", uniqueConstraints = @UniqueConstraint(columnNames = { "utente_id" }))
public class Dipendente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "codiceFiscale")
	private String codiceFiscale;
	@Column(name = "email")
	private String email;
	@Column(name = "dataNascita")
	private Date dataNascita;
	@Column(name = "dataAssunzione")
	private Date dataAssunzione;
	@Column(name = "dataDimissioni")
	private Date dataDimissioni = null;

	@Column(name = "sesso")
	private SessoDipendente sesso;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "utente_id", referencedColumnName = "id") // FIXME Forse da togliere
	private Utente utente;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dipendente")
	private Set<RichiestePermesso> richiestePermesso = new HashSet<RichiestePermesso>(0);

	public Dipendente() {
		super();
	}

	public Dipendente(String nome, String cognome, String codiceFiscale, String email, Date dataNascita,
			Date dataAssunzione, Date dataDimissioni, SessoDipendente sesso, Utente utente,
			Set<RichiestePermesso> richiestePermesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.dataDimissioni = dataDimissioni;
		this.sesso = sesso;
		this.utente = utente;
		this.richiestePermesso = richiestePermesso;
	}

	public Dipendente(Long id, String nome, String cognome, String codiceFiscale, String email, Date dataNascita,
			Date dataAssunzione, Date dataDimissioni, SessoDipendente sesso, Utente utente,
			Set<RichiestePermesso> richiestePermesso) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.dataDimissioni = dataDimissioni;
		this.sesso = sesso;
		this.utente = utente;
		this.richiestePermesso = richiestePermesso;
	}

	public Dipendente(String nome, String cognome, String codiceFiscale, String email, Date dataNascita,
			Date dataAssunzione, SessoDipendente sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.sesso = sesso;
	}

	public Dipendente(String nome, String cognome, String codiceFiscale, Date dataNascita, Date dataAssunzione,
			SessoDipendente sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.sesso = sesso;
	}
	
	public Dipendente(Long id, String nome, String cognome, String codiceFiscale, Date dataNascita, Date dataAssunzione,
			SessoDipendente sesso) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.sesso = sesso;
	}

	public Dipendente(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
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

	public Date getDataDimissioni() {
		return dataDimissioni;
	}

	public void setDataDimissioni(Date dataDimissioni) {
		this.dataDimissioni = dataDimissioni;
	}

	public SessoDipendente getSesso() {
		return sesso;
	}

	public void setSesso(SessoDipendente sesso) {
		this.sesso = sesso;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Set<RichiestePermesso> getRichiestePermesso() {
		return richiestePermesso;
	}

	public void setRichiestePermesso(Set<RichiestePermesso> richiestePermesso) {
		this.richiestePermesso = richiestePermesso;
	}

}
