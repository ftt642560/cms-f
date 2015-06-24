package hjh.orderin.domain;

public class InOrderDetail {
	private long inorderdetail_id;
	private String articleNumber;
	private String type;
	private String color;
	private String size;
	private int count;
	

	public InOrderDetail() {
	}
	

	public InOrderDetail(long inorderdetail_id, 
			String articleNumber, String type, String color, String size,
			int count) {
		super();
		this.inorderdetail_id = inorderdetail_id;
		this.articleNumber = articleNumber;
		this.type = type;
		this.color = color;
		this.size = size;
		this.count = count;
	}

	@Override
	public String toString() {
		return "InOrderDetail [inorderdetail_id=" + inorderdetail_id
				 + ", articleNumber="
				+ articleNumber + ", type=" + type + ", color=" + color
				+ ", size=" + size + ", count=" + count + "]";
	}

	
	public long getInorderdetail_id() {
		return inorderdetail_id;
	}


	public void setInorderdetail_id(long inorderdetail_id) {
		this.inorderdetail_id = inorderdetail_id;
	}


//	public long getReceiptsNumberFK() {
//		return receiptsNumberFK;
//	}
//
//
//	public void setReceiptsNumberFK(long receiptsNumberFK) {
//		this.receiptsNumberFK = receiptsNumberFK;
//	}


	public String getArticleNumber() {
		return articleNumber;
	}


	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
