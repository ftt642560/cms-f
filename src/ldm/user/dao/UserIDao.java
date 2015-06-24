package ldm.user.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ldm.user.po.User;

public class UserIDao extends HibernateDaoSupport implements UserDao{

	public User get(Integer id){

		return (User)getHibernateTemplate()
			.get(User.class , id);
	}
	@Override
	public void delete(int id) {
		getHibernateTemplate()
		.delete(get(id));
	}

	@Override
	public void save(User user) {
		getHibernateTemplate()
		.save(user);
	}
	@Override
	public void save(String sql) {
		this.getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(sql).executeUpdate();
	}

	
}
