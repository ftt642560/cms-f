package hjh.orderin.action;

import hjh.orderin.service.UpdateOrderInService;

import com.opensymphony.xwork2.ActionSupport;

public class UpdateOrderInAction extends ActionSupport {
    private String id_count;
    private String receiptsNumber;
    private String inDate;
    private String inDepot;
    private String source;
    private String note;
    private String who;

    private UpdateOrderInService updateOrderInService;
    
    
	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getReceiptsNumber() {
		return receiptsNumber;
	}

	public void setReceiptsNumber(String receiptsNumber) {
		this.receiptsNumber = receiptsNumber;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
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

	public String getId_count() {
		return id_count;
	}

	public void setId_count(String id_count) {
		this.id_count = id_count;
	}

	
	public String getInDepot() {
		return inDepot;
	}

	public void setInDepot(String inDepot) {
		this.inDepot = inDepot;
	}

	public UpdateOrderInService getUpdateOrderInService() {
		return updateOrderInService;
	}

	public void setUpdateOrderInService(UpdateOrderInService updateOrderInService) {
		this.updateOrderInService = updateOrderInService;
	}

	@Override
	public String execute() throws Exception {
		inDepot = new String(inDepot.getBytes("iso-8859-1"),"utf-8");
		source = new String(source.getBytes("iso-8859-1"),"utf-8");
		note = new String(note.getBytes("iso-8859-1"),"utf-8");
		who = new String(who.getBytes("iso-8859-1"),"utf-8");
		id_count = new String(id_count.getBytes("iso-8859-1"),"utf-8");
		updateOrderInService.update(id_count,receiptsNumber,inDate,inDepot,source,note,who);
		return super.execute();
	}
    
    
}
