package hjh.orderin.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class InOrder {
	private String repertory;
	private long receiptsNumber;
	private Date inDate;
	private String operator;
	private String source;
	private String note;


	private Set<InOrderDetail> inOrderDetails = new HashSet<InOrderDetail>();
	
	public InOrder() {
	}

	public InOrder(String repertory, long receiptsNumber, Date inDate,
			String operator, String source,String note) {
		super();
		this.repertory = repertory;
		this.receiptsNumber = receiptsNumber;
		this.inDate = inDate;
		this.operator = operator;
		this.source = source;
		this.note = note;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public Set<InOrderDetail> getInOrderDetails() {
		return inOrderDetails;
	}

	public void setInOrderDetails(Set<InOrderDetail> inOrderDetails) {
		this.inOrderDetails = inOrderDetails;
	}

	public String getRepertory() {
		return repertory;
	}

	public void setRepertory(String repertory) {
		this.repertory = repertory;
	}

	public long getReceiptsNumber() {
		return receiptsNumber;
	}

	public void setReceiptsNumber(long receiptsNumber) {
		this.receiptsNumber = receiptsNumber;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}


	public Date getInDate() {
		return inDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "InOrder [repertory=" + repertory + ", receiptsNumber="
				+ receiptsNumber + ", inDate=" + inDate + ", operator="
				+ operator + ", source=" + source + ", inOrderDetails="
				+ inOrderDetails + "]";
	}


}
