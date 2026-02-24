package domain;

public class User {
	private String loginId; // 사용자 아아디
	private String loginPw; // 사용자 비빌번호
	
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
