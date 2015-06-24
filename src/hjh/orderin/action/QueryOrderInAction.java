package hjh.orderin.action;

import java.util.List;

import hjh.orderin.domain.InOrder;
import hjh.orderin.service.QueryOrderInService;
import hjh.orderin.service.QueryRepertoriesService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
//http://blog.sina.com.cn/s/blog_601b97ee01018t7x.html
public class QueryOrderInAction extends ActionSupport {
	private String receiptsNumber2 = "";
	private String inDepot = "0";
	private String dateStart = "";
	private String dateEnd="";
	private QueryOrderInService queryOrderInService;
	private QueryRepertoriesService queryRepertoriesService;
	private int firstPage = 1;

	private List<String> repertories;
	
	private int pages;
	private int currentpage;
	private int nextpage;
	private int prepage;
	private List<InOrder> inOrders;

	
	public List<String> getRepertories() {
		return repertories;
	}

	public void setRepertories(List<String> repertories) {
		this.repertories = repertories;
	}

	public int getPrepage() {
		return prepage;
	}

	public void setPrepage(int prepage) {
		this.prepage = prepage;
	}

	public int getNextpage() {
		return nextpage;
	}

	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public List<InOrder> getInOrders() {
		return inOrders;
	}

	public void setInOrders(List<InOrder> inOrders) {
		this.inOrders = inOrders;
	}

	public String getInDepot() {
		return inDepot;
	}

	public void setInDepot(String inDepot) {
		this.inDepot = inDepot;
	}

	public String getReceiptsNumber2() {
		return receiptsNumber2;
	}

	public void setReceiptsNumber2(String receiptsNumber2) {
		this.receiptsNumber2 = receiptsNumber2;
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

	public QueryOrderInService getQueryOrderInService() {
		return queryOrderInService;
	}

	public void setQueryOrderInService(QueryOrderInService queryOrderInService) {
		this.queryOrderInService = queryOrderInService;
	}
	
	public QueryRepertoriesService getQueryRepertoriesService() {
		return queryRepertoriesService;
	}

	public void setQueryRepertoriesService(
			QueryRepertoriesService queryRepertoriesService) {
		this.queryRepertoriesService = queryRepertoriesService;
	}

	@Override
	public String execute() throws Exception {
		inDepot = new String(inDepot.getBytes("iso-8859-1"),"utf-8");

		inOrders = queryOrderInService.queryOrderIn(firstPage,receiptsNumber2,inDepot,dateStart,dateEnd);
		pages = queryOrderInService.getAllPages();
		currentpage = queryOrderInService.getCurrentPage();
		repertories = queryRepertoriesService.queryRepertories();

		nextpage = currentpage + 1;
		prepage = currentpage - 1;
		if(inOrders != null) {
			return SUCCESS;
		}
			
			
		else return ERROR;
	}
}







