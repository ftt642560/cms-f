package hjh.orderin.serviceimpl;

import java.sql.Date;
import java.util.HashSet;

import zlin.store.po.StorePO;

import hjh.orderin.dao.AddOrderInDAO;
import hjh.orderin.domain.InOrder;
import hjh.orderin.domain.InOrderDetail;
import hjh.orderin.service.AddOrderInService;

public class AddOrderInServiceImpl implements AddOrderInService {
    private AddOrderInDAO addOrderInDAO;
    private int countSum;
    
	public int getCountSum() {
		return countSum;
	}

	public void setCountSum(int countSum) {
		this.countSum = countSum;
	}

	public AddOrderInDAO getAddOrderInDAO() {
		return addOrderInDAO;
	}

	public void setAddOrderInDAO(AddOrderInDAO addOrderInDAO) {
		this.addOrderInDAO = addOrderInDAO;
	}

	@Override
	public long addOrderIn(String who,String inDate, String repotory, String source,String note,
			String orderInDetails) {
		HashSet<InOrderDetail> details = null ;
		if(!orderInDetails.trim().equals("")) details = parseDetails(orderInDetails);
		
		String[] dates = inDate.split("-");
		InOrder inOrder = new InOrder();
		inOrder.setInDate(new Date(Integer.valueOf(dates[0])-1900,
				Integer.valueOf(dates[1]) - 1,Integer.valueOf(dates[2])));
		inOrder.setRepertory(repotory);
		inOrder.setSource(source);
		inOrder.setOperator(who);
		inOrder.setNote(note);
		inOrder.setInOrderDetails(details);
		
		return addOrderInDAO.addOrderIn(inOrder,countSum);
	}

	public HashSet<InOrderDetail>  parseDetails(String detailsStr){
		HashSet<InOrderDetail> inOrderDetails = new HashSet<InOrderDetail>();
		String[] details = detailsStr.split(";");
		for(int i = 0;i < details.length;i++){
			String[] detail = details[i].split(",");
			InOrderDetail inOrderDetail = new InOrderDetail();
			inOrderDetail.setArticleNumber(detail[0]);
			inOrderDetail.setType(detail[1]);
			inOrderDetail.setColor(detail[2]);
			inOrderDetail.setSize(detail[3]);
			inOrderDetail.setCount(Integer.valueOf(detail[4]));
			inOrderDetails.add(inOrderDetail);
			countSum += Integer.valueOf(detail[4]);
		}
		return inOrderDetails;
	}
}











