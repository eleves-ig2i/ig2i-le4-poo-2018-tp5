package dao;

import java.util.Collection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metier.Client;

/**
 * Représente un DAO de type Client utilisant comme source de données une bdd.
 * @author user
 */
public class JpaClientDao extends JpaDao<Client> implements ClientDao {

	private static JpaClientDao instance;

	/**
	 * Constrcuteur par défault.
	 */
	private JpaClientDao() {
		super(Client.class);
	}

	/**
	 * Retourne une instance de JpaClientDao.
	 * @return JpaClientDao
	 */
	public static JpaClientDao getInstance() {
		if (instance == null) {
			instance = new JpaClientDao();
		}
		return instance;
	}

	@Override
	public boolean deleteAll() {
		return super.deleteAll();
	}

	@Override
	public Collection<Client> findAll() {
		return super.findAll();
	}

	@Override
	public Client find(Integer id) {
		return super.find(id);
	}

	@Override
	public void close() {
		super.close();
	}

	@Override
	public boolean delete(Client obj) {
		return super.delete(obj);
	}

	@Override
	public boolean update(Client obj) {
		return super.update(obj);
	}

	@Override
	public boolean create(Client obj) {
		return super.create(obj);
	}

	@Override
	public Collection<Client> findNotServed() {
		CriteriaBuilder cb = super.em.getCriteriaBuilder();
		CriteriaQuery<Client> cq = cb.createQuery(Client.class);
		Root<Client> tasks = cq.from(Client.class);
		cq.select(tasks).where(cb.or(cb.isNotNull(tasks.get("nvehicule")),
				cb.isNotEmpty(tasks.get("nvehicule"))));
		return super.em.createQuery(cq).getResultList();
	}

}
