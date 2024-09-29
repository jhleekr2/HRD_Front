package dao.face;

import java.util.List;

import dto.User;

public interface UserDao {

	public List<User> selectAll();

	public int insertUser(User data);

	public User selectByIdx(int idx);

	public void deleteByIdx(int i);

}
