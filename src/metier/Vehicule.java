package metier;

import algo.IntraTourneeInfos;
import algo.MeilleureInsertionInfos;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entité représentant un véhicule.
 * @author user
 */
@Entity
@Table(name = "VEHICULE")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Vehicule.findAll",
			query = "SELECT v FROM Vehicule v"),
		@NamedQuery(name = "Vehicule.findById",
			query = "SELECT v FROM Vehicule v WHERE v.id = :id"),
		@NamedQuery(name = "Vehicule.findByCout",
			query = "SELECT v FROM Vehicule v WHERE v.cout = :cout"),
		@NamedQuery(name = "Vehicule.findByCapaciteutilisee",
			query = "SELECT v FROM Vehicule v WHERE v.capaciteutilisee = :capaciteutilisee"),
		@NamedQuery(name = "Vehicule.findByCapacite",
			query = "SELECT v FROM Vehicule v WHERE v.capacite = :capacite")
})

public class Vehicule implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "COUT")
	private double cout;

	@Column(name = "CAPACITEUTILISEE")
	private Integer capaciteutilisee;

	@Column(name = "CAPACITE")
	private Integer capacite;

	@OneToMany(mappedBy = "nvehicule")
	private List<Client> ensClients;

	@JoinColumn(name = "NINSTANCE", referencedColumnName = "ID")
	@ManyToOne
	private Instance ninstance;

	@JoinColumn(name = "NPLANNING", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Planning nplanning;

	@JoinColumn(name = "NDEPOT", referencedColumnName = "ID")
	@ManyToOne
	private Point ndepot;

	/**
	 * Constructeur par défault.
	 */
	public Vehicule() {
		this.ensClients = new ArrayList<>();
	}

	/**
	 * Constructeur par données.
	 * @param id TODO
	 * @param depot TODO
	 * @param capacite TODO
	 */
	public Vehicule(Integer id,Point depot,Integer capacite) {
		this();
		this.id = id;
		if (capacite < 0) {
			capacite = 0;
		}
		this.capacite = capacite;
		this.capaciteutilisee = 0;
		this.cout = 0.0;
		this.ndepot = depot;
	}

	/**
	 * Constructeur par données.
	 * @param depot TODO
	 * @param capacite TODO
	 */
	public Vehicule(Point depot,Integer capacite) {
		this();
		if (capacite < 0) {
			capacite = 0;
		}
		this.capacite = capacite;
		this.capaciteutilisee = 0;
		this.cout = 0.0;
		this.ndepot = depot;
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

	public Integer getCapaciteutilisee() {
		return capaciteutilisee;
	}

	public void setCapaciteutilisee(Integer capaciteutilisee) {
		this.capaciteutilisee = capaciteutilisee;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	@XmlTransient
	public Set<Client> getEnsClients() {
		return new HashSet<>(ensClients);
	}

	public Instance getNinstance() {
		return ninstance;
	}

	public void setNinstance(Instance ninstance) {
		this.ninstance = ninstance;
	}

	public Planning getNplanning() {
		return nplanning;
	}

	public void setNplanning(Planning nplanning) {
		this.nplanning = nplanning;
	}

	public Point getNdepot() {
		return ndepot;
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
		if (!(object instanceof Vehicule)) {
			return false;
		}
		Vehicule other = (Vehicule) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Vehicule n°" + id + " [capacité max : " + capacite + " \n\t"
				+ "capacité utilisée : " + capaciteutilisee + " \n\tcoût : " + cout + "]";
	}

	/**
	 * Permet d'ajouter un client.
	 * @param c TODO
	 * @return Boolean
	 */
	public boolean addClient(Client c) {
		return this.addClientByPos(c, ensClients.size());
	}

	/**
	 * Permet de vider la liste de clients et réinitialiser les attributs.
	 */
	public void clear() {
		this.nplanning = null;
		this.cout = 0.0;
		this.capaciteutilisee = 0;
		this.ensClients.clear();
	}

	/**
	 * Permet de vérifier si le véhicule est correct.
	 * @return boolean
	 */
	public boolean check() {
		int capa = calculerCapaUtilisee();
		if (capa != this.capaciteutilisee) {
			System.out.println("Capacité mal calculée");
			return false;
		}
		if (capa > this.capacite) {
			System.out.println("Capacité dépassée");
			return false;
		}
		double dist = this.calculerDistance();
		if (Math.abs(dist - this.cout) > 0.0001) {
			System.out.println("Distance mal calculée");
			return false;
		}
		return true;
	}

	/**
	 * Permet de calculer la distance.
	 * @return double
	 */
	private double calculerDistance() {
		double dist = 0;
		List<Client> clients = new ArrayList<>(this.ensClients);
		if (!clients.isEmpty()) {
			dist += this.ndepot.getDistanceTo(clients.get(0));
			for (int i = 0; i < (clients.size() - 1); i++) {
				dist += clients.get(i).getDistanceTo(clients.get(i + 1));
			}
			dist += clients.get(clients.size() - 1).getDistanceTo(this.ndepot);
		}
		return dist;
	}

	/**
	 * Peremt de calculer la capacitée utilisée.
	 * @return int
	 */
	private int calculerCapaUtilisee() {
		int capa = 0;
		for (Client c : this.ensClients) {
			capa += c.getDemand();
		}
		return capa;
	}

	/**
	 * Permet de calculer la meilleure insertion dans une tournée.
	 * @param c TODO
	 * @return MeilleureInsertionInfos
	 */
	public MeilleureInsertionInfos infosMeilleureInsertion(Client c) {
		if (c == null) {
			return null;
		}
		if ((this.capaciteutilisee + c.getDemand()) > this.capacite) {
			return null;
		}
		int position = 0;
		double deltaCout = calculerDeltaCout(c, 0);
		for (int i = 1; i <= ensClients.size(); i++) {
			double d = calculerDeltaCout(c, i);
			if (d < deltaCout) {
				deltaCout = d;
				position = i;
			}
		}
		return new MeilleureInsertionInfos(c, this, position, deltaCout);
	}

	/**
	 * Permet de réaliser l'insertion d'un client au meilleur véhicule et à la
	 * meilleure position.
	 * @param infos TODO
	 * @return boolean
	 */
	public boolean meilleureInsertion(MeilleureInsertionInfos infos) {
		if (infos == null) {
			return false;
		}
		if (!infos.getVehicule().equals(this)) {
			return false;
		}
		return this.addClientByPos(infos.getClient(), infos.getPosition());
	}

	/**
	 * Permet d'ajouter un client à une position donnée.
	 * @param c TODO
	 * @param pos TODO
	 * @return boolean
	 */
	private boolean addClientByPos(Client c, int pos) {
		if (c == null) {
			return false;
		}
		if (pos < 0 || pos > ensClients.size()) {
			return false;
		}
		if ((this.capaciteutilisee + c.getDemand()) > this.capacite) {
			return false;
		}
		if (!c.setVehicule(this)) {
			return false;
		}

		double deltaCout = calculerDeltaCout(c, pos);
		this.ensClients.add(pos, c);
		this.capaciteutilisee += c.getDemand();
		this.cout += deltaCout;
		this.nplanning.setCout(this.nplanning.getCout() + deltaCout);
		return true;
	}

	/**
	 * Permet de calculer le coût delta représentant l'insertion du client c à
	 * la position pos.
	 * @param c TODO
	 * @param pos TODO
	 * @return double
	 */
	private double calculerDeltaCout(Client c, int pos) {
		if (pos < 0 || pos > ensClients.size()) {
			return Double.MAX_VALUE;
		}

		Point prec = ndepot;
		if (pos > 0) {
			prec = ensClients.get(pos - 1);
		}

		Point next = ndepot;
		if (pos < ensClients.size()) {
			next = ensClients.get(pos);
		}

		double previousDistance = 0;
		if (!prec.equals(next)) {
			previousDistance = prec.getDistanceTo(next);
		}
		return prec.getDistanceTo(c) + c.getDistanceTo(next) - previousDistance;
	}

	/**
	 * Permet de calculer le coût d'une fusion.
	 * @param v TODO
	 * @return double
	 */
	public double coutFusion(Vehicule v) {
		if (v == null) {
			return Double.MAX_VALUE;
		}
		if (this.ensClients.isEmpty()) {
			return Double.MAX_VALUE;
		}
		if (v.ensClients.isEmpty()) {
			return Double.MAX_VALUE;
		}
		if ((this.capaciteutilisee + v.getCapaciteutilisee()) > this.capacite) {
			return Double.MAX_VALUE;
		}

		Client i = this.ensClients.get(this.ensClients.size() - 1);
		Client j = v.ensClients.get(0);

		return i.getDistanceTo(j) - i.getDistanceTo(ndepot) - ndepot.getDistanceTo(j);
	}

	/**
	 * Permet la fusion de deux véhicules.
	 * @param v TODO
	 * @return boolean
	 */
	public boolean fusion(Vehicule v) {
		double coutFusion = this.coutFusion(v);
		if (coutFusion > (Double.MAX_VALUE - 1)) {
			return false;
		}

		this.cout += (v.cout + coutFusion);
		this.capaciteutilisee += v.capaciteutilisee;
		this.nplanning.setCout(this.nplanning.getCout() + coutFusion);
		for (Client c : v.ensClients) {
			c.changeVehicule(this);
		}
		this.ensClients.addAll(v.ensClients);
		v.clear();
		this.nplanning.removeVehicule(v);
		return true;
	}

	/**
	 * Retourne les infos sur le déplacment intra d'un véhicule.
	 * @return IntraTourneeInfos
	 */
	public IntraTourneeInfos deplacementIntraVehicule() {
		IntraTourneeInfos intraInfos = new IntraTourneeInfos();
		int nbClients = this.ensClients.size();
		for (int i = 0; i < nbClients; i++) {
			int posClient = i;
			for (int pos = 1; pos < nbClients; pos++) {
				if (pos != posClient) {
					IntraTourneeInfos intraInfosNew = evaluerDeplacement(this.ensClients.get(i),pos);
					if (intraInfosNew.getDiffCout() < intraInfos.getDiffCout()) {
						intraInfos = new IntraTourneeInfos(intraInfosNew);
					}
				}
			}
		}
		return intraInfos;
	}

	/**
	 * Retourne les données représentant l'évaluation du déplacement d'un client.
	 * @param c TODO
	 * @param newPosition TODO
	 * @return IntraTourneeInfos
	 */
	private IntraTourneeInfos evaluerDeplacement(Client c, int newPosition) {
		double diffCout = this.calculerDeltaCout(c, c.getPosition()) - this.calculerDeltaCout(c, newPosition);

		return new IntraTourneeInfos(this,c.getPosition(),newPosition,diffCout);
	}
}
