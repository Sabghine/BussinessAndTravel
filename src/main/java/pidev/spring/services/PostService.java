package pidev.spring.services;

import java.util.List;
import java.util.Set;

import pidev.spring.entities.Post;

public interface PostService {

	String addPost(Post post);
	String updatePost(Post post);
	void deletePost(int idPost);
	List<Post> getAllPosts();
	Post getPostById(int idPost);
   
}
