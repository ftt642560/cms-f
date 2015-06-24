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

	public PagerBean queryForPage(int pageSize, int page,String hql,int allRow) {
		
		//int	allRow = pageDao.getAllRowCount(tableName,findCon);//总记录数
		int totalPage = PagerBean.countTotalPage(pageSize, allRow);//总页数
		final int offset = PagerBean.countOffset(pageSize, page);//当前页开始记录
		final int length = pageSize;//每页记录数
		final int currentPage = PagerBean.countCurrentPage(page);
		List list = queryForPage(hql, offset, length);//"一页"的记录
		//把分页信息保存到Bean中
		PagerBean pageBean = new PagerBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PagerBean findByPage(int pageSize, int page, String hql,int allRow, Object[] values) {
		
		int totalPage = PagerBean.countTotalPage(pageSize, allRow);//总页数
		final int offset = PagerBean.countOffset(pageSize, page);//当前页开始记录
		final int length = pageSize;//每页记录数
		final int currentPage = PagerBean.countCurrentPage(page);
		List list = findByPage(hql, values, offset, length);//"一页"的记录
		//把分页信息保存到Bean中
		PagerBean pageBean = new PagerBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
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
		//通过一个HibernateCallback对象来执行查询
		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
		{
			//实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException
			{
				//执行Hibernate分页查询
				Query query = session.createQuery(hql);
				//为hql语句传入参数
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
	
	//将单、多表查询【非条件查询】共用方法合并
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
