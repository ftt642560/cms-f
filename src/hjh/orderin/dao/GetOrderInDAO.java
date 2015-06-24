package hjh.orderin.dao;

import java.util.List;

import hjh.orderin.domain.InOrder;

public interface GetOrderInDAO {
    public InOrder getInOrderById(long id);
    public List<String> getRepertory();
}
