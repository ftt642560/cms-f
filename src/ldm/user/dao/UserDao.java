package ldm.user.dao;

import ldm.user.po.User;

public interface UserDao {

	//新增用户
	public void save(User user);
	//更加id更改用户
	public void save(String sql);
	//删除用户
	public void delete(int id);
}
