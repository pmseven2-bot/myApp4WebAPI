package api;

import java.util.List;

public interface IUserService {

	List<User> getAllUsers();

	User getUserById(long id);

	User addUser(User user);

	User updateUser(long id, User updatedUser);

	boolean deleteUser(long id);
}
