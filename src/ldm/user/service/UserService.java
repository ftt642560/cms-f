package ldm.user.service;

import java.util.List;

import ldm.pager.util.PagerBean;
import ldm.user.po.User;

public interface UserService {

	public PagerBean queryForPage(int curPage);
	
	public PagerBean findByPage(int curPage,User user);
	
	//�����û��������û�
	public boolean save(User user);
	//ɾ���û�
	public void delete(User user);
	//�û��޸�����
	public boolean modifyPassword(String oldPassword,String newPassword,int id);
	//�û���¼
	public List login(String usercode,String password);
	
}
