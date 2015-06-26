package zlin.clothing.dao;

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



public class ClothingDao extends HibernateDaoSupport
{

	//获取一共有多少条数据
	public int getAllRowCount(String hql)
    {
			int allRows = 0;  
					
        	allRows = this.getHibernateTemplate().find(hql).size();
        
        	return allRows;
    }
	
	
	
	
	
	/**
     * 功能：查找全部的货号信息
     * 参数：offset,pageSize
     * 返回值：List resultlist
     * 
     */
	public List findAllClothing(final int offset,final int pageSize)throws Exception
	{

	    List resultlist = new ArrayList<ClothingPO>();
	    //ClothingPO clothingpo = new ClothingPO();	    
	    resultlist = (ArrayList<ClothingPO>) getHibernateTemplate().execute(new HibernateCallback(){   
			@Override
			public Object doInHibernate(Session session) throws HibernateException,SQLException{   
			Query query = session.createQuery("from ClothingPO order by clothnum");   
			query.setFirstResult(offset);   
			query.setMaxResults(pageSize);   
			List list = query.list();   
			return list;}  
			});   

	    return resultlist;
	}
	
	
	
	/**
     * 功能：按照前台输入的货号，品号，颜色，大小进行查询
     * 参数：CLOTHNUM,TYPE,COLOR,SIZE，offset,pageSize
     * 返回值：List resultlist
     * 
     */

	public List findClothing(final String CLOTHNUM,final String TYPE,final String COLOR,final String SIZE,final int offset,final int pageSize)throws Exception
	{
		final String clothnum=CLOTHNUM;//����
		final String type=TYPE;//Ʒ��
		final String color=COLOR;//ɫ��
		final String size=SIZE;//����
		
	    List resultlist = new ArrayList<ClothingPO>();

	    ClothingPO clothingpo = new ClothingPO();
	    resultlist = (ArrayList<ClothingPO>) getHibernateTemplate().execute(new HibernateCallback(){   
			@Override
			public Object doInHibernate(Session session) throws HibernateException,SQLException{   
			Query query = session.createQuery("from ClothingPO clothing where clothing.clothnum=:clothnum " +
						    		"and clothing.type=:type and color=:color and size=:size order by clothing.clothnum").setFirstResult(offset).setMaxResults(pageSize);   
			query.setString("clothnum", clothnum);
			query.setString("type",type);
			query.setString("color", color);
			query.setString("size", size);  
			List list = query.list();   
			return list;}  
			});   
	    	    
	    return resultlist;
		
	}
	
	
	
	
	
	
	
	/*
	 * 功能：新建货号信息
	 * 参数：ClothingPO CLOTHINGPO
	 * 返回值：Long id
	 * 
	 * */
	public Long newClothing(ClothingPO CLOTHINGPO)throws Exception
	{
		System.out.println("this is newclothing dao");


	    ClothingPO clothingpo = new ClothingPO();
	    clothingpo.setClothnum(CLOTHINGPO.getClothnum());//����
	    clothingpo.setColor(CLOTHINGPO.getColor());//ɫ��
	    clothingpo.setFabric(CLOTHINGPO.getFabric());//����
	    clothingpo.setSize(CLOTHINGPO.getSize());//����
	    clothingpo.setType(CLOTHINGPO.getType());//Ʒ��
	    clothingpo.setRetailPrice(CLOTHINGPO.getRetailPrice());//���ۼ�
	    clothingpo.setFactoryPrice(CLOTHINGPO.getFactoryPrice());//������
	    clothingpo.setClothingMaterial(CLOTHINGPO.getClothingMaterial());//����
	    
	    Long id;
	    id=(Long)this.getHibernateTemplate().save(clothingpo);

	    return id;
	   
	}
	
	
	
	/*
	 * 功能：删除货号信息
	 * 参数:Long CLOTHINGID
	 * 返回值：无
	 * 
	 * */
	public void deleteClothing(Long CLOTHINGID)throws Exception
	{
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(ClothingPO.class, CLOTHINGID));
		
	}
	
	
	/*
	 * 功能：更新货号信息
	 * 参数：ClothingPO CLOTHINGPO
	 * 返回值:无
	 * 
	 * */
	public void updateClothing(ClothingPO CLOTHINGPO)throws Exception
	{	    
	    this.getHibernateTemplate().update(CLOTHINGPO);	    	 
	}
	
	
	/*
	 * 功能：查找一个货号信息
	 * 参数:Long CLOTHINGID
	 * 返回值:ClothingPO clothingpo
	 * 
	 * */
	public ClothingPO findAClothing(Long CLOTHINGID)throws Exception
	{
	    ClothingPO clothingpo = new ClothingPO();   
	    clothingpo =(ClothingPO)this.getHibernateTemplate().get(ClothingPO.class, CLOTHINGID);	    
	    return clothingpo;		
	}
	
	public List<ClothingPO> findAllClothing(){
		List<ClothingPO> clothingList =  getHibernateTemplate().find("FROM ClothingPO");
		return clothingList;
	}
	
	/*
	 * 
	 * 实现模糊查询,允许输入的查询条件为空的情况
	 * 
	 * 
	public List criteriaClothing(final ClothingPO clothingpo,final int offset,final int pageSize)
	{	
		
		 List<ClothingPO> clothinglist = (ArrayList<ClothingPO>) getHibernateTemplate().execute(new HibernateCallback(){   
				@Override
				public Object doInHibernate(Session session) throws HibernateException,SQLException{   
				List<ClothingPO> list=(List<ClothingPO>) session.createCriteria(ClothingPO.class).add(Example.create(clothingpo).ignoreCase().excludeNone().enableLike(MatchMode.ANYWHERE)).setFirstResult(offset).setMaxResults(pageSize).list();  
				
				return list;}  
				});   
		
		 System.out.println("criterial dao clothinglist.size="+clothinglist.size());
		return clothinglist;
	}*/
	
	
	/*
	 * 功能：模糊查询，模糊查询允许查询条件为空的情况
	 * 参数：ClothingPO,offset,pageSize
	 * 返回值:List clothinglist
	 * 
	 * */
	public List criteriaClothing(ClothingPO clothingpo,final int offset,final int pageSize)
	{	
		
		// List<ClothingPO> clothinglist = (ArrayList<ClothingPO>) getHibernateTemplate().execute(new HibernateCallback(){   
		final String clothnum=clothingpo.getClothnum();//����
		final String type=clothingpo.getType();//Ʒ��
		final String color=clothingpo.getColor();//ɫ��
		final String size=clothingpo.getSize();//����

				HibernateTemplate ht = getHibernateTemplate();
				List<ClothingPO> clothinglist = ht.executeFind(new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria =  session.createCriteria(ClothingPO.class);
						if(!clothnum.trim().equals("") && clothnum != null){
							criteria.add(Restrictions.eq("clothnum", clothnum));
						}
						if(!type.trim().equals("") && type != null){
							criteria.add(Restrictions.eq("type", type));
						}
						if(!color.trim().equals("") && color != null){
							criteria.add(Restrictions.eq("color", color));
						}
						if(!size.trim().equals("") && size != null){
							criteria.add(Restrictions.eq("size", size));
						}
						criteria.setMaxResults(pageSize);
						criteria.setFirstResult(offset);
						return criteria.list();
					}
				});
	
		
		return clothinglist;
	}
	
	
	/*
	public int criterialAllRows(final ClothingPO clothingpo)
	{
		
		List<ClothingPO> clothinglist = (ArrayList<ClothingPO>) getHibernateTemplate().execute(new HibernateCallback(){   
			@Override
			public Object doInHibernate(Session session) throws HibernateException,SQLException{   
			List<ClothingPO> list=(List<ClothingPO>) session.createCriteria(ClothingPO.class).add(Example.create(clothingpo).ignoreCase().excludeNone().enableLike(MatchMode.ANYWHERE)).list();  
			System.out.println("criterial dao in allrows");
			return list;}  
			});   
		
		System.out.println("clothinglist.size="+clothinglist.size());
		
		return clothinglist.size();
		
	}
	*/
	
	/*
	 * 功能：通过模糊查询时，查询出多少条数据
	 * 参数：ClothingPO clothingpo
	 * 返回值：int clothinglist.size()
	 * */
	public int criterialAllRows(ClothingPO clothingpo)
	{
		
		final String clothnum=clothingpo.getClothnum();//货号
		final String type=clothingpo.getType();//品名
		final String color=clothingpo.getColor();//颜色
		final String size=clothingpo.getSize();//大小
		
		HibernateTemplate ht = getHibernateTemplate();
		List<ClothingPO> clothinglist = ht.executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria =  session.createCriteria(ClothingPO.class);
				if(!clothnum.trim().equals("") && clothnum != null){
					criteria.add(Restrictions.eq("clothnum", clothnum));
				}
				if(!type.trim().equals("") && type != null){
					criteria.add(Restrictions.eq("type", type));
				}
				if(!color.trim().equals("") && color != null){
					criteria.add(Restrictions.eq("color", color));
				}
				if(!size.trim().equals("") && size != null){
					criteria.add(Restrictions.eq("size", size));
				}
				
				return criteria.list();
			}
		});


		return clothinglist.size();
		
	}
	
}
