package pidev.spring.services;

import java.util.List;
import java.util.Observable;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Post;
import pidev.spring.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public String addPost(Post post) {
		postRepository.save(post);
		return "Post added successfully!";
	}

	@Override
	public String updatePost(Post post) {
		postRepository.saveAndFlush(post);
		return "Post updated successfully!";
		
	}
  
	@Override
	public void deletePost(int idPost) {
		postRepository.deleteById(idPost);
		
	}

	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public Post getPostById(int idPost) {
		return postRepository.findById(idPost).get();
	}
	@Override
	public Post updatePostById(Post post,int idPost) {
	
		Post found= postRepository.findById(idPost).orElse(null);
		found.setCaption(post.getCaption());
		found.setComments(post.getComments());
		found.setContent(post.getContent());
		found.setCreation_date(post.getCreation_date());
		found.setDislikes(post.getDislikes());
		found.setLikes(post.getLikes());
		found.setViews(post.getViews());
	    postRepository.saveAndFlush(found);
		return found;
	}

}
