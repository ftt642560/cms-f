package hjh.orderin.serviceimpl;

import java.util.List;

import hjh.orderin.dao.QueryRepertoriesDAO;
import hjh.orderin.service.QueryRepertoriesService;

public class QueryRepertoriesServiceImpl implements QueryRepertoriesService{
    private QueryRepertoriesDAO queryRepertoriesDAO;
    
	public QueryRepertoriesDAO getQueryRepertoriesDAO() {
		return queryRepertoriesDAO;
	}

	public void setQueryRepertoriesDAO(QueryRepertoriesDAO queryRepertoriesDAO) {
		this.queryRepertoriesDAO = queryRepertoriesDAO;
	}

	@Override
	public List<String> queryRepertories() {
		return queryRepertoriesDAO.queryRepertories();
	}

}
