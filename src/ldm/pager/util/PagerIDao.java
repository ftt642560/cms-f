package ldm.pager.util;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PagerIDao extends HibernateDaoSupport implements PagerDao{

	private PagerDao pagerDao;
	public PagerDao getPagerDao() {
		return pagerDao;
	}

	public void setPagerDao(PagerDao pagerDao) {
		this.pagerDao = pagerDao;
	}

	@Override
	public PagerBean queryForPage(int pageSize, int page,String hql,int allRow) {
		
		//int	allRow = pageDao.getAllRowCount(tableName,findCon);//�ܼ�¼��
		int totalPage = PagerBean.countTotalPage(pageSize, allRow);//��ҳ��
		final int offset = PagerBean.countOffset(pageSize, page);//��ǰҳ��ʼ��¼
		final int length = pageSize;//ÿҳ��¼��
		final int currentPage = PagerBean.countCurrentPage(page);
		List list = queryForPage(hql, offset, length);//"һҳ"�ļ�¼
		//�ѷ�ҳ��Ϣ���浽Bean��
		PagerBean pageBean = new PagerBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	@Override
	public PagerBean findByPage(int pageSize, int page, String hql,int allRow, Object[] values) {
		
		int totalPage = PagerBean.countTotalPage(pageSize, allRow);//��ҳ��
		final int offset = PagerBean.countOffset(pageSize, page);//��ǰҳ��ʼ��¼
		final int length = pageSize;//ÿҳ��¼��
		final int currentPage = PagerBean.countCurrentPage(page);
		List list = findByPage(hql, values, offset, length);//"һҳ"�ļ�¼
		//�ѷ�ҳ��Ϣ���浽Bean��
		PagerBean pageBean = new PagerBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	@Override
	public int getAllRowCount(String tableName,String findCon) {
		String Hql = "";
		if(findCon!=null)
			Hql = "select count(*) from " + tableName+" where "+findCon;
		else
			Hql = "select count(*) from " + tableName;
		
		return oneManyTableRow(Hql.toString());
	}
	
	public List queryForPage(final String hql,final int offset,final int length){
		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
		@Override
		public Object doInHibernate(Session session) throws HibernateException,SQLException{
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(length);

	    List list = query.list();
		return list;}
		});
	
		return list;
	}
	
	public List findByPage(final String hql, final Object[] values, final int offset, final int length) {
		//ͨ��һ��HibernateCallback������ִ�в�ѯ
		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
		{
			//ʵ��HibernateCallback�ӿڱ���ʵ�ֵķ���
			@Override
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException
			{
				//ִ��Hibernate��ҳ��ѯ
				Query query = session.createQuery(hql);
				//Ϊhql��䴫�����
				for (int i = 0 ; i < values.length ; i++)
				{
					query.setParameter( i, values[i]);
				}
				List result = query.setFirstResult(offset)
					.setMaxResults(length)
					.list();
				return result;
			}
		});
		return list;
	}
	
	//����������ѯ����������ѯ�����÷����ϲ�
	public int oneManyTableRow(String queryString){
		Session session=null;
		try{
			 session=this.getSession();
			 Query query = session.createQuery(queryString.toString());
			 return Integer.parseInt(query.list().iterator().next().toString());
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally{
			session.flush();
			session.close();
		}
	}
}
