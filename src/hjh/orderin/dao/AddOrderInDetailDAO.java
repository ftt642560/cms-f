package hjh.orderin.dao;

import java.util.List;

public interface AddOrderInDetailDAO {
    public List<List<String>> query(); 
    
    public boolean add(String inOrderId,String huohao,String pingming,
    		String color,String size,String count);
}
