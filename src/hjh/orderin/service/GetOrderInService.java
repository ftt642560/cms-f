package hjh.orderin.service;

import java.util.List;

import hjh.orderin.domain.InOrder;

public interface GetOrderInService {
    public InOrder getInOrderById(long id);
    public List<String> getRepertory();
}
