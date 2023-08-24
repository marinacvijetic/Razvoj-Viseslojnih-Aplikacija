package rva.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the preduzece database table.
 * 
 */
@Entity
@NamedQuery(name="Preduzece.findAll", query="SELECT p FROM Preduzece p")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Preduzece implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PREDUZECE_ID_GENERATOR", sequenceName="PREDUZECE_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PREDUZECE_ID_GENERATOR")
	private Integer id;

	private String naziv;

	private String opis;

	private Integer pib;

	private String sediste;
	

	public Preduzece() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Integer getPib() {
		return this.pib;
	}

	public void setPib(Integer pib) {
		this.pib = pib;
	}

	public String getSediste() {
		return this.sediste;
	}

	public void setSediste(String sediste) {
		this.sediste = sediste;
	}

}