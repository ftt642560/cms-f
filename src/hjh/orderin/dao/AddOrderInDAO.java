package hjh.orderin.dao;

import hjh.orderin.domain.InOrder;

public interface AddOrderInDAO {
    public long addOrderIn(InOrder inOrder,int countSum);
}
