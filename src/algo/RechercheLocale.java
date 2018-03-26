package algo;

import dao.DaoFactory;
import dao.InstanceDao;
import dao.PersistenceType;
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

	/**
	 * Méthode principale.
	 * @param args TODO
	 */
	public static void main(String[] args) {
		DaoFactory fabrique = DaoFactory.getDaoFactory(PersistenceType.JPA);
		InstanceDao instanceManager = fabrique.getInstanceDao();
		for (Instance inst : instanceManager.findAll()) {
			HeuristiqueConstructive heur = new HeuristiqueConstructive(inst);
			heur.clarkeAndWright();
			System.out.println("Instance : " + inst.getNom()
					+ "\tCout : " + inst.getCoutPlanning()
					+ "\tNb vehicules : " + inst.getnPlanning().getEnsVehicules().size());
			instanceManager.update(inst);
		}
		System.out.println("\n\n\n");
		for (Instance inst : instanceManager.findAll()) {
			//CHECKSTYLE:OFF: LocalVariableNameCheck
			RechercheLocale rL = new RechercheLocale(inst);
			//CHECKSTYLE:ON
			while (rL.deplacementIntraVehicule()) {				
				instanceManager.update(inst);
			}
			System.out.println("Instance : " + inst.getNom()
					+ "\tCout : " + inst.getCoutPlanning()
					+ "\tNb vehicules : " + inst.getnPlanning().getEnsVehicules().size());
		}

	}
}
