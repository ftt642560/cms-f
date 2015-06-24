package hjh.orderin.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hjh.orderin.dao.GetOrderInDAO;
import hjh.orderin.domain.InOrder;

public class GetOrderInDAOImpl implements GetOrderInDAO {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public InOrder getInOrderById(long id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			InOrder inOrder = (InOrder) session.get(InOrder.class, id);
			tx.commit();
			return inOrder;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<String> getRepertory() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("select storename from StorePO");
			List<String> storenames = query.list();
			tx.commit();
			return storenames;
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
