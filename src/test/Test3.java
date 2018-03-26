package test;

import dao.DaoFactory;
import dao.PersistenceType;
import dao.PlanningDao;
import dao.VehiculeDao;
import metier.Planning;
import metier.Vehicule;

/**
 * Jeux de tests 3.
 * @author user
 */
public class Test3 {

	/**
	 * TODO.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		DaoFactory fabrique = DaoFactory.getDaoFactory(PersistenceType.JPA);
		Planning p = new Planning();
		Vehicule v1 = new Vehicule();
		Vehicule v2 = new Vehicule();
		PlanningDao planningManager = fabrique.getPlanningDao();
		VehiculeDao vehiculeManager = fabrique.getVehiculeDao();
		planningManager.deleteAll();
		vehiculeManager.deleteAll();
		vehiculeManager.create(v1);
		vehiculeManager.create(v2);
		if (p.addVehicule(v1)) {
			System.out.println("Add");
		}
		if (p.addVehicule(v2)) {
			System.out.println("Add");
		}
		planningManager.create(p);
		for (Vehicule v : vehiculeManager.findAll()) {
			System.out.println(v);
		}
	}

}
