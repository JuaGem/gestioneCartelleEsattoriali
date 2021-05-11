package it.prova.gestionecartelleesattoriali.service;

import java.util.List;

import it.prova.gestionecartelleesattoriali.model.CartellaEsattoriale;

public interface CartellaEsattorialeService {
	
	public List<CartellaEsattoriale> listAll();

	public CartellaEsattoriale caricaSingolaCartellaEsattoriale(Long id);

	public void aggiorna(CartellaEsattoriale cartellaInstance);

	public void inserisciNuovo(CartellaEsattoriale cartellaInstance);

	public void rimuovi(CartellaEsattoriale cartellaInstance );

	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example);
	
	 public void eliminaCartellaEsattoriale(CartellaEsattoriale cartellaEsattoriale);

}
