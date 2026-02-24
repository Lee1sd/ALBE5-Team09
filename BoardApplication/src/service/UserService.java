package service;

import java.util.ArrayList;
import domain.User;
import repository.UserRepository;

public class UserService {
	UserRepository repo = new UserRepository();
	String userID;
	String userPW;
	
	// register
	public void register(String id, String pw) {
		/**
		 * 중복을 검사하고
		 * 유효성을 검사하며
		 * User 객체 생성을하고
		 * 해당 객체는 UserRepository에 저장해야만한다
		 */
		
		if(isDuplicate(id)) {
			System.out.println("이미 존재하는 아이디입니다.");
			return;
		}
		
		if(!isValidate(id, pw)) {
			System.out.println("유효성 검사 실패, 다시 시도해 주세요");
			return;
		}
		
		User newUser = new User(id, pw);
		
		repo.save(newUser);
		
		System.out.println("회원가입 성공!");
		
	}
	
	public boolean isDuplicate(String id) {
		User newUser = repo.findByLoginId(id);
		return newUser != null;
	}
	
	public boolean isValidate(String id, String pw) {
		if(id == null || id.isEmpty()) {
			System.out.println("ID는 null 혹은 빈값이 될 수 없습니다.");
			return false;
		} else if(pw == null || pw.isEmpty()) {
			System.out.println("PW는 null 혹은 빈값이 될 수 없습니다.");
			return false;
		}
		
		if(id.length() < 2) {
			System.out.println("ID는 최소 2자 이상이어야 합니다");
			return false;
		} else if(pw.length() < 4) {
			System.out.println("PW는 최소 4자 이상이어야 합니다");
			return false;
		}
		
		return true;
	}
}
