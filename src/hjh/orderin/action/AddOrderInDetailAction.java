package hjh.orderin.action;

import hjh.orderin.service.AddOrderInDetailService;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class AddOrderInDetailAction extends ActionSupport {
	private String status = GET_PAGE;
	private static final String GET_PAGE = "getPage";
	private static final String ADD = "add";
	private String huohao;
	private String color;
	private String size;
	private String count;
	private String pingming;
	private String inOrderId;
	
	
	private List<List<String>> infos;
	private AddOrderInDetailService addOrderInDetailService;
	
    

	public String getPingming() {
		return pingming;
	}

	public void setPingming(String pingming) {
		this.pingming = pingming;
	}

	public String getInOrderId() {
		return inOrderId;
	}

	public void setInOrderId(String inOrderId) {
		this.inOrderId = inOrderId;
	}

	public AddOrderInDetailService getAddOrderInDetailService() {
		return addOrderInDetailService;
	}

	public void setAddOrderInDetailService(
			AddOrderInDetailService addOrderInDetailService) {
		this.addOrderInDetailService = addOrderInDetailService;
	}

	public List<List<String>> getInfos() {
		return infos;
	}

	public void setInfos(List<List<String>> infos) {
		this.infos = infos;
	}

	public String getHuohao() {
		return huohao;
	}

	public void setHuohao(String huohao) {
		this.huohao = huohao;
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

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String execute() throws Exception {
		if(status.equals(GET_PAGE)){
			infos = addOrderInDetailService.query();
			if(infos == null) return ERROR;
			return SUCCESS;
		}else{
			huohao = new String(huohao.getBytes("iso-8859-1"),"utf-8");
			pingming = new String(pingming.getBytes("iso-8859-1"),"utf-8");
			color = new String(color.getBytes("iso-8859-1"),"utf-8");
			size = new String(size.getBytes("iso-8859-1"),"utf-8");
			count = new String(count.getBytes("iso-8859-1"),"utf-8");

			boolean result = addOrderInDetailService.save(inOrderId, huohao, pingming, color, size, count);
			if(result){
				System.out.println("login");
				return LOGIN;
			}
			return ERROR;
		}
	}

	
	
}
