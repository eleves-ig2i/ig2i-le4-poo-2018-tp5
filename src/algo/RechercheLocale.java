package algo;

import metier.Instance;

/**
 * Représente une recherche locale.
 * @author user
 */
public class RechercheLocale {
	private Instance instance;

	/**
	 * Constructeur par données.
	 * @param instance TODO
	 */
	public RechercheLocale(Instance instance) {
		this.instance = instance;
	}

	/**
	 * Permet de déterminer si un planning a été amélioré ou pas.
	 * @return boolean
	 */
	private boolean deplacementIntraVehicule() {
		return this.instance.deplacementIntraVehicule();
	}
}
