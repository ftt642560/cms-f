package ftt.orderout.domain.util;

public class CriteriaOutOrder {
	private String storeName;
	private String outNo;
	private String maxOutStoreDate;
	private String minOutStoreDate;
	private int pageNo;
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getOutNo() {
		return outNo;
	}
	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}
	public String getMaxOutStoreDate() {
		return maxOutStoreDate;
	}
	public void setMaxOutStoreDate(String maxOutStoreDate) {
		this.maxOutStoreDate = maxOutStoreDate;
	}
	public String getMinOutStoreDate() {
		return minOutStoreDate;
	}
	public void setMinOutStoreDate(String minOutStoreDate) {
		this.minOutStoreDate = minOutStoreDate;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	@Override
	public String toString() {
		return "CriteriaOutOrder [storeName=" + storeName + ", outNo=" + outNo
				+ ", maxOutStoreDate=" + maxOutStoreDate + ", minOutStoreDate="
				+ minOutStoreDate + ", pageNo=" + pageNo + "]";
	}
	public CriteriaOutOrder(String storeName, String outNo,
			String maxOutStoreDate, String minOutStoreDate, int pageNo) {
		super();
		this.storeName = storeName;
		this.outNo = outNo;
		this.maxOutStoreDate = maxOutStoreDate;
		this.minOutStoreDate = minOutStoreDate;
		this.pageNo = pageNo;
	}
	public CriteriaOutOrder() {
		super();
	}
}
