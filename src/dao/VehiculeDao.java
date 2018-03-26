package dao;

import java.util.Collection;
import metier.Vehicule;

/**
 * Interface générique représentant un DAO de type Vehicule.
 * @author user
 */
public interface VehiculeDao extends DAO<Vehicule> {

	/**
	 * Méthode permettant de renvoyer toutes les véhicules non encore utilisés
	 * (qui ne sont pas affectés à un planning).
	 * @return collection of object
	 */
	public Collection<Vehicule> findAllNotUsed();

}
