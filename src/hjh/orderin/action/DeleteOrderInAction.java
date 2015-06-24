package hjh.orderin.action;

import hjh.orderin.service.DeleteOrderInService;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteOrderInAction extends ActionSupport {
	private long delReceiptsNumber;
	private DeleteOrderInService deleteOrderInService;
	
	
	private String receiptsNumber;
	private String inDepot;
	private String dateStart;
	private String dateEnd;
	private int firstPage;
	
	
	

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public String getReceiptsNumber() {
		return receiptsNumber;
	}

	public void setReceiptsNumber(String receiptsNumber) {
		this.receiptsNumber = receiptsNumber;
	}

	public String getInDepot() {
		return inDepot;
	}

	public void setInDepot(String inDepot) {
		this.inDepot = inDepot;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public DeleteOrderInService getDeleteOrderInService() {
		return deleteOrderInService;
	}

	public void setDeleteOrderInService(
			DeleteOrderInService deleteOrderInService) {
		this.deleteOrderInService = deleteOrderInService;
	}

	

	public long getDelReceiptsNumber() {
		return delReceiptsNumber;
	}

	public void setDelReceiptsNumber(long delReceiptsNumber) {
		this.delReceiptsNumber = delReceiptsNumber;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("receiptsNumber" + receiptsNumber + "inDepot" + inDepot + "dateStart" + dateStart + "dateEnd" + dateEnd + "firstPage"  + firstPage);
		
		if(deleteOrderInService.delOrderInService(delReceiptsNumber))
			return SUCCESS;
		else 
			return ERROR;
	}

}








