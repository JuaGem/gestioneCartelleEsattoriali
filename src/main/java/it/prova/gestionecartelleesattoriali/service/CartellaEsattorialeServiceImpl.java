package it.prova.gestionecartelleesattoriali.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionecartelleesattoriali.model.CartellaEsattoriale;
import it.prova.gestionecartelleesattoriali.model.Stato;
import it.prova.gestionecartelleesattoriali.repository.cartellaesattoriale.CartellaEsattorialeRepository;

@Service
public class CartellaEsattorialeServiceImpl implements CartellaEsattorialeService {
	
	@Autowired
	private CartellaEsattorialeRepository cartellaEsattorialeRepository;

	@Transactional(readOnly = true)
	public List<CartellaEsattoriale> listAll() {
		return (List<CartellaEsattoriale>) cartellaEsattorialeRepository.findAll();
	}

	@Transactional(readOnly = true)
	public CartellaEsattoriale caricaSingolaCartellaEsattoriale(Long id) {
		return cartellaEsattorialeRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(CartellaEsattoriale cartellaInstance) {
		cartellaEsattorialeRepository.save(cartellaInstance);
	}

	@Transactional
	public void inserisciNuovo(CartellaEsattoriale cartellaInstance) {
		cartellaEsattorialeRepository.save(cartellaInstance);
	}

	@Transactional
	public void rimuovi(CartellaEsattoriale cartellaInstance) {
		cartellaEsattorialeRepository.delete(cartellaInstance);

	}

	@Transactional(readOnly = true)
	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example) {
		return cartellaEsattorialeRepository.findByExample(example);
	}
	
	@Transactional
    public void eliminaCartellaEsattoriale(CartellaEsattoriale cartellaEsattoriale) {
        cartellaEsattoriale.setStatoCartella(Stato.INVALIDATA);
        cartellaEsattorialeRepository.save(cartellaEsattoriale);
    }

}
