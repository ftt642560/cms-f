package ftt.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import zlin.store.dao.StoreDao;
import zlin.store.po.StorePO;

import ftt.orderout.dao.OutOrderDao;
import ftt.orderout.domain.OutOrder;
import ftt.orderout.domain.OutOrderDetail;

public class TestOutOrderDao {
	private static OutOrderDao outOrderDao; 
	private static StoreDao storeDao;

	//鍒濆鍖栨柟娉�
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		outOrderDao = new OutOrderDao();
		storeDao  = new StoreDao();
	}

	//鏀跺熬鏂规硶	 
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		outOrderDao = null;
		storeDao = null;
	}

	//娴嬭瘯鑾峰彇涓�潯鏁版嵁
	@Test
	public void testGet() {
		
		fail("Not yet implemented");
	}

	//淇濆瓨涓�潯鏁版嵁
	@Test
	public void testSave() throws Exception {
		OutOrder outOrder1 = new OutOrder();
		OutOrder outOrder2 = new OutOrder();
		OutOrderDetail outOrderDetail1 = new OutOrderDetail();
		StorePO storePO = new StorePO();
		StorePO storePO2 = new StorePO();
		
		
		//娴嬭瘯淇濆瓨store
		storePO.setLinkman("寮犱笁");
		storePO.setStorename("涓�彿浠撳簱");
		storePO.setStoragevolume("200");
		storePO.setStorenum("1001");
		storePO.setTele("110");
		
		storeDao.newStore(storePO);//淇濆瓨store
		
		storePO2.setId(storePO.getId());
		
		
		Set<OutOrderDetail> outOrderDetails = new HashSet<OutOrderDetail>();
		outOrderDetail1.setBrandName("鐪熺淮鏂");
		outOrderDetail1.setColorName("鐧借壊");
		outOrderDetail1.setGoodsNo("HR0909");
		outOrderDetail1.setNumber(12);
		outOrderDetail1.setSize("170");
		outOrderDetails.add(outOrderDetail1);
		
		List<OutOrder> list = new ArrayList<OutOrder>();
		outOrder1.setAddress("娣卞湷");
		outOrder1.setOutDate("2015-6-24");
		outOrder1.setOutNo("whxx627924");
		outOrder1.setReceivePerson("鑳栧瓙");
		outOrder1.setReceivePhone((long) 11111111);
		outOrder1.setRemark("璐甸噸鐗╁搧");
		
		outOrder1.setStorePO(storePO2);
		outOrder1.setOutOrderDetails(outOrderDetails);
		list = outOrderDao.findAll();
		outOrder2 = list.get(0);
		assertEquals("whxx627924", outOrder2.getOutNo());
		outOrderDao.delete((long)1);
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteOutOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllOutOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllCriteriaOutOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPages() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPageList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPageLists() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOne() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByOutNo() {
		fail("Not yet implemented");
	}

}
