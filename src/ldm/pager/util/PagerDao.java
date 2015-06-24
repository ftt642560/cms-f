package ldm.pager.util;

public interface PagerDao {

	
	/**
	 * ��ҳ��pageBean
	 * @param flag	��ʶhql�Ƿ���ڲ�����ѯ
	 * @param pageSize	ÿҳ��ʾ��¼��
	 * @param currentPage	Ϊ��ǰҳ
	 * @return
	 * 
	 * @param1	ÿҳ��¼��
	 * @param2	��ǰҳ��
	 * @param3	��ѯ����Ϣ��HQL���
	 * @param4	��ѯ������Ϣ�ܼ�¼��
	 */
	public PagerBean queryForPage(int pageSize,int currentPage,String hql,int allRow);
	
	
	/**
	 * ��ѯ��Ϣ��ҳ��hql����������ѯ��ҳ��
	 * @param pageSize
	 * @param page
	 * @param hql
	 * @param allRow
	 * @param values
	 * @return
	 */
	public PagerBean findByPage(int pageSize,int page,final String hql ,int allRow,Object[] values);
	/**
	 * ��ȡ���ܼ�¼��
	 * @param tableName	����
	 * @param findCon	������ѯ
	 * @return
	 */
	public int getAllRowCount(String tableName,String findCon);
}
