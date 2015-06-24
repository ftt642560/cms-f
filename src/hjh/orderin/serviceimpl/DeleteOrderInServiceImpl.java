package hjh.orderin.serviceimpl;

import hjh.orderin.dao.DeleteOrderInDAO;
import hjh.orderin.service.DeleteOrderInService;

public class DeleteOrderInServiceImpl implements DeleteOrderInService {
    private DeleteOrderInDAO deleteOrderInDAO;
    
	public DeleteOrderInDAO getDeleteOrderInDAO() {
		return deleteOrderInDAO;
	}

	public void setDeleteOrderInDAO(DeleteOrderInDAO deleteOrderInDAO) {
		this.deleteOrderInDAO = deleteOrderInDAO;
	}

	@Override
	public boolean delOrderInService(long receiptsNumber) {
		return deleteOrderInDAO.delOrderInById(receiptsNumber);
	}

}
