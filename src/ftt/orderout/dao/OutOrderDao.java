package ftt.orderout.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ftt.orderout.domain.OutOrder;
import ftt.orderout.domain.OutOrderDetail;
import ftt.orderout.domain.Page;
import ftt.orderout.domain.util.CriteriaOutOrder;

public class OutOrderDao extends HibernateDaoSupport {
	public OutOrder get(Long id){
		OutOrder order = (OutOrder) getHibernateTemplate().get(OutOrder.class, id);
		return order;
	}
	
	public Long save(OutOrder outOrder){
		return (Long) getHibernateTemplate().save(outOrder);
	}
	
	public List<OutOrder> findAll(){
		return getHibernateTemplate().find("FROM OutOrder");
	}
	
	public void delete(OutOrder order){
		getHibernateTemplate().delete(order);
	}
	
	/**
	 * 一共有多少条记录
	 * @return
	 */
	public Long findAllOutOrder(){
		String hql = "from OutOrder";
		Long allNumber = (long) getHibernateTemplate().find(hql).size(); 
		return allNumber;
	}
	
	public Long findAllCriteriaOutOrder(CriteriaOutOrder criteriaOutOrder){
		final String storeName = criteriaOutOrder.getStoreName();
		final String outNo = criteriaOutOrder.getOutNo();
		final String maosd = criteriaOutOrder.getMaxOutStoreDate();
		final String miosd = criteriaOutOrder.getMinOutStoreDate();
		long allNumbers = 0;
		
		HibernateTemplate ht = getHibernateTemplate();
		List<OutOrder> list = ht.executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria =  session.createCriteria(OutOrder.class);
				if(!storeName.trim().equals("") && storeName != null){
					System.out.println("storeNamw========"+storeName);
					criteria.createCriteria("storePO").add(Restrictions.eq("storename", storeName));
				}
				if(!outNo.trim().equals("") && outNo != null){
					criteria.add(Restrictions.eq("outNo", outNo));
				}
				if(!miosd.trim().equals("") && miosd != null){
					criteria.add(Restrictions.ge("outDate", miosd));
				}
				if(!maosd.trim().equals("") && maosd != null){
					criteria.add(Restrictions.le("outDate", maosd));
				}
				return criteria.list();
			}
			
		});
		allNumbers = list.size();
		return allNumbers;
	}
	
	
	public Page<OutOrder> getPage(int pageNo){
		//利用pageNo获得当前的页数
		Page<OutOrder> page = new Page<OutOrder>(pageNo);
		//设置一共有多少条记录，先获得一共有多少个订单
		page.setTotalItemNumber(findAllOutOrder());
		//校验pageNo的合法性
		page.setList(getPageList((pageNo-1)*5 , 5));
		return page;
	}
	
	
	public Page<OutOrder> getPages(CriteriaOutOrder cr) throws ParseException{
		//利用criteriaOutOrder获取当前页的page
		Page<OutOrder> page = new Page<OutOrder>(cr.getPageNo());
		//获得满足条件的有多少行
		page.setTotalItemNumber(findAllCriteriaOutOrder(cr));
		//校验page的合法性
		cr.setPageNo(page.getPageNo());
		page.setList(getPageLists(cr.getStoreName(), cr.getOutNo(), cr.getMinOutStoreDate(), cr.getMaxOutStoreDate(), (cr.getPageNo()-1)*5 , 5));
		return page;
	}
	
	/**
	 * number : 是取得数目     start :开始  。 
	 */
	public List<OutOrder> getPageList(final int start , final int pageSiza){
		HibernateTemplate ht = getHibernateTemplate();
		List<OutOrder> list = ht.executeFind( new HibernateCallback(){

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from OutOrder");
				query.setMaxResults(pageSiza);
				query.setFirstResult(start);
				return  query.list();
			}
		});
		return list;
	}
	
	public List<OutOrder> getPageLists(final String STORENAME , final String OUTNO , final String MI , final String MA , final int start , final int pageSize ) throws ParseException{
		final String storeName = STORENAME;
		final String outNo =  OUTNO;
		final String ma = MA;
		final String mi = MI ;

		HibernateTemplate ht = getHibernateTemplate();
		List<OutOrder> list = ht.executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria =  session.createCriteria(OutOrder.class);
				if(!storeName.trim().equals("") && storeName != null){
					criteria.createCriteria("storePO").add(Restrictions.eq("storename", storeName));
				}
				if(!outNo.trim().equals("") && outNo != null){
					criteria.add(Restrictions.eq("outNo", outNo));
				}
				if(!mi.trim().equals("") && mi != null){
					criteria.add(Restrictions.ge("outDate", mi));
				}
				if(!ma.trim().equals("") && ma != null){
					criteria.add(Restrictions.le("outDate", ma));
				}
				criteria.setMaxResults(pageSize);
				criteria.setFirstResult(start);
				return criteria.list();
			}
			
//			@Override
//			public Object doInHibernate(Session session) throws HibernateException,
//					SQLException {
//				Query query = session.createQuery("FROM OutOrder as oo where oo.storePO.storename=:storeName and  oo.outNo =:outNo and oo.outDate <= :maxOutStoreDate and oo.outDate >=:minOutStoreDate");
//				query.setString("outNo", outNo);
//				query.setString("storeName", storeName);
//				query.setString("minOutStoreDate", mi);
//				query.setString("maxOutStoreDate", ma);
//				query.setMaxResults(pageSize);//最大行
//				query.setFirstResult(start);//第一行
//				return  query.list();
//			}
		});
		return list;
	}
	
	public OutOrder getOne(){
		String hql1 = "select max(o.id) from OutOrder as o ";
		List<Long> list = getHibernateTemplate().find(hql1);
		long id = list.get(0);
		String hql = "from OutOrder as o where o.id = id";
		OutOrder outOrder = (OutOrder) getHibernateTemplate().get(OutOrder.class, id);
		return outOrder;
	}
	
	//根据id来删除
	public void delete(Long id){
		getHibernateTemplate().delete(get(id));
	}
	
	public void update(OutOrder outOrder){
		getHibernateTemplate().update(outOrder);
	}
	
	public Set<OutOrderDetail> findAllDetail(Long id){
		OutOrder outOrder = (OutOrder) getHibernateTemplate().get(OutOrder.class, id);
		Set<OutOrderDetail> outOrderDetails =  outOrder.getOutOrderDetails();
		return outOrderDetails;
	}
	
	public OutOrder getByOutNo(String outNo){
		OutOrder order =(OutOrder) getHibernateTemplate().find("FROM OutOrder as oo where oo.outNo=?" , outNo).get(0);
		return order;
	}
}
