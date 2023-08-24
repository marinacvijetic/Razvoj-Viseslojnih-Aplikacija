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
 * The persistent class for the obrazovanje database table.
 * 
 */
@Entity
@NamedQuery(name="Obrazovanje.findAll", query="SELECT o FROM Obrazovanje o")
public class Obrazovanje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="OBRAZOVANJE_IDOBRAZOVANJE_GENERATOR", sequenceName="OBRAZOVANJE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OBRAZOVANJE_IDOBRAZOVANJE_GENERATOR")
	@Column(name="id_obrazovanje")
	private Integer idObrazovanje;

	private String naziv;

	private String opis;

	@Column(name="stepen_strucne_spreme")
	private String stepenStrucneSpreme;

	//bi-directional many-to-one association to Radnik
	@JsonIgnore
//	@JsonBackReference
	@OneToMany(mappedBy="obrazovanje", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Radnik> radnik;

	public Obrazovanje() {
	}

	public Integer getIdObrazovanje() {
		return this.idObrazovanje;
	}

	public void setIdObrazovanje(Integer idObrazovanje) {
		this.idObrazovanje = idObrazovanje;
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

	public String getStepenStrucneSpreme() {
		return this.stepenStrucneSpreme;
	}

	public void setStepenStrucneSpreme(String stepenStrucneSpreme) {
		this.stepenStrucneSpreme = stepenStrucneSpreme;
	}

//	public List<Radnik> getRadniks() {
//		return this.radniks;
//	}
//
//	public void setRadniks(List<Radnik> radniks) {
//		this.radniks = radniks;
//	}
//
//	public Radnik addRadnik(Radnik radnik) {
//		getRadniks().add(radnik);
//		radnik.setObrazovanjeBean(this);
//
//		return radnik;
//	}
//
//	public Radnik removeRadnik(Radnik radnik) {
//		getRadniks().remove(radnik);
//		radnik.setObrazovanjeBean(null);
//
//		return radnik;
//	}

}