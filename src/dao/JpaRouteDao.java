package dao;

import java.util.Collection;
import metier.Route;

/**
 * Représente un DAO de type Route utilisant comme source de données une bdd.
 * @author user
 */
public class JpaRouteDao extends JpaDao<Route> implements RouteDao {

	private static JpaRouteDao instance;

	/**
	 * Constrcuteur par défault.
	 */
	private JpaRouteDao() {
		super(Route.class);
	}

	/**
	 * Retourne une instance de JpaRouteDao.
	 * @return JpaRouteDao
	 */
	public static JpaRouteDao getInstance() {
		if (instance == null) {
			instance = new JpaRouteDao();
		}
		return instance;
	}

	@Override
	public boolean deleteAll() {
		return super.deleteAll();
	}

	@Override
	public Collection<Route> findAll() {
		return super.findAll();
	}

	@Override
	public Route find(Integer id) {
		return super.find(id);
	}

	@Override
	public void close() {
		super.close();
	}

	@Override
	public boolean delete(Route obj) {
		return super.delete(obj);
	}

	@Override
	public boolean update(Route obj) {
		return super.update(obj);
	}

	@Override
	public boolean create(Route obj) {
		return super.create(obj);
	}

}
