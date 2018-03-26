package algo;

import metier.Client;
import metier.Vehicule;

/**
 * Représente les infos de la meilleure insertion.
 * @author user
 */
public class MeilleureInsertionInfos {
	private Client client;
	private Vehicule vehicule;
	private int position;
	private double cout;

	/**
	 * Constructeur par défault.
	 */
	public MeilleureInsertionInfos() {
		this.position = -1;
		this.cout = Double.MAX_VALUE;
	}

	/**
	 * Constructeur par données.
	 * @param client  TODO
	 * @param vehicule  TODO
	 * @param position TODO
	 * @param cout  TODO
	 */
	public MeilleureInsertionInfos(Client client, Vehicule vehicule, int position, double cout) {
		this.client = client;
		this.vehicule = vehicule;
		this.position = position;
		this.cout = cout;
	}

	/**
	 * Permet de réaliser l'insertion.
	 * @return boolean
	 */
	public boolean doInsertion() {
		return this.vehicule.meilleureInsertion(this);
	}

	/**
	 * Retourne le client.
	 * @return Client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Retourne le véhicule.
	 * @return Vehicule
	 */
	public Vehicule getVehicule() {
		return vehicule;
	}

	/**
	 * Retourne la position.
	 * @return int
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Retourne le coût.
	 * @return double
	 */
	public double getCout() {
		return cout;
	}
}
