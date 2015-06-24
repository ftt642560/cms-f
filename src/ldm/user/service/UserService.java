package ldm.user.service;

import java.util.List;

import ldm.pager.util.PagerBean;
import ldm.user.po.User;

public interface UserService {

	public PagerBean queryForPage(int curPage);
	
	public PagerBean findByPage(int curPage,User user);
	
	//新增用户、更改用户
	public boolean save(User user);
	//删除用户
	public void delete(User user);
	//用户修改密码
	public boolean modifyPassword(String oldPassword,String newPassword,int id);
	//用户登录
	public List login(String usercode,String password);
	
}
