package hjh.orderin.serviceimpl;

import java.util.List;

import hjh.orderin.dao.GetOrderInDAO;
import hjh.orderin.domain.InOrder;
import hjh.orderin.service.GetOrderInService;

public class GetOrderInServiceImpl implements GetOrderInService {
	private GetOrderInDAO getOrderInDAO;

	public GetOrderInDAO getGetOrderInDAO() {
		return getOrderInDAO;
	}

	public void setGetOrderInDAO(GetOrderInDAO getOrderInDAO) {
		this.getOrderInDAO = getOrderInDAO;
	}

	@Override
	public InOrder getInOrderById(long id) {
		return getOrderInDAO.getInOrderById(id);
	}

	@Override
	public List<String> getRepertory() {
		return getOrderInDAO.getRepertory();
	}

}
