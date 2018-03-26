package dao;

import java.util.Collection;
import metier.Depot;

/**
 * Représente un DAO de type Depot utilisant comme source de données une bdd.
 * @author user
 */
public class JpaDepotDao extends JpaDao<Depot> implements DepotDao {

	private static JpaDepotDao instance;

	/**
	 * Constrcuteur par défault.
	 */
	private JpaDepotDao() {
		super(Depot.class);
	}

	/**
	 * Retourne une instance de JpaDepotDao.
	 * @return JpaDepotDao
	 */
	public static JpaDepotDao getInstance() {
		if (instance == null) {
			instance = new JpaDepotDao();
		}
		return instance;
	}

	@Override
	public boolean deleteAll() {
		return super.deleteAll();
	}

	@Override
	public Collection<Depot> findAll() {
		return super.findAll();
	}

	@Override
	public Depot find(Integer id) {
		return super.find(id);
	}

	@Override
	public void close() {
		super.close();
	}

	@Override
	public boolean delete(Depot obj) {
		return super.delete(obj);
	}

	@Override
	public boolean update(Depot obj) {
		return super.update(obj);
	}

	@Override
	public boolean create(Depot obj) {
		return super.create(obj);
	}

}
