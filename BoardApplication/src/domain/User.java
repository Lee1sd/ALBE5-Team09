package domain;

/** USER 에 대해..
 * domain > User 는 DB에서 데이터의 필드를 명시하는 용도로 만들어졌습니다.
 * 
 * [ FIELD 설명 ]
 * loginId 					// 유저의 로그인 아이디입니다.
 * loginPw 					// 유저의 로그인 비밀번호입니다.
 * 
 * [ METHOD 설명 ]
 * .save(User) 				// 유저를 userList에 추가합니다.
 * .findByLoginId(String)	// userList에서 매개변수 id와 일치하는 id가 있는지 보고,
 * 							 존재하면 해당 user 객체를 반환하고, 없다면 null을 반환합니다.
 */
public class User {
	private String loginId; 
	private String loginPw; 
	
	public User(String loginId, String loginPw) {
		this.loginId = loginId;
		this.loginPw = loginPw;
	}
	
	public String getLoginId() {
		return this.loginId;
	}
	
	public String getLoginPw() {
		return this.loginPw;
	}
}
