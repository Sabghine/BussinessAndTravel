package pidev.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pidev.spring.entities.Post;

public interface PostService {
	String addPost(Post post ,Long userid);
	String updatePost(Post post);
	void deletePost(int idPost);
	List<Post> getAllPosts();
	Post getPostById(int idPost);
	Post updatePostById(Post post,int idPost); 
	List<Post> findPostWithSorting(String field);
   Page<Post> findPostWithPagination(int offset,int pageSize);
   Page<Post> findPostWithPaginationAndSorting(int offset,int pageSize,String field);
   List<Post> findAllProducts();
   List<Post> findAllPostsByUser(Long idUser);
  Post addlikes(Post post, int idPost , Long userid);
   Post addDilikes(Post post,int idPost , Long userid);

}
