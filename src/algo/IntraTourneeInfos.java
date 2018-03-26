package algo;

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
		this.diffCout = 0.0;
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

}
