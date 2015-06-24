package hjh.orderin.action;

import java.util.List;

import hjh.orderin.service.GetAddOrderInService;
import hjh.orderin.service.QueryRepertoriesService;

import com.opensymphony.xwork2.ActionSupport;

public class GetAddOrderInPageAction extends ActionSupport {
	private GetAddOrderInService getAddOrderInService;
	private List<List<String>> clothingInfos;
	private QueryRepertoriesService queryRepertoriesService;
	private List<String> repertories;

	public QueryRepertoriesService getQueryRepertoriesService() {
		return queryRepertoriesService;
	}

	public void setQueryRepertoriesService(
			QueryRepertoriesService queryRepertoriesService) {
		this.queryRepertoriesService = queryRepertoriesService;
	}

	public List<String> getRepertories() {
		return repertories;
	}

	public void setRepertories(List<String> repertories) {
		this.repertories = repertories;
	}

	public List<List<String>> getClothingInfos() {
		return clothingInfos;
	}

	public void setClothingInfos(List<List<String>> clothingInfos) {
		this.clothingInfos = clothingInfos;
	}

	public GetAddOrderInService getGetAddOrderInService() {
		return getAddOrderInService;
	}

	public void setGetAddOrderInService(
			GetAddOrderInService getAddOrderInService) {
		this.getAddOrderInService = getAddOrderInService;
	}

	@Override
	public String execute() throws Exception {
		clothingInfos = getAddOrderInService.getClothingInfos();
		repertories = queryRepertoriesService.queryRepertories();
		if (clothingInfos != null && repertories != null) return SUCCESS;
		return ERROR;
	}

}
