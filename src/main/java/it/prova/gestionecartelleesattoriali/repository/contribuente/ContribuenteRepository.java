package it.prova.gestionecartelleesattoriali.repository.contribuente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionecartelleesattoriali.model.Contribuente;



public interface ContribuenteRepository extends CrudRepository<Contribuente, Long>, CustomContribuenteRepository{

	@Query("from Contribuente c left join fetch c.cartelle a where c.id = ?1")
	Contribuente caricaSingoloContribuenteEager(Long id);
	
	List<Contribuente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String cognome, String nome);
	
}
