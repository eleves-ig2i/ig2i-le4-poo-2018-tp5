package dao;

import java.util.Collection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metier.Vehicule;

/**
 * Représente un DAO de type Vehicule utilisant comme source de données une bdd.
 * @author user
 */
public class JpaVehiculeDao extends JpaDao<Vehicule> implements VehiculeDao {

	private static JpaVehiculeDao instance;

	/**
	 * Constrcuteur par défault.
	 */
	private JpaVehiculeDao() {
		super(Vehicule.class);
	}

	/**
	 * Retourne une instance de JpaVehiculeDao.
	 * @return JpaVehiculeDao
	 */
	public static JpaVehiculeDao getInstance() {
		if (instance == null) {
			instance = new JpaVehiculeDao();
		}
		return instance;
	}

	@Override
	public boolean deleteAll() {
		return super.deleteAll();
	}

	@Override
	public Collection<Vehicule> findAll() {
		return super.findAll();
	}

	@Override
	public Vehicule find(Integer id) {
		return super.find(id);
	}

	@Override
	public void close() {
		super.close();
	}

	@Override
	public boolean delete(Vehicule obj) {
		return super.delete(obj);
	}

	@Override
	public boolean update(Vehicule obj) {
		return super.update(obj);
	}

	@Override
	public boolean create(Vehicule obj) {
		return super.create(obj);
	}

	@Override
	public Collection<Vehicule> findAllNotUsed() {
		CriteriaBuilder cb = super.em.getCriteriaBuilder();
		CriteriaQuery<Vehicule> cq = cb.createQuery(Vehicule.class);
		Root<Vehicule> tasks = cq.from(Vehicule.class);
		cq.select(tasks).where(cb.or(cb.isNotNull(tasks.get("nplanning")),
				cb.isNotEmpty(tasks.get("nplanning"))));
		return super.em.createQuery(cq).getResultList();
	}

}
