package api;

import java.util.List;

public class UserServiceImpl implements IUserService{
    
	private final IUserRepository repo;
	
	public UserServiceImpl(IUserRepository repo) {
		this.repo = repo;
	}
	@Override
	public List<User> getAllUsers() {
		
		return repo.getAllUsers();
	}

	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return repo.getUserById(id);
	}

	@Override
	public User addUser(User user) {
		if(repo.getUserById(user.getId()) != null) {
		throw new IllegalArgumentException("User already exists");
		}
		repo.addUser(user);
		return user;
	}

	@Override
	public User updateUser(String id, User updatedUser) {
		//Check if the user exists
		if(repo.getUserById(id) == null){
			throw new IllegalArgumentException("User not found: " + updatedUser.getId());
		}
		//update the user
		repo.updateUser(updatedUser);
		
		//Return the updated user
		return updatedUser;
	}

	@Override
	public boolean deleteUser(String id) {
		if(repo.getUserById(id) == null) {
			throw new IllegalArgumentException("User not found: " + id);
		}
		return repo.deleteUser(id);
	}

}
