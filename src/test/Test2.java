package test;

import dao.ClientDao;
import dao.DepotDao;
import dao.JpaClientDao;
import dao.JpaDepotDao;
import dao.JpaPlanningDao;
import dao.JpaVehiculeDao;
import dao.PlanningDao;
import dao.VehiculeDao;
import metier.Client;
import metier.Depot;
import metier.Planning;
import metier.Vehicule;



/**
 * Jeux de tests 2.
 * @author user
 */
public class Test2 {

	/**
	 * TODO.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		Depot d = new Depot(0, 0);
		//DepotDao depotManager = new JpaDepotDao();
		DepotDao depotManager = JpaDepotDao.getInstance();
		depotManager.deleteAll();
		depotManager.create(d);
		Client c1 = new Client(10, 10, 10);
		Client c2 = new Client(-10, 10, 5);
		Client c3 = new Client(10, -10, 10);
		//ClientDao clientManager = new JpaClientDao();
		ClientDao clientManager = JpaClientDao.getInstance();
		clientManager.deleteAll();
		clientManager.create(c1);
		clientManager.create(c2);
		clientManager.create(c3);
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
		depotManager.update(d);
		clientManager.update(c1);
		clientManager.update(c2);
		clientManager.update(c3);
		Vehicule v1 = new Vehicule(d, 15);
		Vehicule v2 = new Vehicule(d, 15);
		//VehiculeDao vehiculeManager = new JpaVehiculeDao();
		VehiculeDao vehiculeManager = JpaVehiculeDao.getInstance();
		vehiculeManager.create(v1);
		vehiculeManager.create(v2);
		Planning p = new Planning();
		//PlanningDao planningManager = new JpaPlanningDao();
		PlanningDao planningManager = JpaPlanningDao.getInstance();
		vehiculeManager.deleteAll();
		planningManager.deleteAll();
		p.addVehicule(v1);
		p.addVehicule(v2);
		planningManager.create(p);
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
		clientManager.update(c1);
		clientManager.update(c2);
		clientManager.update(c3);
		System.out.println(p.toString());

		//ClientDao clientManager2 = new JpaClientDao();
		ClientDao clientManager2 = JpaClientDao.getInstance();
		clientManager2.create(c1);
		System.out.println(clientManager.findAll().size());

		// Question 25
		/*Planning p = new Planning();
		Vehicule v1 = new Vehicule();
		Vehicule v2 = new Vehicule();
		PlanningDao planningManager = JpaPlanningDao.getInstance();
		VehiculeDao vehiculeManager = JpaVehiculeDao.getInstance();
		planningManager.deleteAll();
		vehiculeManager.deleteAll();
		vehiculeManager.create(v1);
		vehiculeManager.create(v2);
		p.addVehicule(v1);
		p.addVehicule(v2);
		planningManager.create(p);
		for	(Vehicule v : vehiculeManager.findAll()) {
			System.out.println(v);
		}*/
	}

}
