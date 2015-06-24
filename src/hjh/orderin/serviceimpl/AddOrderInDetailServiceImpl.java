package hjh.orderin.serviceimpl;

import java.util.List;

import hjh.orderin.dao.AddOrderInDetailDAO;
import hjh.orderin.service.AddOrderInDetailService;

public class AddOrderInDetailServiceImpl implements AddOrderInDetailService {
    private AddOrderInDetailDAO addOrderInDetailDAO;
    
	public AddOrderInDetailDAO getAddOrderInDetailDAO() {
		return addOrderInDetailDAO;
	}

	public void setAddOrderInDetailDAO(AddOrderInDetailDAO addOrderInDetailDAO) {
		this.addOrderInDetailDAO = addOrderInDetailDAO;
	}

	@Override
	public List<List<String>> query() {
		return addOrderInDetailDAO.query();
	}

	@Override
	public boolean save(String inOrderId, String huohao, String pingming,
			String color, String size, String count) {
		return addOrderInDetailDAO.add(inOrderId, huohao, pingming, color, size, count);
	}

}
