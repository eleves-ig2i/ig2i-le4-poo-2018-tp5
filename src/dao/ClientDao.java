package dao;

import java.util.Collection;
import metier.Client;

/**
 * Interface générique représentant un DAO de type Client.
 * @author user
 */
public interface ClientDao extends DAO<Client> {

	/**
	 * Méthode permettant de renvoyer tous les clients qui ne sont pas encore
	 * livrés par un véhicule.
	 * @return collection of object
	 */
	public Collection<Client> findNotServed();

}
