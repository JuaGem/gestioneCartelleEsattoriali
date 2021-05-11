package it.prova.gestionecartelleesattoriali.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contribuente")
public class Contribuente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank(message = "{nome.notblank}")
	@Column(name = "nome")
	private String nome;
	
	@NotBlank(message = "{cognome.notblank}")
	@Column(name = "cognome")
	private String cognome;
	
	@NotNull(message = "{dataNascita.notnull}")
	@Column(name = "data_nascita")
	private Date dataNascita;
	
	@NotBlank(message = "{codFis.notblank}")
	@Size(min = 16, max = 16, message = "Il valore inserito '${validatedValue}' deve essere lungo {max} caratteri")
	@Column(name = "codice_fiscale")
	private String codFis;
	
	@NotBlank(message = "{indirizzo.notblank}")
	@Size(min = 10, max = 30, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	@Column(name = "indirizzo")
	private String indirizzo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contribuente")
	private List<CartellaEsattoriale> cartelle = new ArrayList<CartellaEsattoriale>();
	
	public Contribuente() {
	}

	public Contribuente(String nome, String cognome,Date dataNascita, String codFis, String indirizzo) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codFis = codFis;
		this.indirizzo=indirizzo;
	}
	
	public Contribuente(Long id, String nome, String cognome,Date dataNascita, String codFis, String indirizzo) {
		this.id=id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codFis = codFis;
		this.indirizzo=indirizzo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCodFis() {
		return codFis;
	}

	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public List<CartellaEsattoriale> getCartelle() {
		return cartelle;
	}

	public void setCartelle(List<CartellaEsattoriale> cartelle) {
		this.cartelle = cartelle;
	}
	
	public boolean evidenziaContensioso() {
		boolean check = false;
		for (CartellaEsattoriale itemCartella : this.cartelle) {
			if (itemCartella.getStatoCartella() == Stato.IN_CONTENZIOSO) {
				check = true;
			}
		}
		return check;

	}

	public Double sommaTotaleCartelle() {
		Double sum = 0.0;
		for (CartellaEsattoriale itemCartella : this.cartelle) {
			sum += itemCartella.getImporto();
		}
		return sum;
	}

	public Double sommaCartelleChiuse() {
		Double sum = 0.0;
		for (CartellaEsattoriale itemCartella : this.cartelle) {
			if (itemCartella.getStatoCartella() == Stato.CONCLUSA) {
				sum += itemCartella.getImporto();
			}
		}
		return sum;
	}

	public Double sommaContenzioso() {
		Double sum = 0.0;
		for (CartellaEsattoriale itemCartella : this.cartelle) {
			if (itemCartella.getStatoCartella() == Stato.IN_CONTENZIOSO) {
				sum += itemCartella.getImporto();
			}
		}
		return sum;
	}

}
