package it.prova.gestionecartelleesattoriali.repository.cartellaesattoriale;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionecartelleesattoriali.model.CartellaEsattoriale;

public interface CartellaEsattorialeRepository extends CrudRepository<CartellaEsattoriale, Long>, CustomCartellaEsattorialeRepository{

}
