package metier;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entité représentant un point.
 * @author user
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "POINTTYPE", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "POINT")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Point.findAll",
			query = "SELECT p FROM Point p"),
		@NamedQuery(name = "Point.findById",
			query = "SELECT p FROM Point p WHERE p.id = :id"),
		@NamedQuery(name = "Point.findByPointtype",
			query = "SELECT p FROM Point p WHERE p.pointtype = :pointtype"),
		@NamedQuery(name = "Point.findByX",
			query = "SELECT p FROM Point p WHERE p.x = :x"),
		@NamedQuery(name = "Point.findByY",
			query = "SELECT p FROM Point p WHERE p.y = :y")
})

public abstract class Point implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "POINTTYPE")
	private Integer pointtype;

	@Column(name = "X")
	//CHECKSTYLE:OFF: MemberNameCheck
	private double x;
	//CHECKSTYLE:ON

	@Column(name = "Y")
	//CHECKSTYLE:OFF: MemberNameCheck
	private double y;
	//CHECKSTYLE:ON

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ndepart")
	@MapKey(name = "narrivee")
	private Map<Point,Route> ensRoutes;

	@JoinColumn(name = "NINSTANCE", referencedColumnName = "ID")
	@ManyToOne
	private Instance ninstance;

	@OneToMany(mappedBy = "ndepot")
	private Set<Vehicule> vehiculeCollection;

	/**
	 * Constructeur par défault.
	 */
	public Point() {
		/*this.routeCollection = new HashSet<>();
		this.routeCollection1 = new HashSet<>();*/
		this.ensRoutes = new HashMap<>();
		this.vehiculeCollection = new HashSet<>();
	}

	/**
	 * Constructeur par données.
	 * @param id TODO
	 * @param x TODO
	 * @param y TODO
	 */
	public Point(Integer id, double x, double y) {
		this();
		this.id = id;
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructeur par données.
	 * @param x TODO
	 * @param y TODO
	 */
	public Point(double x, double y) {
		this();
		this.x = x;
		this.y = y;
	}

	public Integer getId() {
		return id;
	}

	public Integer getPointtype() {
		return pointtype;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Instance getNinstance() {
		return ninstance;
	}

	public void setNinstance(Instance ninstance) {
		this.ninstance = ninstance;
	}

	public Map<Point, Route> getEnsRoutes() {
		return ensRoutes;
	}

	@XmlTransient
	public Set<Vehicule> getVehiculeCollection() {
		return vehiculeCollection;
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
		if (!(object instanceof Point)) {
			return false;
		}
		Point other = (Point) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Point n°" + id + "[x : " + x + "\n\ty : " + y + "]";
	}

	/**
	 * Permet d'ajouter une destination.
	 * @param p TODO
	 * @param distance TODO
	 * @return Boolean
	 */
	public boolean addDestination(Point p, double distance) {
		Route route = new Route(distance,this,p);
		if (this.ensRoutes.put(route.getNarrivee(), route) == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Retourne la distance entre le point "this" et le point d'id key.
	 * @param key  TODO
	 * @return double
	 */
	public double getDistanceTo(Point key) {
		if (this.ensRoutes.containsKey(key)) {
			return this.ensRoutes.get(key).getDistance();
		} else {
			return Double.POSITIVE_INFINITY;
		}
	}

}
