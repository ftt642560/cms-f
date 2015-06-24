package ftt.orderout.domain;

import java.util.List;

public class Page<T> {
	//当前是第几页
	private int pageNo;
	
	//当前页的list
	private List<T> list;
	
	//每页显示多少条记录
	private int pageSize = 5;
	
	//共有多少条记录
	private long totalItemNumber;

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	//构造器中需要对pageNo初始化
	public Page(int pageNo) {
		this.pageNo = pageNo;
	}
	
	//需要校验一下当前页
	public int getPageNo() {
		if(pageNo < 1){
			pageNo = 1;
		}
		if(pageNo > getTotalPageNumber()){
			pageNo = getTotalPageNumber();
		}
		return pageNo;
	}
	
	//获取总页数
	public int getTotalPageNumber(){
		int totalPageNumber = (int) (totalItemNumber / pageSize);
		if((totalItemNumber % pageSize) > 0){
			totalPageNumber = totalPageNumber + 1 ;
		}
		return totalPageNumber;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setTotalItemNumber(long totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}
	
	public long getTotalItemNumber() {
		return totalItemNumber;
	}
	
	public boolean isHasNext() {
		if(getPageNo() < getTotalPageNumber() ){
			return true;
		}
		return false;
	}
	
	public boolean isHasPrev() {
		if(getPageNo() > 1){
			return true;
		}
		return false;
	}
	
	public int getPrevPage() {
		if(isHasPrev()){
			return getPageNo() - 1;
		}
		return getPageNo();
	}
	
	public int getNextPage(){
		if(isHasNext()){
			return getPageNo()+1;
		}
		return getPageNo();
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", list=" + list + ", pageSize="
				+ pageSize + ", totalItemNumber=" + totalItemNumber + "]";
	}
	
}
