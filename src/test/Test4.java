package test;

import dao.DaoFactory;
import dao.InstanceDao;
import dao.PersistenceType;
import metier.Instance;

/**
 * Jeux de tests 4.
 * @author user
 */
public class Test4 {

	/**
	 * TODO.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		DaoFactory fabrique = DaoFactory.getDaoFactory(PersistenceType.JPA);
		InstanceDao instanceManager = fabrique.getInstanceDao();
		Instance inst = instanceManager.findByName("tiny_test");
		System.out.println(inst);
	}

}
