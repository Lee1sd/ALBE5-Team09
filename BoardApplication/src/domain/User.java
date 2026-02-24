package domain;

public class User {
	private int user_id = 1;
	private String login_id; // username 겸
	private String login_pw;
	
	public User(String login_id, String login_pw) {
		this.login_id = login_id;
		this.login_pw = login_pw;
	}
	
	public String getLoginId() {
		return this.login_id;
	}
	
	public String getLoginPw() {
		return this.login_pw;
	}
}
