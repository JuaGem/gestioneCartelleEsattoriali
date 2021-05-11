package it.prova.gestionecartelleesattoriali.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cartellaEsattoriale")
public class CartellaEsattoriale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank(message = "{descrizione.notblank}")
	@Column(name = "descrizione")
	private String descrizione;

	@NotNull(message = "{importo.notnull}")
	@Min(1)
	@Column(name = "importo")
	private Integer importo;

	@NotNull(message = "{statoCartella.notblank}")
	@Column(name = "stato_cartella")
	@Enumerated(EnumType.STRING)
	private Stato statoCartella;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contribuente_id")
	private Contribuente contribuente;

	public CartellaEsattoriale() {
	}

	public CartellaEsattoriale(String descrizione, Integer importo, Stato statoCartella, Contribuente contribuente) {
		this.descrizione = descrizione;
		this.importo = importo;
		this.statoCartella = statoCartella;
		this.contribuente = contribuente;
	}
	
	public CartellaEsattoriale(Long id,String descrizione, Integer importo, Stato statoCartella, Contribuente contribuente) {
		this.id=id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.statoCartella = statoCartella;
		this.contribuente = contribuente;
	}
	
	public CartellaEsattoriale(String descrizione, Integer importo, Stato statoCartella) {
		this.descrizione = descrizione;
		this.importo = importo;
		this.statoCartella = statoCartella;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getImporto() {
		return importo;
	}

	public void setImporto(Integer importo) {
		this.importo = importo;
	}

	public Stato getStatoCartella() {
		return statoCartella;
	}

	public void setStatoCartella(Stato statoCartella) {
		this.statoCartella = statoCartella;
	}

	public Contribuente getContribuente() {
		return contribuente;
	}

	public void setContribuente(Contribuente contribuente) {
		this.contribuente = contribuente;
	}

	@Override
	public String toString() {
		return "CartellaEsattoriale [id=" + id + ", descrizione=" + descrizione + ", importo=" + importo
				+ ", statoCartella=" + statoCartella + "]";
	} 
	
	

}
