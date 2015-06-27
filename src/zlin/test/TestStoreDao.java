package zlin.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


import zlin.store.dao.StoreDao;
import zlin.store.po.StorePO;

public class TestStoreDao {
	
	private static StoreDao storedao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 ApplicationContext ctx = new FileSystemXmlApplicationContext(  
	                "WebRoot/WEB-INF/applicationContext.xml");  
	       storedao = (StoreDao)ctx.getBean("storedao");   
		//storedao=new StoreDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		storedao=null;
	}

	@Test
	public void testGetAllRowCount() {
		//fail("Not yet implemented");
		StorePO storepo1=new StorePO();
		storepo1.setStorenum("1009");
		storepo1.setStorename("高邮仓库");
		storepo1.setLinkman("小方");
		storepo1.setTele("1759307901");
		storepo1.setStoragevolume("800");
		
		StorePO storepo2=new StorePO();
		storepo2.setStorenum("1010");
		storepo2.setStorename("高邮仓库");
		storepo2.setLinkman("小李");
		storepo2.setTele("1369082901");
		storepo2.setStoragevolume("300");
		
		try
		{
		storedao.newStore(storepo1);
		storedao.newStore(storepo2);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		String hql = "from StorePO";
		int allRows=storedao.getAllRowCount(hql);
		assertNotNull(allRows);
	}

	@Test
	public void testFindAllStore() {
		//fail("Not yet implemented");
		List resultlist = new ArrayList<StorePO>();
		StorePO storepo1=new StorePO();
		storepo1.setStorenum("2009");
		storepo1.setStorename("高邮仓库");
		storepo1.setLinkman("小刘");
		storepo1.setTele("1759307901");
		storepo1.setStoragevolume("800");
		
		StorePO storepo2=new StorePO();
		storepo2.setStorenum("2010");
		storepo2.setStorename("高邮仓库");
		storepo2.setLinkman("小张");
		storepo2.setTele("1369082901");
		storepo2.setStoragevolume("300");
		
		try
		{
			storedao.newStore(storepo1);
			storedao.newStore(storepo2);
			resultlist=storedao.findAllStore(0, 10);
			assertNotNull(resultlist);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
	}

	@Test
	public void testFindStore() {
		//fail("Not yet implemented");
		List resultlist=new ArrayList<StorePO>();
		StorePO storepo1=new StorePO();
		storepo1.setStorenum("2011");
		storepo1.setStorename("啊啊仓库");
		storepo1.setLinkman("小赵");
		storepo1.setTele("1759307901");
		storepo1.setStoragevolume("800");
		
	
		try{
			Long id=storedao.newStore(storepo1);
			resultlist=storedao.findStore("2010","高邮仓库",0, 10);
			assertNotNull(resultlist);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testNewStore() {
		//fail("Not yet implemented");
		StorePO storepo1=new StorePO();
		storepo1.setStorenum("2012");
		storepo1.setStorename("高邮仓库");
		storepo1.setLinkman("小钱");
		storepo1.setTele("1759307901");
		storepo1.setStoragevolume("800");
		
		
		try
		{
			Long id=storedao.newStore(storepo1);
			assertNotNull(id);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Test
	public void testDeleteStore() {
		//fail("Not yet implemented");
		StorePO storepo1=new StorePO();
		storepo1.setStorenum("2013");
		storepo1.setStorename("高邮仓库");
		storepo1.setLinkman("孙大");
		storepo1.setTele("1759307901");
		storepo1.setStoragevolume("800");
		
		StorePO storepo2=new StorePO();
		try
		{
			Long id1=storedao.newStore(storepo1);
			storedao.deleteStore(id1);
			storepo2=storedao.findAStore(id1);
			assertNull(storepo2);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Test
	public void testFindAStore() {
		//fail("Not yet implemented");
		StorePO storepo1=new StorePO();
		storepo1.setStorenum("2014");
		storepo1.setStorename("高邮仓库");
		storepo1.setLinkman("李明");
		storepo1.setTele("1759307901");
		storepo1.setStoragevolume("800");
		
		StorePO storepo2=new StorePO();
		try
		{
			Long id1=storedao.newStore(storepo1);
			storepo2=storedao.findAStore(id1);
			assertNotNull(storepo2);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateStore() {
		//fail("Not yet implemented");
		StorePO storepo1=new StorePO();
		storepo1.setStorenum("2014");
		storepo1.setStorename("高邮仓库");
		storepo1.setLinkman("二旺");
		storepo1.setTele("1759307901");
		storepo1.setStoragevolume("800");
		
		StorePO storepo2=new StorePO();
		StorePO storepo3=new StorePO();
		try
		{
			Long id1=storedao.newStore(storepo1);
			storepo2=storedao.findAStore(id1);
			storepo2.setLinkman("小天");
			storedao.updateStore(storepo2);
			storepo3=storedao.findAStore(id1);
			assertEquals("小天",storepo3.getLinkman());
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
