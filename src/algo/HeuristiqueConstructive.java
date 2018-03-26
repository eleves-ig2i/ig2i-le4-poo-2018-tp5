package algo;

import java.util.ArrayList;
import java.util.List;
import metier.Client;
import metier.Instance;
import metier.Vehicule;

/**
 * Implémente des algorithmes heuristiques constructifs.
 * @author user
 */
public class HeuristiqueConstructive {
	private Instance instance;

	/**
	 * Constrcuteur par données.
	 * @param instance TODO
	 */
	public HeuristiqueConstructive(Instance instance) {
		this.instance = instance;
	}

	/**
	 * Permet une insertion simple.
	 */
	public void insertionSimple() {
		this.instance.clear();
		List<Client> clients = this.instance.getClients();
		List<Vehicule> vehicules = this.instance.getVehicules();
		List<Vehicule> vehiculesUtilises = new ArrayList<>();

		for (Client c : clients) {
			boolean affecte = false;
			for (Vehicule v : vehiculesUtilises) {
				if (v.addClient(c)) {
					affecte = true;
					break;
				}
			}
			if (!affecte) {
				if (vehicules.isEmpty()) {
					System.out.println("Erreur : Plus de vehicule dispo pour "
							+ "affecter le client " + c);
				} else {
					Vehicule v = vehicules.remove(0);
					this.instance.addVehiculeInPlanning(v);
					if (!v.addClient(c)) {
						System.out.println("Erreur : client " + c + " n'a pas "
								+ "pu être affecté au vehicule " + v);
					}
					vehiculesUtilises.add(v);
				}
			}
		}

		this.instance.updatePositions();
		if (!instance.checkPlanning()) {
			System.out.println("Solution FAUSSE !!!");
		} else {
			System.out.println("Solution BONNE !!!");
		}
	}

	/**
	 * Permet une insertion du client le plus proche.
	 */
	public void insertionClientProche() {
		this.instance.clear();
		List<Client> clients = this.instance.getClients();
		List<Vehicule> vehicules = this.instance.getVehicules();
		List<Vehicule> vehiculesUtilises = new ArrayList<>();
		int next = 0;

		while (!clients.isEmpty()) {
			Client c = clients.remove(next);
			boolean affecte = false;
			for (int i = 0; i < vehiculesUtilises.size(); i++) {
				Vehicule v = vehiculesUtilises.get(vehiculesUtilises.size() - 1 - i);
				if (v.addClient(c)) {
					affecte = true;
					break;
				}
			}
			if (!affecte) {
				if (vehicules.isEmpty()) {
					System.out.println("Erreur : Plus de vehicule dispo pour "
							+ "affecter le client " + c);
				} else {
					Vehicule v = vehicules.remove(0);
					this.instance.addVehiculeInPlanning(v);
					if (!v.addClient(c)) {
						System.out.println("Erreur : client " + c + " n'a pas "
								+ "pu être affecté au vehicule " + v);
					}
					vehiculesUtilises.add(v);
				}
			}
			next = clientPlusProche(c, clients);
		}

		this.instance.updatePositions();
		if (!instance.checkPlanning()) {
			System.out.println("Solution FAUSSE !!!");
		} else {
			System.out.println("Solution BONNE !!!");
		}
	}

	/**
	 * Permet de trouver le client le plus proche.
	 * @param c TODO
	 * @param clients TODO
	 * @return integer
	 */
	private int clientPlusProche(Client c, List<Client> clients) {
		if (clients.isEmpty()) {
			return -1;
		}
		double distanceMin = c.getDistanceTo(clients.get(0));
		int next = 0;
		for (int i = 1; i < clients.size(); i++) {
			Client tmp = clients.get(i);
			if (c.getDistanceTo(tmp) < distanceMin) {
				distanceMin = c.getDistanceTo(tmp);
				next = i;
			}
		}
		return next;
	}

	/**
	 * Permet de trouver la meilleure insertion.
	 */
	public void meilleureInsertion() {
		this.instance.clear();
		List<Client> clients = this.instance.getClients();
		List<Vehicule> vehicules = this.instance.getVehicules();
		List<Vehicule> vehiculesUtilises = new ArrayList<>();
		int next = 0;

		while (!clients.isEmpty()) {
			Client c = clients.remove(next);
			boolean affecte = meilleureInsertion(c, vehiculesUtilises);
			if (!affecte) {
				if (vehicules.isEmpty()) {
					System.out.println("Erreur : Plus de vehicule dispo pour "
							+ "affecter le client " + c);
				} else {
					Vehicule v = vehicules.remove(0);
					this.instance.addVehiculeInPlanning(v);
					if (!v.addClient(c)) {
						System.out.println("Erreur : client " + c + " n'a pas "
								+ "pu être affecté au vehicule " + v);
					}
					vehiculesUtilises.add(v);
				}
			}
			next = clientPlusProche(c, clients);
		}

		this.instance.updatePositions();
		if (!instance.checkPlanning()) {
			System.out.println("Solution FAUSSE !!!");
		} else {
			System.out.println("Solution BONNE !!!");
		}
	}

	/**
	 * Permet de calculer la meilleure insertion dans la liste des véhécules
	 * utilisés.
	 * @param c TODO
	 * @param vehiculesUtilises TODO
	 * @return boolean
	 */
	private boolean meilleureInsertion(Client c, List<Vehicule> vehiculesUtilises) {
		boolean affecte = false;
		MeilleureInsertionInfos best = new MeilleureInsertionInfos();
		for (int i = 0; i < vehiculesUtilises.size(); i++) {
			Vehicule v = vehiculesUtilises.get(i);
			MeilleureInsertionInfos infos = v.infosMeilleureInsertion(c);
			if (infos != null && infos.getCout() < best.getCout()) {
				best = infos;
			}
		}
		if (best.getCout() < Double.MAX_VALUE - 1) {
			affecte = best.doInsertion();
		}
		return affecte;
	}

	/**
	 * Algo de Clarke & Wright.
	 */
	public void clarkeAndWright() {
		this.instance.clear();
		List<Client> clients = this.instance.getClients();
		List<Vehicule> vehicules = this.instance.getVehicules();
		List<Vehicule> vehiculesUtilises = new ArrayList<>();
		int next = 0;

		for (Client c : clients) {
			if (vehicules.isEmpty()) {
				System.out.println("Erreur : Plus de vehicule dispo pour "
						+ "affecter le client " + c);
			} else {
				Vehicule v = vehicules.remove(0);
				this.instance.addVehiculeInPlanning(v);
				if (!v.addClient(c)) {
					System.out.println("Erreur : client " + c + " n'a pas "
							+ "pu être affecté au vehicule " + v);
				}
				vehiculesUtilises.add(v);
			}
		}
		boolean ameliore = true;
		while (ameliore) {
			ameliore = fusionTournees(vehiculesUtilises);
		}

		this.instance.updatePositions();
		if (!instance.checkPlanning()) {
			System.out.println("Solution FAUSSE !!!");
		} else {
			System.out.println("Solution BONNE !!!");
		}
	}

	/**
	 * Algo de fusion des tournées.
	 * @param vehiculesUtilises TODO
	 * @return boolean
	 */
	private boolean fusionTournees(List<Vehicule> vehiculesUtilises) {
		int bestR = -1;
		int bestS = -1;
		double bestGain = 1;
		for (int r = 0; r < vehiculesUtilises.size(); r++) {
			for (int s = 0; s < vehiculesUtilises.size(); s++) {
				if (r != s) {
					double gain = vehiculesUtilises.get(r).coutFusion(vehiculesUtilises.get(s));
					if (gain < bestGain) {
						bestGain = gain;
						bestR = r;
						bestS = s;
					}
				}
			}
		}
		if (bestGain >= 0) {
			return false;
		}
		boolean fusion = vehiculesUtilises.get(bestR).fusion(vehiculesUtilises.get(bestS));
		if (fusion) {
			vehiculesUtilises.remove(bestS);
		}
		return true;
	}

}
