package ftt.orderout.service;

import java.util.List;

import zlin.clothing.dao.ClothingDao;
import zlin.clothing.po.ClothingPO;
import ftt.orderout.dao.OutOrderDetailDao;
import ftt.orderout.dao.OutOrderDetailDaoTwo;
import ftt.orderout.domain.OutOrderDetail;

public class OutOrderDetailService {
	private ClothingDao clothingDao;
	private OutOrderDetailDao outOrderDetailDao; 
	private OutOrderDetailDaoTwo outOrderDetailDaoTwo;
	
	public ClothingDao getClothingDao() {
		return clothingDao;
	}

	public void setClothingDao(ClothingDao clothingDao) {
		this.clothingDao = clothingDao;
	}
	
	public OutOrderDetailDao getOutOrderDetailDao() {
		return outOrderDetailDao;
	}

	public void setOutOrderDetailDao(OutOrderDetailDao outOrderDetailDao) {
		this.outOrderDetailDao = outOrderDetailDao;
	}

	public OutOrderDetailDaoTwo getOutOrderDetailDaoTwo() {
		return outOrderDetailDaoTwo;
	}

	public void setOutOrderDetailDaoTwo(OutOrderDetailDaoTwo outOrderDetailDaoTwo) {
		this.outOrderDetailDaoTwo = outOrderDetailDaoTwo;
	}

	//查询出货号，货色，尺码
	public List<ClothingPO> findAllCloth(){
		List<ClothingPO> clothList = clothingDao.findAllClothing();
		return clothList;
	}
	
	public void save(OutOrderDetail outOrderDetail){
		outOrderDetailDao.save(outOrderDetail); 
	}
	public List<OutOrderDetail> findAllDetails(){
		return outOrderDetailDao.findAllDetails();
	}
 	
	public void delete(Long id){
		outOrderDetailDao.delete(id);
	}
	
	public List<OutOrderDetail> getOneOrderDetails(Long id) {
		return outOrderDetailDao.findAllDetail(id);
	}
	
	public void save(OutOrderDetail details , Long outOrderId){
		outOrderDetailDaoTwo.save(details, outOrderId);
	}
	
	public long getStoreId(Long outOrderId){
		long storeId = outOrderDetailDaoTwo.getStoreId(outOrderId);
		return storeId;
	}
	
	public int getStoreNumber(Long storeId){
		int storeNumber = outOrderDetailDaoTwo.getStoreNumber(storeId);
		return storeNumber ;
	}
	
	public void updateStoreNumber(int storeNumber , long storeId){
		outOrderDetailDaoTwo.updateStoreNumber(storeNumber, storeId);
	}
	
	public OutOrderDetail getOneDetail(Long id){
		OutOrderDetail outOrderDetail = outOrderDetailDao.get(id);
		return outOrderDetail;
	}
	
	public Long getOrderIdByDetailId(Long id){
		return outOrderDetailDaoTwo.getOrderIdByDetailId(id);
	}
	
	public void updateOutNumber(int outNumber , long detailId){
		outOrderDetailDaoTwo.updateOutNumber(outNumber, detailId);
	}
	
	public int getOutNumber(Long detailId){
		return outOrderDetailDaoTwo.getOutNumber(detailId);
	}
}
