package it.prova.gestionepermessi.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "richiestapermesso")
public class RichiestePermesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoPermesso tipoPermesso;
	@Column(name = "dataInizio")
	private Date dataInizio;
	@Column(name = "dataFine")
	private Date dataFine;
	@Column(name = "approvato")
	private Boolean approvato = false;
	@Column(name = "codiceCertificato")
	private String codiceCertificato;
	@Column(name = "note")
	private String note;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "attachment_id", referencedColumnName = "id")
	private Attachment attachment;

	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "dipendente_id", referencedColumnName = "id", nullable = false)
	private Dipendente dipendente;

	public RichiestePermesso() {
		super();
	}

	public RichiestePermesso(TipoPermesso tipoPermesso, Date dataInizio, Date dataFine, boolean approvato,
			String codiceCertificato, String note, Attachment attachment, Dipendente dipendete) {
		super();
		this.tipoPermesso = tipoPermesso;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.approvato = approvato;
		this.codiceCertificato = codiceCertificato;
		this.note = note;
		this.attachment = attachment;
		this.dipendente = dipendete;
	}

	public RichiestePermesso(Long id, TipoPermesso tipoPermesso, Date dataInizio, Date dataFine, boolean approvato,
			String codiceCertificato, String note, Attachment attachment, Dipendente dipendete) {
		super();
		this.id = id;
		this.tipoPermesso = tipoPermesso;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.approvato = approvato;
		this.codiceCertificato = codiceCertificato;
		this.note = note;
		this.attachment = attachment;
		this.dipendente = dipendete;
	}
	

	public RichiestePermesso(Long id, Date dataInizio, Date dataFine, boolean approvato, String codiceCertificato, String note,
			TipoPermesso tipoPermesso, Attachment attachment) {
		super();
		this.id = id;
		this.tipoPermesso = tipoPermesso;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.approvato = approvato;
		this.codiceCertificato = codiceCertificato;
		this.note = note;
		this.attachment = attachment;
	}

	public RichiestePermesso(Long id, Date dataInizio, Date dataFine, Boolean approvato, String codiceCertificato,
			String note) {
		super();
		this.id = id;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.approvato = approvato;
		this.codiceCertificato = codiceCertificato;
		this.note = note;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPermesso getTipoPermesso() {
		return tipoPermesso;
	}

	public void setTipoPermesso(TipoPermesso tipoPermesso) {
		this.tipoPermesso = tipoPermesso;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public boolean isApprovato() {
		return approvato;
	}

	public void setApprovato(boolean approvato) {
		this.approvato = approvato;
	}

	public String getCodiceCertificato() {
		return codiceCertificato;
	}

	public void setCodiceCertificato(String codiceCertificato) {
		this.codiceCertificato = codiceCertificato;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendete) {
		this.dipendente = dipendete;
	}

}
