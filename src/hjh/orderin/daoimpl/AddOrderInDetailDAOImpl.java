package hjh.orderin.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import zlin.clothing.po.ClothingPO;
import hjh.orderin.dao.AddOrderInDetailDAO;
import hjh.orderin.domain.InOrder;
import hjh.orderin.domain.InOrderDetail;

public class AddOrderInDetailDAOImpl implements AddOrderInDetailDAO {
    SessionFactory sessionFactory ;
    
    
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public List<List<String>> query() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<String> huohao = new ArrayList<String>();
		List<String> pingming = new ArrayList<String>();
		List<String> color = new ArrayList<String>();
		List<String> size = new ArrayList<String>();
		List<List<String>> list = new ArrayList<List<String>>();
		list.add(huohao);
		list.add(pingming);
		list.add(color);
		list.add(size);
		try {
			Query query = session.createQuery("from ClothingPO");
			List<ClothingPO> clothingPOs = query.list();
			for(int i = 0 ;i < clothingPOs.size();i++){
				ClothingPO clothing = clothingPOs.get(i);
				huohao.add(clothing.getClothnum());
				pingming.add(clothing.getType());
				color.add(clothing.getColor());
				size.add(clothing.getSize());
			}
			tx.commit();
			return list;
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
	public boolean add(String inOrderId, String huohao, String pingming,
			String color, String size, String count) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			 InOrderDetail detail = new InOrderDetail();
			 detail.setArticleNumber(huohao);
			 detail.setType(pingming);
			 detail.setColor(color);
			 detail.setSize(size);
			 detail.setCount(Integer.valueOf(count));
			 InOrder inOrder = (InOrder) session.get(InOrder.class,Long.valueOf(inOrderId));
			 Set<InOrderDetail> set = inOrder.getInOrderDetails();
			 set.add(detail);
			 session.saveOrUpdate(inOrder);
			 tx.commit();
			return true;
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








