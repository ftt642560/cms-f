package zlin.clothing.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

//Hibernate服务提供类
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
	
    //构造方法	
	public HibernateServiceProvider()
	{
		initHibernate();
	}    

    //初始化sessionFactory实例：
	public static synchronized void initHibernate() throws HibernateException
	{    
    	if(sessionFactory == null)
    	try {
    			configuration.configure();   //启动hibernate，读配置文件hibernate.cfg.xml
    			sessionFactory = configuration.buildSessionFactory();  //创建SessionFactory实例
    		} catch (Exception e) 
    		{
    			System.err.println(" Error Creating SessionFactory ");
    			e.printStackTrace();
    		}    		
    }	
	
    //重新创建SessionFactory实例
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
	
    //获取Session实例
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