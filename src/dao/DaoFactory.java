package dao;

/**
 * Représente une frabrique DAO.
 * @author user
 */
public abstract class DaoFactory {

	/**
	 * Peremt de retourner un DaoFactory.
	 * @param type TODO.
	 * @return DaoFactory
	 */
	public static DaoFactory getDaoFactory(PersistenceType type) {
		if (type.equals(PersistenceType.JPA)) {
			return new DaoFactoryJpa();
		}
		return null;
	}

	/**
	 * Retourne un DepotDao.
	 * @return DepotDao
	 */
	public abstract DepotDao getDepotDao();

	/**
	 * Retourne un ClientDao.
	 * @return ClientDao
	 */
	public abstract ClientDao getClientDao();

	/**
	 * Retourne un RouteDao.
	 * @return RouteDao
	 */
	public abstract RouteDao getRouteDao();

	/**
	 * Retourne un VehiculeDao.
	 * @return VehiculeDao
	 */
	public abstract VehiculeDao getVehiculeDao();

	/**
	 * Retourne un PlanningDao.
	 * @return PlanningDao
	 */
	public abstract PlanningDao getPlanningDao();

	/**
	 * Retourne une InstanceDao.
	 * @return InstanceDao
	 */
	public abstract InstanceDao getInstanceDao();

}
