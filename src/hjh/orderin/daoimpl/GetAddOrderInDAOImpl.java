package hjh.orderin.daoimpl;

import hjh.orderin.dao.GetAddOrderInDAO;
import hjh.orderin.domain.InOrder;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GetAddOrderInDAOImpl implements GetAddOrderInDAO {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<List<String>> getClothingInfos() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<List<String>> infos = new ArrayList<List<String>>();
		List<String> huohao = new ArrayList<String>();
		List<String> color = new ArrayList<String>();
		List<String> size = new ArrayList<String>();
		List<String> pingming = new ArrayList<String>();
		try {
			huohao = session.createQuery("select clothnum from ClothingPO").list();
			pingming = session.createQuery("select type from ClothingPO").list();
			color = session.createQuery("select color from ClothingPO").list();
			size = session.createQuery("select size from ClothingPO").list();
			tx.commit();
			infos.add(huohao);
			infos.add(pingming);
			infos.add(color);
			infos.add(size);
			return infos;
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
