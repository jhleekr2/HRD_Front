package dao.face;

import java.util.List;

import dto.User;

public interface UserDao {

	public List<User> selectAll();

	public void insertUser(User insertUser);

	public User selectByIdx(int i);

	public void deleteByIdx(int i);

}
