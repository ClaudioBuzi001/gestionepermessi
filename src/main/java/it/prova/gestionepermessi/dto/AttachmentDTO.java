package it.prova.gestionepermessi.dto;

import javax.persistence.Column;
import javax.persistence.Lob;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.RichiestePermesso;

public class AttachmentDTO {
	private Long id;
	private String nomeFile;
	private String contentType;
	private byte[] payload;

	public AttachmentDTO(String nomeFile, String contentType, byte[] payload) {
		super();
		this.nomeFile = nomeFile;
		this.contentType = contentType;
		this.payload = payload;
	}

	public AttachmentDTO() {
		super();
	}

	public AttachmentDTO(Long id, String nomeFile, String contentType, byte[] payload) {
		super();
		this.id = id;
		this.nomeFile = nomeFile;
		this.contentType = contentType;
		this.payload = payload;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public Attachment buildAttachementFromDTO() {
		return new Attachment(this.id, this.nomeFile, this.contentType, this.payload);		
	}
	
	public static AttachmentDTO buildAttachemntDTOFromModel(Attachment attachment) {
		return new AttachmentDTO(attachment.getId(), attachment.getNomeFile(), attachment.getContentType(), attachment.getPayload());
	}


}
