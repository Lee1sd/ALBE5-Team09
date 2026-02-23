package runner;

import java.util.Scanner;

public class BoardConsoleRunner {
	/* ===============================
       멤버 필드
    ================================== */
	//private UserService userService = new UserService();
	private Scanner sc = new Scanner(System.in);
	private boolean isRunning = false; // 실행 여부
	private int selectNum = -1; // 실행 선택 메뉴 번호
	
	
	/* ===============================
       메인 실행 메소드
    ================================== */
	public void run() {	
		// 1. 프로그램 실행 여부 선택 	
		showStartMenu(); // 메뉴 출력 
		selectNum = getMenuInput(1,2); // 메뉴 입력
		handleStartMenu(); // 메뉴 처리
		
		// 2. 프로그램 서비스 메뉴 선택
		while(isRunning) {
			showMainMenu(); // 메뉴 출력
			
			// (로그인 여부에 따른) 메뉴 입력
			if(BoardApplication.session == null) { selectNum = getMenuInput(1,4); }
			else { selectNum = getMenuInput(1,3); }
			
			handleMainMenu(); // 메뉴 처리
		}
		
		// 3. 프로그램 종료 
		System.out.println("\n프로그램을 종료합니다...");
	}

	
	/* ===============================
       메뉴 출력 메소드 (시작 & 실행)
    ================================== */
	private void showStartMenu() { // 시작 메뉴 출력
		System.out.println("\n\n-------------------------------------------------");
		System.out.println("프로그램을 실행하시겠습니까?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		System.out.print("[번호 입력] >> ");
	}
	
	private void showMainMenu() { // 실행 메뉴 출력
		System.out.println("\n\n-------------------------------------------------");
		System.out.println("원하시는 실행 메뉴를 선택해주세요.");
		System.out.println("1. 프로그램 종료");
		System.out.println("2. 게시판 기능");
		
		if(BoardApplication.session == null) { // 로그아웃 사용자
			System.out.println("3. 로그인");
			System.out.println("4. 회원가입");
		}else { // 로그인 사용자
			System.out.println("3. 로그아웃");	
		}
		
		System.out.print("[번호 입력] >> ");
	}
	
	/* ===============================
       메뉴 선택 메소드
    ================================== */
	private int getMenuInput(int minNum, int maxNum) {
	    while (true) {
	        String input = sc.nextLine();

	        try { // 정상 입력 여부 확인
	            int num = Integer.parseInt(input);
	            if (num >= minNum && num <= maxNum) {  return num; } 
	            else { System.out.print("올바른 번호를 입력해주세요\n[번호 입력] >> "); }
	        } catch (NumberFormatException e) {
	        	System.out.print("올바른 번호를 입력해주세요\n[번호 입력] >> ");
	        }
	    }
	}
	

	/* ===============================
       선택 메뉴 처리 메소드 (시작 & 실행)
    ================================== */
	private void handleStartMenu() { // 시작 메뉴 처리
		if(selectNum == 1) { isRunning = true; }
		else if(selectNum == 2) { isRunning = false; }
	}
	
	private void handleMainMenu() { // 실행 메뉴 처리
		if(selectNum == 1) { // 프로그램 종료
			isRunning = false; 
		}
		else if(selectNum == 2) { // 게시판 기능
			System.out.println("\n게시판 기능 사용 구현 진행중...");
		}
		else if(selectNum == 3) { // 로그아웃 & 로그인
			if(BoardApplication.session == null) { // 로그아웃 사용자
				System.out.println("\n로그인 기능 구현 진행중...");
				//userService.login();
			}else { // 로그인 사용자
				System.out.println("\n로그아웃 기능 구현 진행중...");
				//userService.logout();
			}
		}
		else if(selectNum == 4) { // 회원가입
			System.out.println("\n회원가입 기능 구현 진행중...");
			//userService.register();
		}
	}
}
