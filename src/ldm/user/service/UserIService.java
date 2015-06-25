package ldm.user.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ldm.pager.util.MD5Util;
import ldm.pager.util.PagerBean;
import ldm.pager.util.PagerDao;
import ldm.user.dao.UserDao;
import ldm.user.po.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserIService extends HibernateDaoSupport implements UserService,javax.servlet.Filter {

	private PagerDao pagerDao;
	private UserDao userDao;
	private FilterConfig codeConfig = null;
	public static int pageSize = 5;
	public void setPagerDao(PagerDao pagerDao) {
		this.pagerDao = pagerDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public PagerBean findByPage(int curPage,User user) {
		System.out.println("userCode:"+user.getUsercode()+",userName:"+user.getUsername());
		int allRow = 0;
		Object[] obj =  null;
		String hql = "";
		if(!user.getUsercode().equals("") && user.getUsername().equals("")){
			obj = new Object[]{"%"+user.getUsercode()+"%"};
			allRow = pagerDao.getAllRowCount("User", "usercode like '%"+user.getUsercode()+"%'");
			hql = "from User where usercode like ? order by id desc ";
		}
		if(!user.getUsername().equals("") && user.getUsercode().equals("")){
			obj = new Object[]{"%"+user.getUsername()+"%"};
			allRow = pagerDao.getAllRowCount("User", "usercode like '%"+user.getUsername()+"%'");
			hql = "from User where username like ? order by id desc ";
		}
		if(!user.getUsername().equals("") && !user.getUsercode().equals("")){
			obj = new Object[]{"%"+user.getUsercode()+"%","%"+user.getUsername()+"%"};
			allRow = pagerDao.getAllRowCount("User", "usercode like '%"+user.getUsercode()+"%' and username like '%"+user.getUsername()+"%'");
			hql = "from User where usercode like ? and username like ? order by id desc ";
		}
		return pagerDao.findByPage(pageSize, curPage, hql, allRow,obj);
	}

	@Override
	public PagerBean queryForPage(int curPage) {
		int allRow = pagerDao.getAllRowCount("User", null);
		return pagerDao.queryForPage(pageSize, curPage, "from User order by id desc", allRow);
	}
	@Override
	public void delete(User user) {
		userDao.delete(user.getId());
	}
	@Override
	public boolean save(User user) {
		user.setPassword(MD5Util.MD5(user.getPassword()));
		boolean b = false;
		if(user.getId()==0){
			String sql = "select count(*) from User where usercode='"+user.getUsercode()+"'";
			if(count(sql)>0){
				return b;
			}
			
			userDao.save(user);
			b = true;
		}else{
			String sql = "update user set usercode='"+user.getUsercode()+"',username='"+user.getUsername()+"',password='"+user.getPassword()+"',intro='"+user.getIntro()+"' where id="+user.getId();
			userDao.save(sql);
			b = true;
		}
		return b;
	}
	@Override
	public boolean modifyPassword(String oldPassword, String newPassword,int id) {
		String sql = "select count(*) from User where password='"+MD5Util.MD5(oldPassword)+"' and id="+id;
		boolean b = false;
		if(count(sql)>0){
			String sql1 = "update user set password='"+MD5Util.MD5(newPassword)+"' where id="+id;
			userDao.save(sql1);
			b = true;
		}
		return b ;
	}
	
	public int count(String queryString){
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
	@Override
	public List login(String usercode, String password) {
		System.out.println("11111111111111111111");
		String sql = "select count(*) from User where usercode='"+usercode+"' and password='"+MD5Util.MD5(password)+"'";
		if(count(sql)>0){
			System.out.println("2222222222222222");
			String hql= "from User u where u.usercode=? and u.password=?";
			try{
				 return this.getHibernateTemplate().find(hql, new String[]{usercode, MD5Util.MD5(password)});
			}catch (Exception e) {
				e.printStackTrace();
			}
           
            System.out.println("3333333333333333333");
		}
		return null;
	}
	
	@Override
	public void destroy() {
		codeConfig = null;
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.codeConfig = filterConfig;
		UserIService.pageSize = Integer.parseInt(this.codeConfig.getInitParameter("pageSize"));
	}
	
}
