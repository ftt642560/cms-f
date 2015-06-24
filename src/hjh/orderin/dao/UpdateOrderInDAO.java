package hjh.orderin.dao;

public interface UpdateOrderInDAO {
    public boolean update(String data,String receiptsNumber,String inDate,
			String inDepot,String source,String note,String who);
}
