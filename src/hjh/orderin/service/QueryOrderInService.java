package hjh.orderin.service;

import hjh.orderin.domain.InOrder;

import java.util.List;

public interface QueryOrderInService {
    public List<InOrder> queryOrderIn(int firstPage,String receiptsNum,String repotory,String startDate,String endDate) throws Exception;
    public int getAllPages();
    public int getCurrentPage();
}
