package zlin.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import zlin.clothing.dao.ClothingDao;
import zlin.clothing.po.ClothingPO;

public class TestClothingDao {
	
	private static ClothingDao clothingdao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		       ApplicationContext ctx = new FileSystemXmlApplicationContext(  
		                "WebRoot/WEB-INF/applicationContext.xml");  
		       clothingdao = (ClothingDao )ctx.getBean("clothingdao");   
		//clothingdao=new ClothingDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		clothingdao=null;
	}

	@Test
	public void testGetAllRowCount() {
		//fail("Not yet implemented");
		ClothingPO clothingpo1=new ClothingPO();
		clothingpo1.setClothnum("10");
		clothingpo1.setColor("大红色");
		clothingpo1.setFabric("fabric");
		clothingpo1.setClothingMaterial("material");
		clothingpo1.setSize("180");
		clothingpo1.setType("长款外套");
		clothingpo1.setFactoryPrice("800");
		clothingpo1.setRetailPrice("200");
		
		
		ClothingPO clothingpo2=new ClothingPO();
		clothingpo2.setClothnum("20");
		clothingpo2.setColor("粉红色");
		clothingpo2.setFabric("fabric");
		clothingpo2.setClothingMaterial("material");
		clothingpo2.setSize("150");
		clothingpo2.setType("短款裤子");
		clothingpo2.setFactoryPrice("300");
		clothingpo2.setRetailPrice("100");
		
		try
		{
		clothingdao.newClothing(clothingpo1);
		clothingdao.newClothing(clothingpo2);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		String hql = "from ClothingPO";
		int allRows=clothingdao.getAllRowCount(hql);
		assertNotNull(allRows);
		
	}

	@Test
	public void testFindAllClothing() {
		List resultlist = new ArrayList<ClothingPO>();
		try{
			resultlist=clothingdao.findAllClothing(0, 10);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		assertNotNull(resultlist);
	}

	@Test
	public void testFindClothing() {
		//fail("Not yet implemented");
		List resultlist = new ArrayList<ClothingPO>();
		try
		{
			ClothingPO clothingpo1=new ClothingPO();
			clothingpo1.setClothnum("1");
			clothingpo1.setColor("大红色");
			clothingpo1.setFabric("fabric");
			clothingpo1.setClothingMaterial("material");
			clothingpo1.setSize("180");
			clothingpo1.setType("长款外套");
			clothingpo1.setFactoryPrice("800");
			clothingpo1.setRetailPrice("200");
			
			Long id1=clothingdao.newClothing(clothingpo1);
			resultlist=clothingdao.findClothing("1", "长款外套", "大红色", "180", 0, 10);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		assertNotNull(resultlist);
	}

	@Test
	public void testNewClothing() {
		//fail("Not yet implemented");
		ClothingPO clothingpo2=new ClothingPO();
		clothingpo2.setClothnum("2");
		clothingpo2.setColor("天蓝色");
		clothingpo2.setFabric("fabric");
		clothingpo2.setClothingMaterial("material");
		clothingpo2.setSize("150");
		clothingpo2.setType("180");
		clothingpo2.setFactoryPrice("300");
		clothingpo2.setRetailPrice("100");
		try
		{
		Long id2=clothingdao.newClothing(clothingpo2);
		assertNotNull(id2);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Test
	public void testDeleteClothing() {
		//fail("Not yet implemented");
		ClothingPO clothingpo2=new ClothingPO();
		try
		{
			ClothingPO clothingpo1=new ClothingPO();
			clothingpo1.setClothnum("3");
			clothingpo1.setColor("深紫色");
			clothingpo1.setFabric("fabric");
			clothingpo1.setClothingMaterial("material");
			clothingpo1.setSize("180");
			clothingpo1.setType("短款裙子");
			clothingpo1.setFactoryPrice("800");
			clothingpo1.setRetailPrice("200");
			Long id1=clothingdao.newClothing(clothingpo1);
			
			clothingdao.deleteClothing(id1);
			
			
			clothingpo2=clothingdao.findAClothing(id1);
			assertNull(clothingpo2);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testUpdateClothing() {
		//fail("Not yet implemented");
		ClothingPO clothingpo1=new ClothingPO();
		ClothingPO clothingpo2=new ClothingPO();
		ClothingPO clothingpo3=new ClothingPO();
		
		try
		{
			
			clothingpo1.setClothnum("4");
			clothingpo1.setColor("黑色");
			clothingpo1.setFabric("fabric");
			clothingpo1.setClothingMaterial("material");
			clothingpo1.setSize("180");
			clothingpo1.setType("短款裤子");
			clothingpo1.setFactoryPrice("800");
			clothingpo1.setRetailPrice("200");
			
			Long id1=clothingdao.newClothing(clothingpo1);
			
			clothingpo2=clothingdao.findAClothing(id1);
			clothingpo2.setColor("棕色");
			clothingdao.updateClothing(clothingpo2);
			clothingpo3=clothingdao.findAClothing(id1);
			assertEquals("棕色",clothingpo3.getColor());
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		
	}

	@Test
	public void testFindAClothing() {
		//fail("Not yet implemented");
		ClothingPO clothingpo1=new ClothingPO();
		ClothingPO clothingpo2=new ClothingPO();
		try
		{
			clothingpo1.setClothnum("5");
			clothingpo1.setColor("浅蓝色");
			clothingpo1.setFabric("fabric");
			clothingpo1.setClothingMaterial("material");
			clothingpo1.setSize("180");
			clothingpo1.setType("长款裙子");
			clothingpo1.setFactoryPrice("800");
			clothingpo1.setRetailPrice("200");
			
			Long id1=clothingdao.newClothing(clothingpo1);
			clothingpo2=clothingdao.findAClothing(id1);
			//assertNotNull(clothingpo2);
			assertEquals("5",clothingpo2.getClothnum());
			
		}catch(Exception e)
		{
			
			e.printStackTrace();
		}
		//assertEquals("5",clothingpo2.getClothnum());
	}

}
