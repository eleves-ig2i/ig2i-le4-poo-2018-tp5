package metier;

import algo.IntraTourneeInfos;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entité représentant un planning.
 * @author user
 */
@Entity
@Table(name = "PLANNING")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Planning.findAll",
			query = "SELECT p FROM Planning p"),
		@NamedQuery(name = "Planning.findById",
			query = "SELECT p FROM Planning p WHERE p.id = :id"),
		@NamedQuery(name = "Planning.findByCout",
			query = "SELECT p FROM Planning p WHERE p.cout = :cout")
})

public class Planning implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "COUT")
	private double cout;

	@JoinColumn(name = "NINSTANCE", referencedColumnName = "ID")
	@OneToOne
	private Instance ninstance;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "nplanning")
	private Set<Vehicule> ensVehicules;

	/**
	 * Constructeur par défault.
	 */
	public Planning() {
		this.ensVehicules = new HashSet<>();
	}

	/**
	 * Constructeur par données.
	 * @param cout TODO
	 */
	public Planning(double cout) {
		this();
		if (cout < 0) {
			cout = 0.0;
		}
		this.cout = cout;
	}

	public Integer getId() {
		return id;
	}

	public double getCout() {
		return cout;
	}

	public void setCout(double cout) {
		this.cout = cout;
	}

	public Instance getNinstance() {
		return ninstance;
	}

	public void setNinstance(Instance ninstance) {
		this.ninstance = ninstance;
	}

	@XmlTransient
	public Set<Vehicule> getEnsVehicules() {
		return ensVehicules;
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
		if (!(object instanceof Planning)) {
			return false;
		}
		Planning other = (Planning) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String retour = "Planning n°" + id + " : coût => " + cout;
		for (Vehicule v : this.ensVehicules) {
			retour += "\n\t" + v.toString();
			for (Client c : v.getEnsClients()) {
				retour += "\n\t\t" + c.toString();
			}
		}
		return retour;
	}

	/**
	 * Permet d'ajouter un véhicule au planning.
	 * @param v TODO
	 * @return Boolean
	 */
	public boolean addVehicule(Vehicule v) {
		if (this.ensVehicules.add(v)) {
			v.setNplanning(this);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Permet de mettre à jour la position d'un client.
	 */
	public void updatePositionClients() {
		int i = 0;
		for (Vehicule v : this.ensVehicules) {
			i = 0;
			for (Client c : v.getEnsClients()) {
				c.setPosition(i);
				i++;
			}
		}
	}

	/**
	 * Permet de vider l'ensemble des véhicules et réinitialiser les atrributs.
	 */
	public void clear() {
		this.cout = 0.0;
		this.ensVehicules.clear();
	}

	/**
	 * Permet de vérifier si le planning est réalisable.
	 * @return boolean
	 */
	public boolean check() {
		Set<Client> clientsServis = new HashSet<>();
		double coutTotal = 0;
		for (Vehicule v : this.ensVehicules) {
			if (!v.check()) {
				System.out.println("Vehicule incorrect : " + v);
				return false;
			}
			coutTotal += v.getCout();
			clientsServis.addAll(v.getEnsClients());
		}
		if (clientsServis.size() != this.ninstance.getClients().size()) {
			System.out.println("Tous les clients ne sont pas servis");
			return false;
		}
		if (Math.abs(coutTotal - this.cout) > 0.0001) {
			System.out.println("Mauvais calcul du coût total");
			return false;
		}
		return true;
	}

	/**
	 * Peremt de supprimer un véhicule du planning.
	 * @param v TODO
	 * @return boolean
	 */
	public boolean removeVehicule(Vehicule v) {
		if (v == null) {
			return false;
		}
		if (!v.getEnsClients().isEmpty()) {
			return false;
		}
		return this.ensVehicules.remove(v);
	}

	/**
	 * Permet de déterminer si un planning a été amélioré ou pas.
	 * @return boolean
	 */
	public boolean deplacementIntraVehicule() {
		IntraTourneeInfos intraTourneeInfos = new IntraTourneeInfos();
		for (Vehicule v : this.ensVehicules) {
			IntraTourneeInfos tmp = v.deplacementIntraVehicule();
			if (intraTourneeInfos.getDiffCout() > tmp.getDiffCout()) {
				intraTourneeInfos = tmp;
			}
		}
		if (intraTourneeInfos.getDiffCout() < 0) {
			return intraTourneeInfos.doDeplacementIntraTournee();
		}
		return false;
	}

	/**
	 * Permet de déterminer si un planning a été amélioré ou pas par échange de
	 * véhicule.
	 * @return boolean
	 */
	public boolean echangeIntraVehicule() {
		IntraTourneeInfos intraTourneeInfos = new IntraTourneeInfos();
		for (Vehicule v : this.ensVehicules) {
			IntraTourneeInfos tmp = v.echangeIntraVehicule();
			if (intraTourneeInfos.getDiffCout() > tmp.getDiffCout()) {
				intraTourneeInfos = tmp;
			}
		}
		if (intraTourneeInfos.getDiffCout() < 0) {
			return intraTourneeInfos.doEchangeIntraTournee();
		}
		return false;
	}
}
