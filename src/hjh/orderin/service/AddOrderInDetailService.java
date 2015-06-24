package hjh.orderin.service;

import java.util.List;

public interface AddOrderInDetailService {
    public List<List<String>> query();
    
    public boolean save(String inOrderId,String huohao,String pingming,
    		String color,String size,String count);
}
