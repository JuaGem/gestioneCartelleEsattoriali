package it.prova.gestionecartelleesattoriali.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionecartelleesattoriali.model.Contribuente;
import it.prova.gestionecartelleesattoriali.repository.contribuente.ContribuenteRepository;

@Service
public class ContribuenteServiceImpl implements ContribuenteService {

	@Autowired
	private ContribuenteRepository contribuenteRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(readOnly = true)
	public List<Contribuente> listAll() {
		return (List<Contribuente>) contribuenteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Contribuente caricaSingoloContribuente(Long id) {
		return contribuenteRepository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Contribuente caricaSingoloElementoEager(Long id) {
		return contribuenteRepository.caricaSingoloContribuenteEager(id);
	}

	@Transactional
	public void aggiorna(Contribuente contribuenteInstance) {
		contribuenteRepository.save(contribuenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Contribuente contribuenteInstance) {
		contribuenteRepository.save(contribuenteInstance);
	}

	@Transactional
	public void rimuovi(Contribuente contribuenteInstance) {
		entityManager.merge(contribuenteInstance);

		if(!contribuenteInstance.getCartelle().isEmpty())
			throw new RuntimeException("Impossibile rimuovere");
		
		contribuenteRepository.delete(contribuenteInstance);
	}

	@Transactional(readOnly = true)
	public List<Contribuente> findByExample(Contribuente example) {
		return contribuenteRepository.findByExample(example);
	}
	
	@Transactional(readOnly = true)
	public List<Contribuente> cercaByCognomeENomeILike(String term) {
		return contribuenteRepository.findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(term, term);
	}


}
