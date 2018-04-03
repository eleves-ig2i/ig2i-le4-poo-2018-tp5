package algo;

import java.util.ArrayList;
import metier.Client;
import metier.Vehicule;

/**
 * Représente les infos liées à un opérateur dans une même tournée.
 * @author user
 */
public class IntraTourneeInfos {
	private Vehicule vehicule;

	/**
	 * Ancienne position du client.
	 */
	private int oldPosition;

	/**
	 * Nouvelle position du client.
	 */
	private int newPosition;

	/**
	 * Différence de coût entre la solution initiale et la solution après le
	 * déplacement.
	 */
	private double diffCout;

	/**
	 * Constructeur par défault.
	 */
	public IntraTourneeInfos() {
		this.vehicule = null;
		this.oldPosition = -1;
		this.newPosition = -1;
		this.diffCout = Double.MAX_VALUE;
	}

	/**
	 * Constructeur par données.
	 * @param vehicule TODO
	 * @param oldPosition TODO
	 * @param newPosition TODO
	 * @param diffCout TODO
	 */
	public IntraTourneeInfos(Vehicule vehicule, int oldPosition, int newPosition, double diffCout) {
		this.vehicule = vehicule;
		this.oldPosition = oldPosition;
		this.newPosition = newPosition;
		this.diffCout = diffCout;
	}

	/**
	 * Constructeur par copie.
	 * @param intraTourneeInfos TODO
	 */
	public IntraTourneeInfos(IntraTourneeInfos intraTourneeInfos) {
		this.vehicule = intraTourneeInfos.vehicule;
		this.oldPosition = intraTourneeInfos.oldPosition;
		this.newPosition = intraTourneeInfos.newPosition;
		this.diffCout = intraTourneeInfos.diffCout;
	}

	/**
	 * Méthode exécutant le déplacement qui permet d’améliorer le plus la
	 * solution courante.
	 * @return boolean
	 */
	public boolean doDeplacementIntraTournee() {
		if (this.diffCout == Double.MAX_VALUE) {
			return false;
		}
		if (this.newPosition == -1 || this.oldPosition == -1) {
			return false;
		}

		if (this.vehicule == null) {
			return false;
		} else {
			return this.vehicule.doDeplacementIntraTournee(this);
		}
	}

	/**
	 * Méthode exécutant l'échange qui permet d’améliorer le plus la
	 * solution courante.
	 * @return boolean
	 */
	public boolean doEchangeIntraTournee() {
		if (this.diffCout == Double.MAX_VALUE) {
			return false;
		}
		if (this.newPosition == -1 || this.oldPosition == -1) {
			return false;
		}

		if (this.vehicule == null) {
			return false;
		} else {
			return this.vehicule.doEchangeIntraTournee(this);
		}
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public int getOldPosition() {
		return oldPosition;
	}

	public int getNewPosition() {
		return newPosition;
	}

	public double getDiffCout() {
		return diffCout;
	}

}
