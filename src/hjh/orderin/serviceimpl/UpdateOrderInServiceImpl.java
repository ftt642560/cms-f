package hjh.orderin.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import hjh.orderin.daoimpl.UpdateOrderInDAOImpl;
import hjh.orderin.service.UpdateOrderInService;

public class UpdateOrderInServiceImpl implements UpdateOrderInService {
	private UpdateOrderInDAOImpl updateOrderInDAOImpl;

	public UpdateOrderInDAOImpl getUpdateOrderInDAOImpl() {
		return updateOrderInDAOImpl;
	}

	public void setUpdateOrderInDAOImpl(
			UpdateOrderInDAOImpl updateOrderInDAOImpl) {
		this.updateOrderInDAOImpl = updateOrderInDAOImpl;
	}

	@Override
	public boolean update(String data,String receiptsNumber,String inDate,
			String inDepot,String source,String note,String who) {
		return updateOrderInDAOImpl.update(data, receiptsNumber, inDate, inDepot, source, note,who);
	}	
}






