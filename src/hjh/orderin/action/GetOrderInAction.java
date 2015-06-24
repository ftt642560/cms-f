package hjh.orderin.action;

import java.util.List;

import hjh.orderin.domain.InOrder;
import hjh.orderin.service.GetOrderInService;

import com.opensymphony.xwork2.ActionSupport;

public class GetOrderInAction extends ActionSupport {
    private long orderInId;
    private InOrder inOrder;
    private List<String> storenames;
    
    private GetOrderInService getOrderInService;
    
	public List<String> getStorenames() {
		return storenames;
	}

	public void setStorenames(List<String> storenames) {
		this.storenames = storenames;
	}

	public GetOrderInService getGetOrderInService() {
		return getOrderInService;
	}

	public void setGetOrderInService(GetOrderInService getOrderInService) {
		this.getOrderInService = getOrderInService;
	}

	public InOrder getInOrder() {
		return inOrder;
	}

	public void setInOrder(InOrder inOrder) {
		this.inOrder = inOrder;
	}

	public long getOrderInId() {
		return orderInId;
	}

	public void setOrderInId(long orderInId) {
		this.orderInId = orderInId;
	}

	@Override
	public String execute() throws Exception {
		inOrder = getOrderInService.getInOrderById(orderInId);
		storenames = getOrderInService.getRepertory();
		for(int i = 0;i < storenames.size();i++){
			if(storenames.get(i).equals(inOrder.getRepertory())){
				storenames.remove(i);
			}
		}
		if(inOrder != null) return SUCCESS;
		return ERROR;
	}
    
}
