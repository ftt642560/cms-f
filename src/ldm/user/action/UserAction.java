package ldm.user.action;

import java.util.List;

import ldm.pager.util.PagerBean;
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
	private int searchFlag;//��������ѯ��ֱ�־
	private UserService userService;
	private User user;
	private String result;
	private String oldPassword;//���վ�����
	private List userList;
	
	/**
	 * ��ѯ�û��б�
	 * @return
	 */
	public String userList(){
		pagerBean = userService.queryForPage(pagerBean.getPage());
		searchFlag = 0;
		return "userListSuc";
	}
	/**
	 * ������ѯ�û��б���Ϣ
	 * @return
	 */
	public String userConList(){
		pagerBean = userService.findByPage(pagerBean.getPage(),user);
		searchFlag = 1;
		return "userListSuc";
	}
	/**
	 * �û����
	 * @return
	 */
	public String userAdd(){
		try{
			boolean b = userService.save(user);
			if(b)
				result = "����û��ɹ�";
			else 
				result = "���˺��Ѵ���";
		}catch (Exception e) {
			e.printStackTrace();
			result = "����û�ʧ��";
		}
		return "userOpr";
	}
	public String userUpdate(){
		try{
			userService.save(user);
			result = "����û���Ϣ�ɹ�";
		}catch (Exception e) {
			e.printStackTrace();
			result = "����û���Ϣʧ��";
		}
		return "userOpr";
	}
	/**
	 * ɾ���û�
	 * @return
	 */
	public String userDel(){
		try{
			userService.delete(user);
			result = "ɾ���û��ɹ�";
		}catch (Exception e) {
			e.printStackTrace();
			result = "ɾ���û�ʧ��";
		}
		return "userOpr";
	}
	/**
	 * �û��޸�����
	 * @return
	 */
	public String modifyPassword(){
		try{
			boolean b = userService.modifyPassword(oldPassword, user.getPassword(),user.getId());
			if(b)
				result = "�޸�����ɹ�";
			else
				result = "��������ߵ�¼�˺Ų���ȷ";
		}catch (Exception e) {
			e.printStackTrace();
			result = "�޸�����ʧ��";
		}
		return "userOpr";
	}
	/**
	 * ��¼
	 * @return
	 */
	public String login(){
		userList = userService.login(user.getUsercode(), user.getPassword());
		System.out.println("~~~~~~~~~"+userList);
		if(userList!=null){
			ActionContext.getContext().getSession().put("curUser",userList);//���浱ǰ��¼�û�
			result = "true";
		}else 
			result = "�û�������������,����������";
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
