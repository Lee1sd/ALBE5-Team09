package domain;

public class User {
	/* ============================
       멤버 필드
    =============================== */
	private String loginId; // 사용자 아이디 
	private String loginPw; // 사용자 비밀번호
	
	
	/* ============================
       생성자
    =============================== */
	public User(String loginId, String loginPw) {
		this.loginId = loginId;
		this.loginPw = loginPw;
	}
	
	
	/* ============================
       Getter 메소드
 	=============================== */
	public String getLoginId() {
		return loginId;
	}
	public String getLoginPw() {
		return loginPw;
	}
}
