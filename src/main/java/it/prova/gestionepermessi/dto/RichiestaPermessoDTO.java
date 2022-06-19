package it.prova.gestionepermessi.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestePermesso;
import it.prova.gestionepermessi.model.TipoPermesso;

public class RichiestaPermessoDTO {

	private Long id;

	@NotNull(message = "{tipoPermesso.notnull}")
	private TipoPermesso tipoPermesso;
	@NotNull(message = "{dataInizio.notnull}")
	private Date dataInizio;
	private Date dataFine;
	private Boolean approvato;
	private String codiceCertificato;
	private String note;

	private AttachmentDTO attachment = new AttachmentDTO();

	private DipendenteDTO dipendente = new DipendenteDTO();

	public RichiestaPermessoDTO() {
		super();
	}

	public RichiestaPermessoDTO(TipoPermesso tipoPermesso, Date dataInizio, Date dataFine, boolean approvato,
			String codiceCertificato, String note, AttachmentDTO attachment, DipendenteDTO dipendete) {
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

	public RichiestaPermessoDTO(Long id, TipoPermesso tipoPermesso, Date dataInizio, Date dataFine, boolean approvato,
			String codiceCertificato, String note, AttachmentDTO attachment, DipendenteDTO dipendete) {
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

	public RichiestaPermessoDTO(Long id2, Date dataInizio2, Date dataFine2, boolean approvato2,
			String codiceCertificato2, String note2, TipoPermesso tipoPermesso2) {
		// TODO Auto-generated constructor stub
	}
	

	public RichiestaPermessoDTO(Long id, TipoPermesso tipoPermesso, Date dataInizio, Date dataFine, Boolean approvato,
			String codiceCertificato, String note, AttachmentDTO attachment) {
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
	

	public RichiestaPermessoDTO(Long id, Date dataInizio, Date dataFine, boolean approvato,
			String codiceCertificato, String note, TipoPermesso tipoPermesso,
			AttachmentDTO attachemnt) {
		super();
		this.id = id;
		this.tipoPermesso = tipoPermesso;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.approvato = approvato;
		this.codiceCertificato = codiceCertificato;
		this.note = note;
		this.attachment = attachemnt;
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

	public AttachmentDTO getAttachment() {
		return attachment;
	}

	public void setAttachment(AttachmentDTO attachment) {
		this.attachment = attachment;
	}

	public DipendenteDTO getDipendente() {
		return dipendente;
	}

	public void setDipendente(DipendenteDTO dipendete) {
		this.dipendente = dipendete;
	}

	// TODO
	public static RichiestaPermessoDTO buildRichiestaPermessoDTOFromModel(RichiestePermesso richiestaPermessoModel) {
		return new RichiestaPermessoDTO(richiestaPermessoModel.getId(), richiestaPermessoModel.getDataInizio(),
				richiestaPermessoModel.getDataFine(), richiestaPermessoModel.isApprovato(),
				richiestaPermessoModel.getCodiceCertificato(), richiestaPermessoModel.getNote(),
				richiestaPermessoModel.getTipoPermesso(), AttachmentDTO.buildAttachemntDTOFromModel(richiestaPermessoModel.getAttachment()));
	}

	public RichiestePermesso buildRichiestaPermessoFromDTO() {
		return new RichiestePermesso(this.id, this.dataInizio, this.dataFine, false, this.codiceCertificato, this.note,
				this.tipoPermesso, this.attachment.buildAttachementFromDTO());
	}

	public static List<RichiestaPermessoDTO> buildRichiestaPermessoDTOFromModelList(List<RichiestePermesso> richieste) {
		return richieste.stream().map(richiesta -> RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(richiesta))
				.collect(Collectors.toList());
	}
}
