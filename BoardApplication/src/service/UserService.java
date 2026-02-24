package service;

import java.util.Scanner;
import domain.User;
import repository.UserRepository;
import runner.BoardApplication;

public class UserService {
	Scanner sc = new Scanner(System.in);
	UserRepository userRepository = new UserRepository();
	String userID;
	String userPW;
	
	// 회원가입 메소드
	public void register() {
		/**
		 * 사용자로부터 생성할 아이디와 비밀번호를 입력받고
		 * 중복을 검사하고
		 * 유효성을 검사하며
		 * User 객체 생성을하고
		 * 해당 객체는 UserRepository에 저장해야만한다
		 */
		
		System.out.println("\n회원가입을 진행합니다");
		System.out.print("[사용자 아이디 입력] >> ");
		String id = sc.nextLine();
		System.out.print("[사용자 비빌번호 입력] >> ");
		String pw = sc.nextLine();
		
		if(isDuplicate(id)) {
			System.out.println("이미 존재하는 아이디입니다.");
			return;
		}
		
		if(!isValidate(id, pw)) {
			System.out.println("유효성 검사 실패, 다시 시도해 주세요");
			return;
		}
		
		// 회원가입 완료
		User newUser = new User(id, pw);
		userRepository.save(newUser);
		System.out.println("\n" + newUser.getLoginId() + "님, 회원가입이 완료되었습니다");
		
	}
	
	public boolean isDuplicate(String id) {
		User newUser = userRepository.findByLoginId(id);
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
	
	
	// 로그인 메소드 
	public void login() {
		/**
		 * 사용자로부터 아이디와 비밀번호를 입력고
		 * 입력된 아이디로 사용자를 조회한다.
		 * 비밀번호가 일치하면 세션에 사용자 정보를 저장한다.
		 */
		// id,password 입력
		System.out.println("\n로그인을 진행합니다.");
		System.out.print("[사용자 아이디 입력] >> ");
		String id = sc.nextLine();
		System.out.print("[사용자 비빌번호 입력] >> ");
		String password = sc.nextLine();
		
		// 로그인 처리
		User user = userRepository.findByLoginId(id);
		if(user==null) { // id에 해당하는 user 존재하지 않음
			System.out.println("\n" + id + "님은 존재하지 않는 사용자입니다.");
			return;
		}else { // pw 불일치
			if(user.getLoginPw().equals(password) == false) { 
				System.out.println("\n비밀번호가 일치하지 않습니다.");
				return;
			}
		}
		
		// 로그인 완료
		BoardApplication.session = user;
		System.out.println("\n" + user.getLoginId() + "님께서 로그인하셨습니다!");
	}
	
	
	// 로그아웃 메소드
	public void logout() {
		User user = BoardApplication.session;
		System.out.println("\n" + user.getLoginId() + "님께서 로그아웃되셨습니다!");
		BoardApplication.session = null;
	}
}
