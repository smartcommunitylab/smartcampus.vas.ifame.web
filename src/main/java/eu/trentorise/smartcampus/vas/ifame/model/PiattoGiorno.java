package eu.trentorise.smartcampus.vas.ifame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "PiattoGiorno")
@NamedQueries({ @NamedQuery(name = "PiattoGiorno.getPiattiDelGiorno", query = "from PiattoGiorno where day = ?1 order by ordine asc") })
public class PiattoGiorno {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "DAY")
	private int day;

	@Column(name = "ORDINE")
	private Integer ordine;

	@Column(name = "PIATTO_ID")
	private Long piattoId;

	public PiattoGiorno() {
		super();
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Long getPiattoId() {
		return piattoId;
	}

	public void setPiattoId(Long piattoId) {
		this.piattoId = piattoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrdine() {
		return ordine;
	}

	public void setOrdine(Integer ordine) {
		this.ordine = ordine;
	}

}