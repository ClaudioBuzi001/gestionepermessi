package it.prova.gestionepermessi.service;

import it.prova.gestionepermessi.model.Attachment;

public interface AttachmentService {
	
	public Attachment inserisciNuovo(Attachment attachment);
	
	public void rimuovi(Attachment attachment);
	
	
}
