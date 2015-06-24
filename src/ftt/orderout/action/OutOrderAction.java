package ftt.orderout.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import zlin.store.service.StoreService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import ftt.orderout.domain.OutOrder;
import ftt.orderout.domain.Page;
import ftt.orderout.domain.util.CriteriaOutOrder;
import ftt.orderout.service.CreateNoService;
import ftt.orderout.service.OutOrderDetailService;
import ftt.orderout.service.OutOrderService;

public class OutOrderAction extends ActionSupport{
	private OutOrder outOrder;
	private OutOrderService outOrderService;
	private StoreService storeService ;
	private List<OutOrder> list;
	private CreateNoService createNoService;
	private OutOrderDetailService outOrderDetailService;
	private String result;
	private CriteriaOutOrder criteriaOutOrder;
	private int pageNo;
	private Long id;
	private String outNo;
	
	public OutOrder getOutOrder() {
		return outOrder;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setOutOrder(OutOrder outOrder) {
		this.outOrder = outOrder;
	}

	public OutOrderService getOutOrderService() {
		return outOrderService;
	}

	public void setOutOrderService(OutOrderService outOrderService) {
		this.outOrderService = outOrderService;
	}
	
	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public OutOrderDetailService getOutOrderDetailService() {
		return outOrderDetailService;
	}

	public void setOutOrderDetailService(OutOrderDetailService outOrderDetailService) {
		this.outOrderDetailService = outOrderDetailService;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public CreateNoService getCreateNoService() {
		return createNoService;
	}

	public void setCreateNoService(CreateNoService createNoService) {
		this.createNoService = createNoService;
	}

	public CriteriaOutOrder getCriteriaOutOrder() {
		return criteriaOutOrder;
	}

	public void setCriteriaOutOrder(CriteriaOutOrder criteriaOutOrder) {
		this.criteriaOutOrder = criteriaOutOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOutNo() {
		return outNo;
	}

	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}

	public String save() throws UnsupportedEncodingException{
		String flag = "";
		Map<String, Object> map = ActionContext.getContext().getParameters();
		String[] str = (String[]) map.get("method");
		String s = "";
		for(int i = 0 ; i < 1 ; i ++){
			 s = str[i];
		}
		String outNo1 = outOrder.getReceivePerson();
		if(s.equals("add")){
			String outNo = createNoService.getOrderNo();
			outOrder.setOutNo(outNo);
			Long id = outOrderService.save(outOrder);
			String outOrderNo = outOrderService.get(id).getOutNo();
			Map<String, String> mapOutNo = new HashMap<String, String>();
			mapOutNo.put("outNo", outOrderNo);
			JSONArray json = JSONArray.fromObject(mapOutNo);
			result = json.toString();
			flag = "save";
		}
		if(s.equals("update")){
			String outNo = outOrder.getOutNo();
			OutOrder outOrder1 = outOrderService.getByOutNo(outNo);
			if(outOrder.getOutDate().equals(outOrder1.getOutDate()) && outOrder.getAddress().equals(outOrder1.getAddress()) &&outOrder.getStorePO().getStorename().equals(outOrder1.getStorePO().getStorename()) && outOrder.getReceivePhone().equals(outOrder1.getReceivePhone())
					&& outOrder.getReceivePerson().equals(outOrder1.getReceivePerson()) && outOrder.getRemark().equals(outOrder1.getRemark())){
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			}else {
				outOrderService.update(outOrder);
				inputStream =  new ByteArrayInputStream("1".getBytes("UTF-8"));
			}
			flag = "update";
		}
		return flag;
	}
	
	public String findAll(){
		int pageNum = 0 ;
		if(pageNo == 0){
			pageNum = 1;
		}else{
			pageNum = pageNo;
		}
		ActionContext.getContext().put("stores", storeService.findAllStore());
		Page<OutOrder> page = outOrderService.getPage(pageNum);
		ActionContext.getContext().put("list", page);
		ActionContext.getContext().put("model", "findAll");
		return "findAll";
	}
	
	public String findAllStore(){
		HttpServletRequest re = ServletActionContext.getRequest();
		String model = re.getParameter("model");
		if(model.equals("add")){
			re.setAttribute("model", "add");
			ActionContext.getContext().put("stores", storeService.findAllStore());
		}
		if(model.equals("update")){
			outOrder = outOrderService.get(id);
			//根据出库单的id查询所有的出库单明细
			ActionContext.getContext().put("outOrder", outOrder);
			ActionContext.getContext().put("stores", storeService.findAllStore());
			re.setAttribute("model", "update");
			ActionContext.getContext().put("orderDetails", outOrderService.findByOutOrderId(id));
		}
		return "findAllStore";
	}
	
	public String findPart(){
		int pageNum = 0;
		if(pageNo == 0){
			pageNum = 1;
		}else{
			pageNum = pageNo;
		}
		criteriaOutOrder.setPageNo(pageNum);
		ActionContext.getContext().put("stores", storeService.findAllStore());
		Page<OutOrder> page = outOrderService.getPages(criteriaOutOrder);
		ActionContext.getContext().put("list", page);
		ActionContext.getContext().put("model", "findPart");
		return "findPart";
	}
	
	private InputStream inputStream;
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public String delete(){
		OutOrder order = outOrderService.get(id);
		outOrderService.delete(order);
		long allRows = outOrderService.findAll().size();
		JSONArray json = JSONArray.fromObject(allRows);
		result = json.toString();
		return "delete";
	}
	
	public String getOrderByOutNo(){
		OutOrder outOrder1 = outOrderService.getByOutNo(outOrder.getOutNo());
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{  "outOrderDetails" });
		JSONArray json = JSONArray.fromObject(outOrder1, config);
		result = json.toString();
		return "success";
	}
}
