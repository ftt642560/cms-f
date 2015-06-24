package ftt.orderout.dao;

import java.math.BigInteger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ftt.orderout.domain.OutOrderDetail;

public class OutOrderDetailDaoTwo {
	private SessionFactory sessionFactory;
	private Session session;
	private Query query;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void save(OutOrderDetail details , Long outOrderId){
		String sql = "INSERT INTO details VALUES (?, ?, ?, ? , ? , ? , ?)";
		session = sessionFactory.openSession();
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, details.getId());
		query.setParameter(1, details.getGoodsNo());
		query.setParameter(2 , details.getBrandName());
		query.setParameter(3 , details.getColorName());
		query.setParameter(4 , details.getSize());
		query.setParameter(5 , details.getNumber());
		query.setParameter(6 , outOrderId);
		query.executeUpdate();
	}
	
	/**
	 *  添加出库单时首先要检查一下出库单,要检查库存量（根据出库单明细的outOrder_id，找到出库单的，然后根据出库单id找到仓库，即找到了库存量）
	 * @param outOrderId
	 * @return
	 */
	public long getStoreId(Long outOrderId){//查询仓库的id
		String sql = "SELECT STORE_ID from out_order where id = ?";
		session = sessionFactory.openSession();
		query = session.createSQLQuery(sql);
		query.setParameter(0, outOrderId);
		BigInteger num =  (BigInteger) query.list().get(0);
		long storeId = num.intValue();
		return storeId;
	}
	
	public int getStoreNumber(Long storeId){//查询仓库的库存量
		String sql = "select storagevolume from store where id = ?";
		session = sessionFactory.openSession();
		query = session.createSQLQuery(sql);
		String sStoreNumber = (String) query.setLong(0, storeId).list().get(0);
		int storeNumber = Integer.parseInt(sStoreNumber);
		return storeNumber;
	}
	
	public void updateStoreNumber(int storeNumber , long storeId){
		String sql = "UPDATE store SET storagevolume = ? WHERE id = ? ";
		session = sessionFactory.openSession();
		query = session.createSQLQuery(sql);
		query.setInteger(0, storeNumber);
		query.setLong(1, storeId);
		query.executeUpdate();
	}
	
	//更新
	public void updateOutNumber(int outNumber , long detailId){
		String sql = "UPDATE details SET NUMBER = ? WHERE id = ?";
		session = sessionFactory.openSession();
		query = session.createSQLQuery(sql);
		query.setInteger(0, outNumber);
		query.setLong(1, detailId);
		query.executeUpdate();
	}
	
	public Long getOrderIdByDetailId(Long id){
		String sql = "SELECT OUTORDER_ID FROM details WHERE ID = ?";
		session = sessionFactory.openSession();
		query = session.createSQLQuery(sql);
		query.setParameter(0, id);
		BigInteger b = (BigInteger) query.list().get(0);
		long outOrderId = b.intValue();
		return outOrderId;
	}
	
	public int getOutNumber(Long detailId){
		String sql = "SELECT NUMBER FROM details WHERE ID = ?";
		session = sessionFactory.openSession();
		query = session.createSQLQuery(sql);
		query.setParameter(0, detailId);
		int outNumber = (Integer) query.list().get(0);
		return outNumber;
	}
}
