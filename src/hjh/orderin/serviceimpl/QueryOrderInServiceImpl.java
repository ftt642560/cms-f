package hjh.orderin.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import hjh.orderin.dao.QueryOrderInDAO;
import hjh.orderin.daoimpl.QueryOrderInDAOImpl;
import hjh.orderin.domain.InOrder;
import hjh.orderin.service.QueryOrderInService;

public class QueryOrderInServiceImpl implements QueryOrderInService {
    private QueryOrderInDAO query ;
    
	public QueryOrderInDAO getQuery() {
		return query;
	}

	public void setQuery(QueryOrderInDAO query) {
		this.query = query;
	}

	@Override
	public List<InOrder> queryOrderIn(int firstPage,String receiptsNum,String repotory,String startDate,String endDate) throws Exception {
		return query.query(firstPage,receiptsNum, repotory, startDate, endDate);
	}

	@Override
	public int getAllPages() {
		return query.getAllPages();
	}

	@Override
	public int getCurrentPage() {
		return query.getCurrentPage();
	}

}









