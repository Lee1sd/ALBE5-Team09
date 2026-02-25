package repository;

import java.util.ArrayList;
import java.util.List;

import domain.User;

/** REPOSITORY 에 대해..
 * 
 * [ METHOD 설명 ]
 * .save(User) 				// 유저를 userList에 추가합니다. 
 * 							 ( Service에서 회원가입이 성공적으로 수행된 후, User 객체가 UserRepository의 userList에 저장되어야 합니다. )
 * .findByLoginId(String)	// userList에서 매개변수 id와 일치하는 id가 있는지 보고,
 * 							 존재하면 해당 user 객체를 반환하고, 없다면 null을 반환합니다.
 */
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
