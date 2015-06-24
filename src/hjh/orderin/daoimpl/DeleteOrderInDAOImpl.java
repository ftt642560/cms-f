package hjh.orderin.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hjh.orderin.dao.DeleteOrderInDAO;
import hjh.orderin.domain.InOrder;
import hjh.orderin.domain.InOrderDetail;

public class DeleteOrderInDAOImpl implements DeleteOrderInDAO {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean delOrderInById(long receiptsNumber) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			InOrder inOrder = (InOrder) session.get(InOrder.class, receiptsNumber);
//			System.out.println(inOrder);
			if(inOrder != null) {
				session.delete(inOrder);
				tx.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		
	}

}
