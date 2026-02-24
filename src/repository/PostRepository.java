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
    
    
    public void delete(int postId) {
    	postMap.remove(postId);
    }
}
