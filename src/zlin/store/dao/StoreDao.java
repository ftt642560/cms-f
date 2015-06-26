package zlin.store.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import zlin.clothing.po.ClothingPO;
import zlin.store.po.StorePO;

public class StoreDao extends HibernateDaoSupport
{
	//获得数据库一共有多少条信息
	public int getAllRowCount(String hql)
    {
			int allRows = 0;           
        	allRows = this.getHibernateTemplate().find(hql).size();
        
        	return allRows;
    }
	
	/*
	 * 功能：查找全部的货号信息，并进行分页
	 * 参数:final int offset,final int pageSize
	 * 返回值:List resultlist
	 * 
	 * */
	public List findAllStore(final int offset,final int pageSize)throws Exception
	{

	    List resultlist = new ArrayList<StorePO>();
	      
	    resultlist = (ArrayList<StorePO>) getHibernateTemplate().execute(new HibernateCallback(){   
			@Override
			public Object doInHibernate(Session session) throws HibernateException,SQLException{   
			Query query = session.createQuery("from StorePO as storepo order by storepo.storenum asc");   
			query.setFirstResult(offset);   
			query.setMaxResults(pageSize);   
			List list = query.list();   
			return list;}  
			});   

	    return resultlist;
	}
	
	
	/*
	 * 功能：按照条件查找仓库
	 * 参数：final String STORENUM,final String STORENAME,final int offset,final int pageSize
	 * 返回值:List resultlist
	 * 前提条件：输入的4个参数不能为空
	 * 
	 * */
	public List findStore(final String STORENUM,final String STORENAME,final int offset,final int pageSize)throws Exception
	{
		List resultlist=new ArrayList<StorePO>();
		
		final String storenum=STORENUM;//�ֿ���
		final String storename=STORENAME;//�ֿ����

		 StorePO storepo=new StorePO();
		 resultlist = (ArrayList<StorePO>) getHibernateTemplate().execute(new HibernateCallback(){   
				@Override
				public Object doInHibernate(Session session) throws HibernateException,SQLException{   
				Query query = session.createQuery("from StorePO storepo where storepo.storenum=:storenum " +
							    		"and storepo.storename=:storename order by storepo.storenum asc").setFirstResult(offset).setMaxResults(pageSize);   
				query.setString("storenum", storenum);
				query.setString("storename",storename);
				List list = query.list();   
				return list;}  
				});   
		    	    
		    return resultlist;
		
		
	}
	
	/*
	 * 功能：新建仓库
	 * 参数：StorePO STOREPO
	 * 返回值：Long id
	 * 
	 * */
	public Long newStore(StorePO STOREPO)throws Exception
	{
		StorePO storepo=new StorePO();
		storepo.setStorenum(STOREPO.getStorenum());
		storepo.setStorename(STOREPO.getStorename());
		storepo.setLinkman(STOREPO.getLinkman());
		storepo.setTele(STOREPO.getTele());
		storepo.setStoragevolume(STOREPO.getStoragevolume());
		
		Long id;
		id=(Long)this.getHibernateTemplate().save(storepo);

		return id;
		
	}
	
	
	/*
	 * 功能：删除仓库
	 * 参数：Long STOREID
	 * 返回值：无
	 * 
	 * */
	public void deleteStore(Long STOREID)throws Exception
	{		
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(StorePO.class, STOREID));
		
	}
	
	/*
	 * 功能：按照ID查找一个仓库信息
	 * 参数：Long STOREID
	 * 返回值：StorePO storepo
	 * 
	 * */
	public StorePO findAStore(Long STOREID)throws Exception
	{
		StorePO storepo=new StorePO();
		storepo=(StorePO)this.getHibernateTemplate().get(StorePO.class,STOREID);
		return storepo;
	}
	
	/*
	 * 功能：更新仓库信息
	 * 参数：StorePO storepo
	 * 返回值：无
	 * 
	 * */
	public void updateStore(StorePO storepo)throws Exception
	{
		 this.getHibernateTemplate().update(storepo);
	}
	
	
	/*
	 * 功能：查找全部仓库信息
	 * 参数：无
	 * 返回值：List 
	 * 
	 * */
	public List<StorePO> findAllStore(){
		return getHibernateTemplate().find("FROM StorePO");
	}
	
	
	
	/*
	 * 功能：模糊查询时，获得查询结果个数
	 * 参数：StorePO storepo
	 * 返回值：int storelist.size()
	 * 
	 * */
	public int criterialAllRows(StorePO storepo)
	{
		
		final String storenum=storepo.getStorenum();//�ֿ���
		final String storename=storepo.getStorename();//�ֿ����
		
		HibernateTemplate ht = getHibernateTemplate();
		List<StorePO> storelist = ht.executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria =  session.createCriteria(StorePO.class);
				if(!storenum.trim().equals("") && storenum != null){
					criteria.add(Restrictions.eq("storenum", storenum));
				}
				if(!storename.trim().equals("") && storename != null){
					criteria.add(Restrictions.eq("storename", storename));
				}
				
				
				return criteria.list();
			}
		});


		return storelist.size();
		
	}
	
	/*
	 * 功能：模糊查询，允许查询条件为空的情况，并进行分页
	 * 参数：StorePO storepo,final int offset,final int pageSize
	 * 返回值：int storelist
	 * 
	 * */
	public List criteriaStore(StorePO storepo,final int offset,final int pageSize)
	{	
		
		final String storenum=storepo.getStorenum();//�ֿ���
		final String storename=storepo.getStorename();//�ֿ����
		
		HibernateTemplate ht = getHibernateTemplate();
		List<StorePO> storelist = ht.executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria =  session.createCriteria(StorePO.class);
				if(!storenum.trim().equals("") && storenum != null){
					criteria.add(Restrictions.eq("storenum", storenum));
				}
				if(!storename.trim().equals("") && storename != null){
					criteria.add(Restrictions.eq("storename", storename));
				}
				
				criteria.setMaxResults(pageSize);
				criteria.setFirstResult(offset);
				
				return criteria.list();
			}
		});


		return storelist;
		
	}
	
	
}
