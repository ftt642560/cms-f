package ldm.pager.util;

public interface PagerDao {

	
	/**
	 * 分页的pageBean
	 * @param flag	标识hql是否存在参数查询
	 * @param pageSize	每页显示记录数
	 * @param currentPage	为当前页
	 * @return
	 * 
	 * @param1	每页记录数
	 * @param2	当前页数
	 * @param3	查询表信息的HQL语句
	 * @param4	查询出表信息总记录数
	 */
	public PagerBean queryForPage(int pageSize,int currentPage,String hql,int allRow);
	
	
	/**
	 * 查询信息分页【hql语句带条件查询分页】
	 * @param pageSize
	 * @param page
	 * @param hql
	 * @param allRow
	 * @param values
	 * @return
	 */
	public PagerBean findByPage(int pageSize,int page,final String hql ,int allRow,Object[] values);
	/**
	 * 获取表总记录数
	 * @param tableName	表名
	 * @param findCon	条件查询
	 * @return
	 */
	public int getAllRowCount(String tableName,String findCon);
}
