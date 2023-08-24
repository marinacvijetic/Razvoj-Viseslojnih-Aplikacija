package rva.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the obrazovanje database table.
 * 
 */
@Entity
@NamedQuery(name="Obrazovanje.findAll", query="SELECT o FROM Obrazovanje o")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Obrazovanje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="OBRAZOVANJE_ID_GENERATOR", sequenceName="OBRAZOVANJE_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OBRAZOVANJE_ID_GENERATOR")
	private Integer id;

	private String naziv;

	private String opis;

	@Column(name="stepen_strucne_spreme")
	private String stepenStrucneSpreme;

	public Obrazovanje() {
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

	public String getStepenStrucneSpreme() {
		return this.stepenStrucneSpreme;
	}

	public void setStepenStrucneSpreme(String stepenStrucneSpreme) {
		this.stepenStrucneSpreme = stepenStrucneSpreme;
	}

}