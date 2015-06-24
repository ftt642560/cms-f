package ftt.orderout.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ftt.orderout.domain.OutOrderDetail;

public class OutOrderDetailDao extends HibernateDaoSupport{
	public void save(OutOrderDetail outOrderDetail){
		getHibernateTemplate().save(outOrderDetail);
	}
	
	public List<OutOrderDetail> findAllDetail(Long id){
		return getHibernateTemplate().find("from OutOrderDetail ood LEFT OUTER JOIN FETCH ood.outOrder WHERE ood.outOrder.id=?" , id);
	}
	
	public List<OutOrderDetail> findAllDetails(){
		return getHibernateTemplate().find("from OutOrderDetail");
	}
	
	public OutOrderDetail get(Long id){
		return (OutOrderDetail) getHibernateTemplate().get(OutOrderDetail.class , id);
	}
	
	public void delete(Long id){
		getHibernateTemplate().delete(get(id));
	}
}
