package pidev.spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import pidev.spring.entities.Post;

public interface PostService {

	String addPost(Post post);
	String updatePost(Post post);
	void deletePost(int idPost);
	List<Post> getAllPosts();
	Post getPostById(int idPost);
	Post updatePostById(Post post,int idPost); 
	List<Post> findPostWithSorting(String field);
   Page<Post> findPostWithPagination(int offset,int pageSize);
   Page<Post> findPostWithPaginationAndSorting(int offset,int pageSize,String field);
   List<Post> findAllProducts();
} 
