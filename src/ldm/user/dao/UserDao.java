package ldm.user.dao;

import ldm.user.po.User;

public interface UserDao {

	//�����û�
	public void save(User user);
	//����id�����û�
	public void save(String sql);
	//ɾ���û�
	public void delete(int id);
}
