package it.prova.gestionepermessi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.repository.AttachmentRepository;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentRepository repository;

	@Override
	@Transactional
	public Attachment inserisciNuovo(Attachment attachment) {
		return repository.save(attachment);
	}

	@Override
	@Transactional
	public void rimuovi(Attachment attachment) {
		repository.delete(attachment);
	}

}
