package api;

import java.util.List;


public interface IUserRepository {
	void addUser(User user);
	void updateUser(User user);
	boolean deleteUser(String userId);
	User getUserById(String id);
	List<User> getAllUsers();
	List<User> searchUsers(String keyword);
	void save();

}
