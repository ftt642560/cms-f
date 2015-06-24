package hjh.orderin.service;

public interface UpdateOrderInService {
    public boolean update(String data,String receiptsNumber,String inDate,
			String inDepot,String source,String note,String who);
}
