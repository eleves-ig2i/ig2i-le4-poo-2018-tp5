package test;

import algo.HeuristiqueConstructive;
import dao.DaoFactory;
import dao.InstanceDao;
import dao.PersistenceType;
import metier.Instance;
import metier.Vehicule;

/**
 * Jeux de tests 8.
 * @author user
 */
public class Test8 {

	/**
	 * TODO.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		testToutesInstances();
	}

	/**
	 * Jeux de tests sur toutes les instances.
	 */
	public static void testToutesInstances() {
		DaoFactory fabrique = DaoFactory.getDaoFactory(PersistenceType.JPA);
		InstanceDao instanceManager = fabrique.getInstanceDao();
		for (Instance inst : instanceManager.findAll()) {
			HeuristiqueConstructive heur = new HeuristiqueConstructive(inst);
			heur.meilleureInsertion();
			System.out.println("Instance : " + inst.getNom()
					+ "\tCout : " + inst.getCoutPlanning()
					+ "\tNb vehicules : " + inst.getnPlanning().getEnsVehicules().size());
			instanceManager.update(inst);
		}
	}
}
