package api;

import java.util.List;

public interface IUserService {

	List<User> getAllUsers();

	User getUserById(String id);

	User addUser(User user);

	User updateUser(String id, User updatedUser);

	boolean deleteUser(String id);
}
