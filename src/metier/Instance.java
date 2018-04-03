package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entité représentant une instance.
 * @author user
 */
@Entity
@Table(name = "INSTANCE")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Instance.findAll",
			query = "SELECT i FROM Instance i"),
		@NamedQuery(name = "Instance.findById",
			query = "SELECT i FROM Instance i WHERE i.id = :id"),
		@NamedQuery(name = "Instance.findByNom",
			query = "SELECT i FROM Instance i WHERE i.nom = :nom")
})

public class Instance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOM")
	private String nom;

	@OneToMany(mappedBy = "ninstance")
	private Set<Point> pointSet;

	@OneToOne(mappedBy = "ninstance")
	private Planning nplanning;

	@OneToMany(mappedBy = "ninstance")
	private Set<Vehicule> vehiculeSet;

	/**
	 * Constructeur par défault.
	 */
	public Instance() {
		this.nplanning = null;
		this.vehiculeSet = new HashSet<>();
		this.pointSet = new HashSet<>();
	}

	/**
	 * Constructeur par données.
	 * @param nom TODO
	 */
	public Instance(String nom) {
		this();
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@XmlTransient
	public Set<Point> getPointSet() {
		return pointSet;
	}

	public Planning getnPlanning() {
		return nplanning;
	}

	@XmlTransient
	public Set<Vehicule> getVehiculeSet() {
		return vehiculeSet;
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
		if (!(object instanceof Instance)) {
			return false;
		}
		Instance other = (Instance) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String retour = "Instance n°" + id + " [\n\tVéhicules :\n\t";
		for (Vehicule v : vehiculeSet) {
			retour += v.toString();
		}
		retour += "\n\tPoints :\n\t";
		for (Point p : pointSet) {
			retour += p.toString();
		}
		return retour + "\n]";
	}

	/**
	 * Retourne une liste de véhicules.
	 * @return list of vehicules
	 */
	public List<Vehicule> getVehicules() {
		return new ArrayList<>(this.vehiculeSet);
	}

	/**
	 * Retourne une liste de clients.
	 * @return list of clients
	 */
	public List<Client> getClients() {
		List<Client> clients = new ArrayList<>();
		for (Point p : this.pointSet) {
			if (p instanceof Client) {
				clients.add((Client) p);
			}
		}
		return clients;
	}

	/**
	 * Permet d'un ajouter un véhicule au planning.
	 * @param v TODO
	 */
	public void addVehiculeInPlanning(Vehicule v) {
		this.nplanning.addVehicule(v);
	}

	/**
	 * Mets à jour les positions des clients dans les véhicules du planning.
	 */
	public void updatePositions() {
		this.nplanning.updatePositionClients();
	}

	/**
	 * Renvoie le coût du planning.
	 * @return double
	 */
	public double getCoutPlanning() {
		return this.nplanning.getCout();
	}

	/**
	 * Permet d'afficher le planning.
	 */
	public void printPlanning() {
		System.out.println(this.nplanning);
	}

	/**
	 * Permet de vider les éléments liés à la solution : les clients ne doivent
	 * plus être affectés à un véhicule, et avoir une position par défault, les
	 * véhicules doivent vider leur liste de clients et réinitialiser les
	 * attributs, et le planning doit vider son ensemble de véhicules et
	 * réinitialiser son coût.
	 */
	public void clear() {
		for (Point p : this.pointSet) {
			if (p instanceof Client) {
				((Client) p).clear();
			}
		}
		for (Vehicule v : this.vehiculeSet) {
			v.clear();
		}
		this.nplanning.clear();
	}

	/**
	 * Permet de vérifier si le planning est réalisable.
	 * @return boolean
	 */
	public boolean checkPlanning() {
		return this.nplanning.check();
	}

	/**
	 * Permet de déterminer si un planning a été amélioré ou pas.
	 * @return boolean
	 */
	public boolean deplacementIntraVehicule() {
		if (this.nplanning == null) {
			return false;
		}
		return this.nplanning.deplacementIntraVehicule();
	}

	/**
	 * Permet de déterminer si un planning a été amélioré ou pas par échange de
	 * véhicule.
	 * @return boolean
	 */
	public boolean echangeIntraVehicule() {
		if (this.nplanning == null) {
			return false;
		}
		return this.nplanning.echangeIntraVehicule();
	}

}
