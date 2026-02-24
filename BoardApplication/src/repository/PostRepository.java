package repository;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import domain.Post;

public class PostRepository { //DB역할
	
	private Map<Integer, Post> postMap = new TreeMap<>();
	
    public void save(Post post) {
      postMap.put(post.getPostId(), post);
    	
    }
    
    public Collection<Post> findAll() {
    	return postMap.values();
    }
    
    public Post findById(int postId) {
    	return postMap.get(postId);
    }

    //게시판 제목 변경
    public Post updateTitle(int postId, String updatedPostTitle){
        Post post = postMap.get(postId);

        if(post!=null){
            post.setTitle(updatedPostTitle);
        }

        return post;
    }

    //게시판 내용 변경
    public Post updateContent(int postId, String updatedPostContent){
        Post post = postMap.get(postId);

        if(post!=null){
            post.setContent(updatedPostContent);
        }

        return post;
    }


    public void delete(int postId) {
    	postMap.remove(postId);
    }


}
