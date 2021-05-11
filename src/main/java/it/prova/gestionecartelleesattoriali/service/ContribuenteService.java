package it.prova.gestionecartelleesattoriali.service;

import java.util.List;

import it.prova.gestionecartelleesattoriali.model.Contribuente;

public interface ContribuenteService {
	
	public List<Contribuente> listAll();

	public Contribuente caricaSingoloContribuente(Long id);
	
	public Contribuente caricaSingoloElementoEager(Long id);

	public void aggiorna(Contribuente contribuenteInstance);

	public void inserisciNuovo(Contribuente contribuenteInstance);

	public void rimuovi(Contribuente contribuenteInstance);

	public List<Contribuente> findByExample(Contribuente example);
	
	public List<Contribuente> cercaByCognomeENomeILike(String term);

}
