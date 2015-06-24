package hjh.orderin.daoimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import zlin.store.po.StorePO;

import hjh.orderin.dao.UpdateOrderInDAO;
import hjh.orderin.domain.InOrder;
import hjh.orderin.domain.InOrderDetail;

public class UpdateOrderInDAOImpl implements UpdateOrderInDAO {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<String[]> parse(String data) {
		if(data == null || data.trim().equals("")) return null;
		List<String[]> list = new ArrayList<String[]>();
		String[] datas = data.split(";");
		for(int i = 0 ;i < datas.length;i++) list.add(datas[i].split(","));
		return list;
	}

	@Override
	public boolean update(String data,String receiptsNumber,String inDate,
			String inDepot,String source,String note,String who) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			//更新入库单
		    InOrder inOrder = (InOrder) session.get(InOrder.class, Long.valueOf(receiptsNumber));
		    String oldRepertory = inOrder.getRepertory();
		    String[] dates = inDate.split("-");
		    inOrder.setInDate(new Date(Integer.valueOf(dates[0]) + 100,
					Integer.valueOf(dates[1]) - 1,Integer.valueOf(dates[2])));
		    inOrder.setNote(note);
		    inOrder.setReceiptsNumber(Long.valueOf(receiptsNumber));
		    inOrder.setSource(source);
		    inOrder.setRepertory(inDepot);
		    inOrder.setOperator(who);
		    session.saveOrUpdate(inOrder);
		    
		    //更新入库单明细
		    List<String[]> list = parse(data);
		    Query query = session.createQuery("from InOrderDetail where receiptsNumberFK = :receiptsNumberFK");
		    query.setLong("receiptsNumberFK",inOrder.getReceiptsNumber());
		    List<InOrderDetail> details = query.list();
		    int oldCountSum = 0;
		    for(int i = 0;i < details.size();i++){
		    	InOrderDetail detail = details.get(i);
		    	oldCountSum += detail.getCount();
		    	for(int j = 0;list != null & j < list.size();j++){
		    		if(Long.valueOf(list.get(j)[0]) == detail.getInorderdetail_id()){
		    			detail.setCount(Integer.valueOf(list.get(j)[1]));
		    			session.saveOrUpdate(detail);
		    		}
		    	}
		    }
		    
		    //更新仓库数量；
		    int newCountSum = 0;
		    for(int i = 0;null != list && i < list.size();i++){
		    	newCountSum += Integer.valueOf(list.get(i)[1]);
		    }
		    Query q = session.createQuery("from StorePO where storename= :storename");
		    q.setString("storename",oldRepertory);
		    List<StorePO> stores = q.list();
		    stores.get(0).setStoragevolume("" + (Integer.valueOf(stores.get(0).getStoragevolume()) - oldCountSum));
		    session.saveOrUpdate(stores.get(0));
		    
		    Query q2 = session.createQuery("from StorePO where storename= :storename");
		    q2.setString("storename",inDepot);
		    List<StorePO> stores2 = q2.list();
		    stores2.get(0).setStoragevolume("" + (Integer.valueOf(stores2.get(0).getStoragevolume()) + newCountSum));
		    session.saveOrUpdate(stores2.get(0));
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
