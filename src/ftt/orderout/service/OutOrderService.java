package ftt.orderout.service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import ftt.orderout.dao.OutOrderDao;
import ftt.orderout.dao.OutOrderDetailDao;
import ftt.orderout.domain.OutOrder;
import ftt.orderout.domain.OutOrderDetail;
import ftt.orderout.domain.Page;
import ftt.orderout.domain.util.CriteriaOutOrder;

public class OutOrderService {
	private OutOrderDao outOrderDao;
	private OutOrderDetailDao outOrderDetailDao;
	private List<OutOrder> list; 
	
	public void setOutOrderDao(OutOrderDao outOrderDao) {
		this.outOrderDao = outOrderDao;
	}
	
	public void setOutOrderDetailDao(OutOrderDetailDao outOrderDetailDao) {
		this.outOrderDetailDao = outOrderDetailDao;
	}
	
	public Long save(OutOrder outOrder){
		return outOrderDao.save(outOrder);
	}
	
	public List<OutOrder> findAll(){
		list = outOrderDao.findAll();
		return list;
	}
	
	public Page<OutOrder> getPage(int pageNo){
		return outOrderDao.getPage(pageNo);
	}
	
	public Page<OutOrder> getPages(CriteriaOutOrder criteriaOutOrder){
		Page<OutOrder> page = null;
		try {
			page = outOrderDao.getPages(criteriaOutOrder);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return page;
	}
	
	public void delete(Long id){
		outOrderDao.delete(id);
	}
	
	public OutOrder get(Long id){
		OutOrder outOrder = outOrderDao.get(id);
		return outOrder;
	}
	
	public void update(OutOrder outOrder){
		outOrderDao.update(outOrder);
	}
	
	public List<OutOrderDetail> findAllDetail(Long id){
		List<OutOrderDetail> DetailLists = outOrderDetailDao.findAllDetail(id);
		return outOrderDetailDao.findAllDetail(id);
	}
	
	public OutOrder getByOutNo(String outNo){
		return outOrderDao.getByOutNo(outNo);
	}
	
	public Set<OutOrderDetail> findByOutOrderId( Long id){
		OutOrder order = outOrderDao.get(id);
		Set<OutOrderDetail> detailSet = order.getOutOrderDetails();
		return detailSet;
	}
	
	public void delete(OutOrder order){
		outOrderDao.delete(order);
	}
	
}
