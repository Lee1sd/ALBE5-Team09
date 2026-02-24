package domain;

import java.time.LocalDateTime;

public class Post {
	
	private int postId;
	private String postTitle;
	private String postContent;
	private LocalDateTime postCreateDate;
	private LocalDateTime postUpdatedDate;
	private String loginId;
	
	//생성자
	public Post(int postId, String postTitle, String postContent, String loginId) {
		this.postId = postId;
		this.postContent = postContent;
		this.postTitle = postTitle;
		this.loginId = loginId;
		this.postCreateDate = LocalDateTime.now();
		this.postUpdatedDate = LocalDateTime.now();
		
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return postTitle;
	}

	public void setTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getContent() {
		return postContent;
	}

	public void setContent(String postContent) {
		this.postContent = postContent;
	}

	public LocalDateTime getCreateDate() {
		return postCreateDate;
	}
	

	public void setCreateDate(LocalDateTime postCreateDate) {
		this.postCreateDate = postCreateDate;
	}

	public LocalDateTime getUpdatedDate() {
		return postUpdatedDate;
	}

	public void setUpdatedDate(LocalDateTime postUpdatedDate) {
		this.postUpdatedDate = postUpdatedDate;
	}

	public String getUserId() {
		return loginId;
	}

	public void setUserId(String loginId) {
		this.loginId = loginId;
	}
	
	

}
