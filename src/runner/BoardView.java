package runner;

import java.util.Collection;

import domain.Post;

public class BoardView {

    public void printPostList(Collection<Post> posts) {

        System.out.println("================ 📋 게시판 목록 =================");
        System.out.printf("%-6s %-12s %-20s\n", "번호", "작성자", "제목");
        System.out.println("-------------------------------------------------");

        for (Post post : posts) {
            System.out.printf(
                "%-6d %-12s %-20s\n",
                post.getPostId(),
                post.getUserId(),
                post.getTitle()
            );
        }

        System.out.println("=================================================");
    }

    public void printPostDetail(Post post) {

        System.out.println("=============== 📄  " + post.getPostId() + " 번 게시글 상세 ===============");
        System.out.println("게시글 번호 : " + post.getPostId());
        System.out.println("작성자     : " + post.getUserId());
        System.out.println("작성일     : " + post.getCreateDate());
        System.out.println("수정일     : " + post.getUpdatedDate());
        System.out.println();
        System.out.println("<제목>");
        System.out.println(post.getTitle());
        System.out.println("<내용>");
        System.out.println(post.getContent());
        System.out.println("=================================================");
    }
}
