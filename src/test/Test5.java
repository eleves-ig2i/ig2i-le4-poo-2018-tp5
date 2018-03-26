package test;

import algo.HeuristiqueConstructive;
import dao.DaoFactory;
import dao.InstanceDao;
import dao.PersistenceType;
import metier.Instance;
import metier.Vehicule;

/**
 * Jeux de tests 5.
 * @author user
 */
public class Test5 {

	/**
	 * TODO.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		DaoFactory fabrique = DaoFactory.getDaoFactory(PersistenceType.JPA);
		InstanceDao instanceManager = fabrique.getInstanceDao();
		Instance inst = instanceManager.findByName("tiny_test");
		HeuristiqueConstructive heuristique = new HeuristiqueConstructive(inst);
		heuristique.insertionSimple();
		System.out.println("Coût : " + inst.getCoutPlanning());
		inst.printPlanning();
		instanceManager.update(inst);
	}

}
