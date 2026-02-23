package runner;

import domain.User;

public class BoardApplication {
	public static User session = null; // 현재 로그인 사용자
	
	public static void main(String[] args) {
		BoardConsoleRunner runner = new BoardConsoleRunner();
		runner.run();
	}

}
