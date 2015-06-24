package hjh.orderin.dao;

import hjh.orderin.domain.InOrder;

import java.util.List;

public interface QueryOrderInDAO {
	public List<InOrder> query(int firstPage,String receiptsNum,String repotory,String startDate,String endDate) ;
    public int getAllPages();
    public int getCurrentPage();
}
