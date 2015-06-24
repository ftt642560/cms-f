package hjh.orderin.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import hjh.orderin.dao.GetAddOrderInDAO;
import hjh.orderin.service.GetAddOrderInService;

public class GetAddOrderInServiceImpl implements GetAddOrderInService {
    private GetAddOrderInDAO getAddOrderInDAO;
    
	public GetAddOrderInDAO getGetAddOrderInDAO() {
		return getAddOrderInDAO;
	}

	public void setGetAddOrderInDAO(GetAddOrderInDAO getAddOrderInDAO) {
		this.getAddOrderInDAO = getAddOrderInDAO;
	}

	@Override
	public List<List<String>> getClothingInfos() {
		List<List<String>> infos = getAddOrderInDAO.getClothingInfos();
		if(infos != null) return filter(infos);
		return infos;
	}

	public List<List<String>> filter(List<List<String>> infos){
		List<String> huohao = new ArrayList<String>();
		List<String> pingming = new ArrayList<String>();
		List<String> color = new ArrayList<String>();
		List<String> size = new ArrayList<String>();
		for(int i = 0;i < infos.get(0).size();i++){
			if(! huohao.contains(infos.get(0).get(i))) huohao.add(infos.get(0).get(i));
		}
		for(int i = 0;i < infos.get(1).size();i++){
			if(! pingming.contains(infos.get(1).get(i))) pingming.add(infos.get(1).get(i));
		}
		for(int i = 0;i < infos.get(2).size();i++){
			if(! color.contains(infos.get(2).get(i))) color.add(infos.get(2).get(i));
		}
		for(int i = 0;i < infos.get(3).size();i++){
			if(! size.contains(infos.get(3).get(i))) size.add(infos.get(3).get(i));
		}
		infos.clear();
		infos.add(huohao);
		infos.add(pingming);
		infos.add(color);
		infos.add(size);
		return infos;
	}
}
