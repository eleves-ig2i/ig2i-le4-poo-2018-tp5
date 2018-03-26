package test;

import metier.Client;
import metier.Depot;
import metier.Planning;
import metier.Vehicule;

/**
 * Jeux de tests 1.
 * @author user
 */
public class Test1 {

	/**
	 * TODO.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		Depot d = new Depot(0, 0, 0);
		Client c1 = new Client(1, 10, 10, 10);
		Client c2 = new Client(2, -10, 10, 5);
		Client c3 = new Client(3, 10, -10, 10);
		d.addDestination(c1, 14.1);
		d.addDestination(c2, 14.1);
		d.addDestination(c3, 14.1);
		c1.addDestination(d, 14.1);
		c1.addDestination(c2, 20);
		c1.addDestination(c3, 20);
		c2.addDestination(d, 14.1);
		c2.addDestination(c1, 20);
		c2.addDestination(c3, 20);
		c3.addDestination(d, 14.1);
		c3.addDestination(c1, 20);
		c3.addDestination(c2, 20);
		Vehicule v1 = new Vehicule(0, d, 15);
		Vehicule v2 = new Vehicule(1, d, 15);
		Planning p = new Planning();
		p.addVehicule(v1);
		p.addVehicule(v2);
		if (!v1.addClient(c1)) {
			v2.addClient(c1);
		}
		if (!v1.addClient(c2)) {
			v2.addClient(c2);
		}
		if (!v1.addClient(c3)) {
			v2.addClient(c3);
		}
		p.updatePositionClients();
		System.out.println(p.toString());
		// Afficher le planning avec ses véhicules et les clients sur les véhicules
		// Vérifier cout total des véhicules
		// Vérifier capacité utilisée des véhicules
	}

}
