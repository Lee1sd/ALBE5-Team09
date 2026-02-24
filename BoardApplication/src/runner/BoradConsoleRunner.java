package runner;

import java.util.Collection;
import java.util.Scanner;

import domain.Post;
import service.PostService;

public class BoradConsoleRunner {

    private Scanner sc = new Scanner(System.in);
    private PostService postService = new PostService();
    private BoardView boardView = new BoardView();

    public void boardMenu() {

        while (true) {

            System.out.println("원하시는 게시판 기능을 선택해주세요.");
            System.out.println("1. 게시판 목록 조회");
            System.out.println("2. 게시글 상세 조회");
            System.out.println("3. 게시글 작성");
            System.out.println("4. 게시글 수정");
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
        System.out.println("user 님의 게시글을 작성합니다.");
        //System.out.println(loginUserId + " 님의 게시글을 작성합니다.");

        System.out.print("[게시글 제목 입력] >> ");
        String title = sc.nextLine();

        System.out.print("[게시글 내용 입력] >> ");
        String content = sc.nextLine();

       int postId = postService.createPost(title, content, 1);

        System.out.println("게시글 작성을 완료하였습니다.");
        System.out.print("게시글 번호: " + postId +"\n");
    }

    private void updatePost(){
        System.out.println("수정할 게시글의 id를 입력해주세요 : ");
        int postId = Integer.parseInt(sc.nextLine());

        //입력 받은 postId 있는지 확인
        Post post = postService.getPostById(postId);
        if(post == null){
            System.out.println("해당 게시글은 존재하지 않습니다 : " + postId);
        }else{
            System.out.println("수정할 부분 선택");
            System.out.println("1. 제목");
            System.out.println("2. 내용");
            System.out.println("0. 이전으로 돌아가기");
            System.out.print("[번호 입력] >> ");

            int select = Integer.parseInt(sc.nextLine());

            switch(select){
                case 1:
                    System.out.println("변경전 게시글 제목 : " + post.getTitle());
                    System.out.println("변경할 제목 입력 >> ");
                    String updatedPostTitle = sc.nextLine();
                    postService.updatePostTitle(postId, updatedPostTitle);
                    System.out.println("변경 되었습니다.");
                    boardView.printPostDetail(post);

                case 2:
                    System.out.println("변경전 게시글 내용 : " + post.getContent());
                    System.out.println("변경할 내용 입력 >> ");
                    String updatedPostContent = sc.nextLine();
                    postService.updatePostContent(postId, updatedPostContent);
                    System.out.println("변경 되었습니다.");
                    boardView.printPostDetail(post);
                case 0:

            }
        }

    }




}