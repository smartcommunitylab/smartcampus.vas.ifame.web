/*
package eu.trentorise.smartcampus.vas.ifame.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@XmlRootElement(name = "Piatto_Mensa")
@NamedQuery(name = "Piatto_Mensa.getPiattiWhereMensaIdEquals", query = "from Piatto_Mensa p where  p.mensa.mensa_id = ?1")
public class Piatto_Mensa {

	@Id
	@GeneratedValue
	private Long piatto_mensa_id;

	@Column(name = "NUMERO_GIUDIZI")
	private Long numero_giudizi;

	@Column(name = "VOTO_MEDIO")
	private Float voto_medio;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "MENSA_ID")
	private Mensa mensa;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PIATTO_ID")
	private Piatto piatto;

	public Piatto_Mensa() {
		super();
	}

	public Piatto_Mensa(Long numero_giudizi, Float voto_medio, Mensa mensa,
			Piatto piatto) {
		super();
		this.numero_giudizi = numero_giudizi;
		this.voto_medio = voto_medio;
		this.mensa = mensa;
		this.piatto = piatto;
	}

	public Long getPiatto_mensa_id() {
		return piatto_mensa_id;
	}

	public void setPiatto_mensa_id(Long piatto_mensa_id) {
		this.piatto_mensa_id = piatto_mensa_id;
	}

	public Long getNumero_giudizi() {
		return numero_giudizi;
	}

	public void setNumero_giudizi(Long numero_giudizi) {
		this.numero_giudizi = numero_giudizi;
	}

	public Float getVoto_medio() {
		return voto_medio;
	}

	public void setVoto_medio(Float voto_medio) {
		this.voto_medio = voto_medio;
	}

	public Mensa getMensa() {
		return mensa;
	}

	public void setMensa(Mensa mensa) {
		this.mensa = mensa;
	}

	public Piatto getPiatto() {
		return piatto;
	}

	public void setPiatto(Piatto piatto) {
		this.piatto = piatto;
	}

}
*/