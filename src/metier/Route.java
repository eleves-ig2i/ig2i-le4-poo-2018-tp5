package metier;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entité représentant une route.
 * @author user
 */
@Entity
@Table(name = "ROUTE")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Route.findAll",
			query = "SELECT r FROM Route r"),
		@NamedQuery(name = "Route.findById",
			query = "SELECT r FROM Route r WHERE r.id = :id"),
		@NamedQuery(name = "Route.findByDistance",
			query = "SELECT r FROM Route r WHERE r.distance = :distance")
})

public class Route implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "DISTANCE")
	private double distance;

	@JoinColumn(name = "NARRIVEE", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Point narrivee;

	@JoinColumn(name = "NDEPART", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Point ndepart;

	/**
	 * Constructeur par défault.
	 */
	public Route() {
	}

	/**
	 * Constructeur par données.
	 * @param distance TODO
	 * @param depart TODO
	 * @param arrivee TODO
	 */
	public Route(double distance, Point depart, Point arrivee) {
		this.distance = distance;
		this.narrivee = arrivee;
		this.ndepart = depart;
	}

	public Integer getId() {
		return id;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Point getNarrivee() {
		return narrivee;
	}

	public void setNarrivee(Point narrivee) {
		this.narrivee = narrivee;
	}

	public Point getNdepart() {
		return ndepart;
	}

	public void setNdepart(Point ndepart) {
		this.ndepart = ndepart;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Route)) {
			return false;
		}
		Route other = (Route) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Route n°" + id + " [distance : " + distance + " ]";
	}

}
