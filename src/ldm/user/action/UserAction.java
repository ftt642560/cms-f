package ldm.user.action;

import java.util.List;

import ldm.pager.util.PagerBean;
import ldm.pager.util.PagerDao;
import ldm.user.dao.UserDao;
import ldm.user.po.User;
import ldm.user.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* 
 * @author ldm
 *
 */
public class UserAction extends ActionSupport{

	
	private PagerBean pagerBean;
	private int searchFlag;//与条件查询区分标志
	private UserService userService;
	private User user;
	private String result;
	private String oldPassword;//接收旧密码
	private List userList;
	
	/**
	 * 查询用户列表
	 * @return
	 */
	public String userList(){
		pagerBean = userService.queryForPage(pagerBean.getPage());
		searchFlag = 0;
		return "userListSuc";
	}
	/**
	 * 条件查询用户列表信息
	 * @return
	 */
	public String userConList(){
		pagerBean = userService.findByPage(pagerBean.getPage(),user);
		searchFlag = 1;
		return "userListSuc";
	}
	/**
	 * 用户添加
	 * @return
	 */
	public String userAdd(){
		try{
			boolean b = userService.save(user);
			if(b)
				result = "添加用户成功";
			else 
				result = "该账号已存在";
		}catch (Exception e) {
			e.printStackTrace();
			result = "添加用户失败";
		}
		return "userOpr";
	}
	public String userUpdate(){
		try{
			userService.save(user);
			result = "更改用户信息成功";
		}catch (Exception e) {
			e.printStackTrace();
			result = "更改用户信息失败";
		}
		return "userOpr";
	}
	/**
	 * 删除用户
	 * @return
	 */
	public String userDel(){
		try{
			userService.delete(user);
			result = "删除用户成功";
		}catch (Exception e) {
			e.printStackTrace();
			result = "删除用户失败";
		}
		return "userOpr";
	}
	/**
	 * 用户修改密码
	 * @return
	 */
	public String modifyPassword(){
		try{
			boolean b = userService.modifyPassword(oldPassword, user.getPassword(),user.getId());
			if(b)
				result = "修改密码成功";
			else
				result = "旧密码或者登录账号不正确";
		}catch (Exception e) {
			e.printStackTrace();
			result = "修改密码失败";
		}
		return "userOpr";
	}
	/**
	 * 登录
	 * @return
	 */
	public String login(){
		userList = userService.login(user.getUsercode(), user.getPassword());
		System.out.println("~~~~~~~~~"+userList);
		if(userList!=null){
			ActionContext.getContext().getSession().put("curUser",userList);//保存当前登录用户
			result = "true";
		}else 
			result = "用户名或者密码错误,请重新输入";
		return "userOpr";
	}
	
	
	public PagerBean getPagerBean() {
		return pagerBean;
	}
	public void setPagerBean(PagerBean pagerBean) {
		this.pagerBean = pagerBean;
	}
	public int getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(int searchFlag) {
		this.searchFlag = searchFlag;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public List getUserList() {
		return userList;
	}
	public void setUserList(List userList) {
		this.userList = userList;
	}
}
