package runner;

import java.util.Collection;
import java.util.Scanner;

import domain.Post;
import service.PostService;
import service.UserService;

public class BoradConsoleRunner {
	// 멤버 필드
	private UserService userService = new UserService(); // 아진 구현 전(주석처리)
	private Scanner sc = new Scanner(System.in);
	private boolean isRunning = false; // 실행 여부
	private int selectNum = -1; // 실행 선택 메뉴 번호
    private PostService postService = new PostService();
    private BoardView boardView = new BoardView();

    // 메인 실행 메소드
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
    
    // 메뉴 출력 메소드
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
    
	
	// 메뉴 선택 메소드
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
	
	// 선택 메뉴 처리 메소드
	private void handleStartMenu() { // 시작 메뉴 처리
		if(selectNum == 1) { isRunning = true; }
		else if(selectNum == 2) { isRunning = false; }
	}
	
	private void handleMainMenu() { // 실행 메뉴 처리
		if(selectNum == 1) { // 프로그램 종료
			isRunning = false; 
		}
		else if(selectNum == 2) { // 게시판 기능
			boardMenu();
		}
		else if(selectNum == 3) { // 로그아웃 & 로그인
			if(BoardApplication.session == null) { // 로그아웃 사용자
				userService.login();
			}else { // 로그인 사용자
				userService.logout();
			}
		}
		else if(selectNum == 4) { // 회원가입
			userService.register();
		}
	}
	
	
	// 게시판 메뉴
    public void boardMenu() {

        while (true) {

            System.out.println("\n원하시는 게시판 기능을 선택해주세요.");
            System.out.println("1. 게시판 목록 조회");
            System.out.println("2. 게시글 상세 조회");
            System.out.println("3. 게시글 작성");
            System.out.println("4. 게시글 수정");
            System.out.println("5. 게시글 삭제");
            System.out.println("0. 뒤로가기");
            System.out.print("[번호 입력] >> ");

            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {

                case 1:
                    printPostList();
                    break;

                case 2:
                    printPostDetail();
                    break;

                case 3:
                    createPost();
                    break;

                case 4:
                    updatePost();
                    break;

                case 5:
                    deletePost();
                    break;

                case 0:
                    return; //boardMenu() 종료
            }
        }
    }

    private void printPostList() {
        Collection<Post> posts = postService.getPostList();
        boardView.printPostList(posts);
    }

    private void printPostDetail() {
        System.out.print("조회할 게시글 번호 입력 >> ");
        int id = sc.nextInt();
        sc.nextLine();

        Post post = postService.getPostById(id);

        if (post == null) {
            System.out.println("존재하지 않는 게시글입니다.");
            return;
        }

        boardView.printPostDetail(post);
    }

    private void createPost() {
    	
    	if (BoardApplication.session == null) {
	        System.out.println("로그인 후 이용 가능합니다.");
	        return;
	    }
    	
        System.out.println(BoardApplication.session.getLoginId() +"님의 게시글을 작성합니다.");
        

        System.out.print("[게시글 제목 입력] >> ");
        String title = sc.nextLine();

        System.out.print("[게시글 내용 입력] >> ");
        String content = sc.nextLine();

       int postId = postService.createPost(title, content);

        System.out.println("게시글 작성을 완료하였습니다.");
        System.out.print("게시글 번호: " + postId +"\n");
    }

    private void updatePost(){
        System.out.print("수정할 게시글의 id를 입력해주세요 : ");
        int postId = Integer.parseInt(sc.nextLine());

        Post post = postService.getPostById(postId);
        if(post == null){
            System.out.println("해당 게시글은 존재하지 않습니다 : " + postId);
        } else {
            System.out.println("수정할 부분 선택 (1. 제목 / 2. 내용 / 0. 취소)");
            System.out.print("[번호 입력] >> ");
            int select = Integer.parseInt(sc.nextLine());

            switch(select){
                case 1:
                    System.out.print("변경할 제목 입력 >> ");
                    String updatedPostTitle = sc.nextLine();
                    postService.updatePostTitle(postId, updatedPostTitle);
                    System.out.println("제목이 변경 되었습니다.");
                    break;
                case 2:
                    System.out.print("변경할 내용 입력 >> ");
                    String updatedPostContent = sc.nextLine();
                    postService.updatePostContent(postId, updatedPostContent);
                    System.out.println("내용이 변경 되었습니다.");
                    break;
                case 0:
                    return;
            }
            boardView.printPostDetail(post);
        }
    }

    // 삭제 기능 구현
    private void deletePost() {
        System.out.print("삭제할 게시글의 id를 입력해주세요 : ");
        int postId = Integer.parseInt(sc.nextLine());

        Post post = postService.getPostById(postId);
        if (post == null) {
            System.out.println("해당 게시글이 존재하지 않습니다.");
        } else {
            postService.deletePost(postId);
            System.out.println(postId + "번 게시글이 삭제되었습니다.");
        }
    }




}