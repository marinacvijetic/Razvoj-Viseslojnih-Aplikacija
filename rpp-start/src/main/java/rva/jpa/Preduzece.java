package rva.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the preduzece database table.
 * 
 */
@Entity
@NamedQuery(name="Preduzece.findAll", query="SELECT p FROM Preduzece p")
public class Preduzece implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PREDUZECE_IDPREDUZECE_GENERATOR", sequenceName="PREDUZECE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PREDUZECE_IDPREDUZECE_GENERATOR")
	@Column(name="id_preduzece")
	private Integer idPreduzece;

	private String naziv;
	
	private Integer pib;

	private String sediste;
	
	private String opis;



	//bi-directional many-to-one association to Sektor
	@JsonIgnore
	@OneToMany(mappedBy="preduzece", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Sektor> sektors;

	public Preduzece() {
	}

	public Integer getIdPreduzece() {
		return this.idPreduzece;
	}

	public void setIdPreduzece(Integer idPreduzece) {
		this.idPreduzece = idPreduzece;
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

	public List<Sektor> getSektors() {
		return this.sektors;
	}

	public void setSektors(List<Sektor> sektors) {
		this.sektors = sektors;
	}

	public Sektor addSektor(Sektor sektor) {
		getSektors().add(sektor);
		sektor.setPreduzece(this);

		return sektor;
	}

	public Sektor removeSektor(Sektor sektor) {
		getSektors().remove(sektor);
		sektor.setPreduzece(null);

		return sektor;
	}

}