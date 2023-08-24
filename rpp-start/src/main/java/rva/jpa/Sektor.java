package rva.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the sektor database table.
 * 
 */
@Entity
@NamedQuery(name="Sektor.findAll", query="SELECT s FROM Sektor s")
public class Sektor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEKTOR_IDSEKTOR_GENERATOR", sequenceName="SEKTOR_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEKTOR_IDSEKTOR_GENERATOR")
	@Column(name="id_sektor")
	private Integer idSektor;

	private String naziv;

	private String oznaka;

	//bi-directional many-to-one association to Radnik
	@JsonIgnore
	@OneToMany(mappedBy="sektor", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Radnik> radniks;

	//bi-directional many-to-one association to Preduzece
	@ManyToOne
	@JoinColumn(name="preduzece")
	private Preduzece preduzece;

	public Sektor() {
	}

	public Integer getIdSektor() {
		return this.idSektor;
	}

	public void setIdSektor(Integer idSektor) {
		this.idSektor = idSektor;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public List<Radnik> getRadniks() {
		return this.radniks;
	}

	public void setRadniks(List<Radnik> radniks) {
		this.radniks = radniks;
	}

	public Radnik addRadnik(Radnik radnik) {
		getRadniks().add(radnik);
		radnik.setSektor(this);

		return radnik;
	}

	public Radnik removeRadnik(Radnik radnik) {
		getRadniks().remove(radnik);
		radnik.setSektor(null);

		return radnik;
	}

	public Preduzece getPreduzece() {
		return this.preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

}