package zlin.clothing.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

//Hibernate�����ṩ��
public class HibernateServiceProvider {
	private static Configuration configuration = new Configuration();
	private static SessionFactory sessionFactory = null;
	
	//get()
	public static Configuration getConfiguration() {
		return configuration;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
    //���췽��	
	public HibernateServiceProvider()
	{
		initHibernate();
	}    

    //��ʼ��sessionFactoryʵ����
	public static synchronized void initHibernate() throws HibernateException
	{    
    	if(sessionFactory == null)
    	try {
    			configuration.configure();   //����hibernate���������ļ�hibernate.cfg.xml
    			sessionFactory = configuration.buildSessionFactory();  //����SessionFactoryʵ��
    		} catch (Exception e) 
    		{
    			System.err.println(" Error Creating SessionFactory ");
    			e.printStackTrace();
    		}    		
    }	
	
    //���´���SessionFactoryʵ��
	public static void rebuildSessionFactory() throws HibernateException
	{
		try {
			configuration.configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println(" Error Creating SessionFactory ");
			e.printStackTrace();
		}
	}
	
    //��ȡSessionʵ��
	public Session getSession() throws HibernateException {
		if (sessionFactory == null) 
		{
			rebuildSessionFactory();
		}
		Session session = sessionFactory.openSession();
        return session;
    }
	
	public void closeSessionFactory() throws HibernateException
	{
		sessionFactory.close();
	}
}