package zlin.clothing.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mysql.jdbc.log.Log;
import com.mysql.jdbc.log.LogFactory;

import zlin.clothing.po.ClothingPO;
import zlin.store.po.StorePO;



public class ClothingDao extends HibernateDaoSupport
{

	//��ȡȫ����Ϣ
	public int getAllRowCount(String hql)
    {
			int allRows = 0;  
					
        	allRows = this.getHibernateTemplate().find(hql).size();
        
        	return allRows;
    }
	
	
	
	
	
	/**
     * ʹ��hibernate�ṩ�ķ�ҳ���ܣ��õ���ҳ��ʾ�����
     * ��ѯȫ����Ϣ
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
     * ʹ��hibernate�ṩ�ķ�ҳ���ܣ��õ���ҳ��ʾ�����
     * ��������ѯ��Ϣ
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
	
	
	
	
	
	
	
	//�½�����(����һ�����Ŷ���)���ػ���ID
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
	
	
	
	//ɾ�����(����һ�����Ŷ����ID),����ɾ�������
	public void deleteClothing(Long CLOTHINGID)throws Exception
	{
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(ClothingPO.class, CLOTHINGID));
		
	}
	
	
	//���»�����Ϣ
	public void updateClothing(ClothingPO CLOTHINGPO)throws Exception
	{	    
	    this.getHibernateTemplate().update(CLOTHINGPO);	    	 
	}
	
	
	//���»�����Ϣ��ʱ����Ҫ������Ҫ���µĻ�����Ϣ
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
	
}
