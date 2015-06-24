package hjh.orderin.action;

import hjh.orderin.service.AddOrderInService;

import com.opensymphony.xwork2.ActionSupport;

public class AddOrderInAction extends ActionSupport {
	private String inDate;
	private String repotory;
	private String source;
	private String note;
	private String who = "hjh";
	private String orderInDetails="";
	
	private long justInsertId = -1;
	
	private AddOrderInService addOrderInService;
	
	public long getJustInsertId() {
		return justInsertId;
	}

	public void setJustInsertId(long justInsertId) {
		this.justInsertId = justInsertId;
	}

	public AddOrderInService getAddOrderInService() {
		return addOrderInService;
	}

	public void setAddOrderInService(AddOrderInService addOrderInService) {
		this.addOrderInService = addOrderInService;
	}

	public String getOrderInDetails() {
		return orderInDetails;
	}

	public void setOrderInDetails(String orderInDetails) {
		this.orderInDetails = orderInDetails;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getRepotory() {
		return repotory;
	}

	public void setRepotory(String repotory) {
		this.repotory = repotory;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	
	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	@Override
	public String execute() throws Exception {
		repotory = new String(repotory.getBytes("iso-8859-1"),"utf-8");
		source = new String(source.getBytes("iso-8859-1"),"utf-8");
		note = new String(note.getBytes("iso-8859-1"),"utf-8");
		who = new String(who.getBytes("iso-8859-1"),"utf-8");
	    orderInDetails = new String(orderInDetails.getBytes("iso-8859-1"),"utf-8");
 
	    System.out.println(who);
 		
		justInsertId = addOrderInService.addOrderIn(who,inDate, repotory, source,note, orderInDetails);
	    if(justInsertId != -1) return SUCCESS;
	    return ERROR;
	}
}









