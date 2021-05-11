package it.prova.gestionecartelleesattoriali.repository.cartellaesattoriale;

import java.util.List;

import it.prova.gestionecartelleesattoriali.model.CartellaEsattoriale;

public interface CustomCartellaEsattorialeRepository {
	
	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example);

}
