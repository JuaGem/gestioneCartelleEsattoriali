package it.prova.gestionecartelleesattoriali.repository.contribuente;

import java.util.List;

import it.prova.gestionecartelleesattoriali.model.Contribuente;

public interface CustomContribuenteRepository {
	
	public List<Contribuente> findByExample(Contribuente example);

}
