package service;

import java.time.LocalDateTime;
import java.util.Collection;

import domain.Post;
import repository.PostRepository;

public class PostService { //CRUD
	 private PostRepository postRepository = new PostRepository();
	 private int nextPostId = 1;
	
	//작성
	public int createPost(String postTitle, String postContent, int userId){
		//번호 생성,post객체 생성, 저장 호출, postId반환
		int postId = nextPostId++;
		Post post = new Post(postId, postTitle, postContent, userId);
		postRepository.save(post);
		return postId;
	}
	
	//목록 조회
	public Collection<Post>  getPostList() { //뭘 기준으로 찾을 필요 없어서 전체 반환
		return postRepository.findAll();
		
	}
	
	//상세 조회
	public Post getPostById(int postId) {
		return postRepository.findById(postId);
		
	}
	
	
	public void updatePost(int postId, String title, String content) {
		
	}
	
	public void deletePost(int postId) {
		
	}
	

}
