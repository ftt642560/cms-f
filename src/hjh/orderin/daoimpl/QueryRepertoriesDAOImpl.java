package hjh.orderin.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hjh.orderin.dao.QueryRepertoriesDAO;

public class QueryRepertoriesDAOImpl implements QueryRepertoriesDAO {
    private SessionFactory sessionFactory;
    
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<String> queryRepertories() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("select storename from StorePO");
			List<String> repertories = query.list();
			tx.commit();
			return repertories;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

}
