package repositroy;

import java.util.ArrayList;
import java.util.List;

import domain.User;

public class UserRepository {
	List<User> userList = new ArrayList<>();
	
	public void save(User user) {
		userList.add(user);
	}
	
	public User findByLoginId(String id) {
		for(User u : userList) {
			if (u.getLoginId().equals(id)) {
				return u;
			}
		}
		return null;
	}
	
}
