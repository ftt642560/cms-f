package ftt.orderout.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import ftt.orderout.domain.OutOrder;
import ftt.orderout.domain.OutOrderDetail;
import ftt.orderout.service.OutOrderDetailService;
import ftt.orderout.service.OutOrderService;

public class OutOrderDetailAction extends ActionSupport{
	private OutOrderDetailService outOrderDetailService;
	private OutOrderDetail outOrderDetail;
	private OutOrderService outOrderService;
	private Long id;
	private String outNo;
	private OutOrder outOrder;
	private String mess;
	private String result;
	private long storeId;
	
	public OutOrder getOutOrder() {
		return outOrder;
	}

	public void setOutOrder(OutOrder outOrder) {
		this.outOrder = outOrder;
	}

	public OutOrderDetailService getOutOrderDetailService() {
		return outOrderDetailService;
	}

	public void setOutOrderDetailService(OutOrderDetailService outOrderDetailService) {
		this.outOrderDetailService = outOrderDetailService;
	}
	
	public OutOrderDetail getOutOrderDetail() {
		return outOrderDetail;
	}

	public void setOutOrderDetail(OutOrderDetail outOrderDetail) {
		this.outOrderDetail = outOrderDetail;
	}

	public OutOrderService getOutOrderService() {
		return outOrderService;
	}

	public void setOutOrderService(OutOrderService outOrderService) {
		this.outOrderService = outOrderService;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String findAllCloth(){
		HttpServletRequest re = ServletActionContext.getRequest();
		String model = re.getParameter("model");
		ActionContext actionContext = ActionContext.getContext();
		if(model.equals("add")){
			actionContext.put("storeNumber", outOrderDetailService.getStoreNumber(outOrderDetailService.getStoreId(id)));
			actionContext.put("outOrderList", outOrderService.findAll());
			actionContext.put("clothList", outOrderDetailService.findAllCloth());
			
			re.setAttribute("model", "add");
		}
		if(model.equals("update")){
			//查询库存量（根据出库单详细的id）
			re.setAttribute("outOrderDetailId", id);
			Long outOrderId = outOrderDetailService.getOrderIdByDetailId(id);
			//根绝出库单id查找库存剩余量
			actionContext.put("storeNumber", outOrderDetailService.getStoreNumber(outOrderDetailService.getStoreId(outOrderId)));
			//查询明细所属的出库单编号（根据出库单详细的id）
			OutOrder outOrder = outOrderService.get(outOrderId);
			re.setAttribute("outNo", outOrder.getOutNo());
			//根据出库单明细id查询出库单明细
			OutOrderDetail orderDetail = outOrderDetailService.getOneDetail(id);
			re.setAttribute("outOrderDetail", orderDetail);
			re.setAttribute("model", "update");
		}
		
		return "findAllCloth";
	}
	
	/**
	 * 添加出库单时首先要检查一下出库单，要检查一下库存量是多少，根据库存检查一下库存量是否满足出库的数量（根据out_id）
	 * @return
	 */
	public String save(){
		Map<String, Object> map = ActionContext.getContext().getParameters();
		String[] str = (String[]) map.get("model");
		String model = "";
		for( int i = 0 ; i < 1 ; i++){
			model = str[i];
		}
		if(model.equals("add")){
			String[] goodsNos = (String[]) map.get("outOrderDetail.goodsNo");
			String[] brandNames = (String[]) map.get("outOrderDetail.brandName");
			String[] colorNames = (String[]) map.get("outOrderDetail.colorName");
			String[] sizes = (String[]) map.get("outOrderDetail.size");
			String[] ids = (String[]) map.get("id");
			String[] numbers = (String[]) map.get("outOrderDetail.number");

			String goodsNo = "";
			String brandName = "";
			String colorName = "";
			String size = "";
			Long id = null;
			int number = 0;
			
			for( int i = 0 ; i < 1 ; i++){
				goodsNo = goodsNos[i];
				brandName = brandNames[i];
				colorName = colorNames[i];
				size = sizes[i];
				id = Long.parseLong(ids[i]);
				number =(int)Integer.parseInt(numbers[i]) ;
			}
			
			outOrderDetail.setBrandName(brandName);
			outOrderDetail.setGoodsNo(goodsNo);
			outOrderDetail.setColorName(colorName);
			outOrderDetail.setSize(size);
			outOrderDetail.setNumber(number);
			//查询仓库的id
			storeId = outOrderDetailService.getStoreId(id);
			//查询仓库的库存量
			int storeNumber = outOrderDetailService.getStoreNumber(storeId);
			if(number > storeNumber){//出库量大于库存量，不允许出库，给予页面一个提示。
				mess = ("出库量大于库存量，则不允许出库!");
			}else{//允许出库
				int surplusNumber = storeNumber - number;//库存剩余量
				//1:将剩余库存量保存到仓库
				outOrderDetailService.updateStoreNumber(surplusNumber, storeId);
				//2:保存出库单
				outOrderDetailService.save(outOrderDetail, id);
				mess = ("允许出库，出库量小于库存量！");
			}
		}
		
		if(model.equals("update")){//更新出库单明细
			String[] numbers = (String[]) map.get("number");
			String[] detailIds = (String[]) map.get("detailId");
			int number = 0;
			long detailId = 0 ;
			for( int i = 0 ; i < 1 ; i++){
				number = Integer.parseInt(numbers[i]) ;
				detailId = Long.parseLong(detailIds[i]);
			}
			
			//1:根据出库单明细的id，查询出库单的id
			long outOrderId = outOrderDetailService.getOrderIdByDetailId(detailId);
			//1.1:根据出库单明细的id查询number
			int outNumber = outOrderDetailService.getOutNumber(detailId);
			//2:根据出库单id,查询仓库id
			long storeId = outOrderDetailService.getStoreId(outOrderId);
			//3:根据仓库的id查询，查处库存量
			int storeNumber = outOrderDetailService.getStoreNumber(storeId);
			int diffNumber = number - outNumber;
			int diff1 = storeNumber - number;
			if(diffNumber < 0){
				//1:说明出库的数量减少，使仓库的库存增加
				storeNumber = storeNumber - diffNumber;
				//2:修改库存量
				outOrderDetailService.updateStoreNumber(storeNumber, storeId);
				//3:修改出库的数量
				outOrderDetailService.updateOutNumber(number, detailId);
				mess = "允许修改出库量，出库的数量变少，修改成功";
			}else if(diffNumber == 0){
				mess = "出库的数量不变，则不进行任何操作";
			}else{ 
				int diff2 = storeNumber - diffNumber;
				if(diff2 >= 0){
					//diff2 >= 0表示允许出库
					storeNumber = storeNumber - diffNumber;
					outOrderDetailService.updateOutNumber(number, detailId);
					outOrderDetailService.updateStoreNumber(storeNumber, storeId);
					mess = "允许修改出库量，出库的数量变多，修改成功";
				}else {
					//diff2 < 0表示不允许修改出库量
					mess = ("出库量大于库存量，则不允许修改出库量，修改失败!");
				}
			}
		} 
		Map<String, String> mapMess = new HashMap<String, String>();
		mapMess.put("mess", mess);
		JSONArray json = JSONArray.fromObject(mapMess);//将map对象转换成json类型数据
		result = json.toString();
		return "save";
	}
	
	private InputStream inputStream;
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public String deleteDetail(){
		outOrderDetailService.delete(id);
		try {
			inputStream =  new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "deleteDetail";
	}
	
	public String findAllDetails(){
		ActionContext.getContext().put("findAllDetail", outOrderDetailService.findAllDetails());
		return "findAllDetails";
	}
}
