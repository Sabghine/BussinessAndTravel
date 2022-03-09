package pidev.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import pidev.spring.entities.ERole;
import pidev.spring.entities.Post;
import pidev.spring.entities.Reaction;
import pidev.spring.entities.User;
import pidev.spring.repository.PostRepository;
import pidev.spring.repository.ReactionRepository;
import pidev.spring.repository.User_Repository;
import pidev.spring.repository.badwordRepo;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;
	
	
	@Autowired
	private ReactionRepository reactionRepository;
	@Autowired
	private badwordRepo badwordRepo;
	@Autowired 
	 User_Repository  user_Repository ;




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
		Post p = postRepository.findById(idPost).get();
		p.setViews(p.getViews()+1);
		return postRepository.save(p);

	}

	@Override
	public Post updatePostById(Post post, int idPost) {
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

	@Override
	public List<Post> findPostWithSorting(String field) {
		return  postRepository.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC,field));
	}

	@Override
	public Page<Post> findPostWithPagination(int offset, int pageSize) {
		Page<Post> posts = postRepository.findAll(PageRequest.of(offset, pageSize));
        return  posts;
	}

	@Override
	public Page<Post> findPostWithPaginationAndSorting(int offset, int pageSize, String field) {
		 Page<Post> posts = postRepository.findAll(PageRequest.of(offset, pageSize).withSort(org.springframework.data.domain.Sort.by(field)));
	        return  posts;
	}

	@Override
	public List<Post> findAllProducts() {
		return postRepository.findAll();
	}

	@Override
	public List<Post> findAllPostsByUser(Long userid) {
		User user = user_Repository.findById(userid).get();	
		if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_USER))) {
		List<Post> posts = new ArrayList<Post>();
		List<Post> LikedPostsSorted = user.getLikedPosts();
		List<Post> LikedCommentsSorted = user.getLikedComments();
		List<Post> DislikedPostsSorted = user.getDislikedPosts();
		List<Post> DislikedCommentsSorted = user.getDislikedComments();
		LikedPostsSorted.sort((Post o1, Post o2) -> o1.getCreation_date().compareTo(o2.getCreation_date()));
		LikedCommentsSorted.sort((Post o1, Post o2) -> o1.getCreation_date().compareTo(o2.getCreation_date()));
		DislikedPostsSorted.sort((Post o1, Post o2) -> o1.getCreation_date().compareTo(o2.getCreation_date()));
		DislikedCommentsSorted.sort((Post o1, Post o2) -> o1.getCreation_date().compareTo(o2.getCreation_date()));

		posts.addAll(DislikedCommentsSorted);
	posts.addAll(LikedCommentsSorted);
		posts.addAll(DislikedPostsSorted);
		posts.addAll(DislikedCommentsSorted);
		
		return posts;
		}
		else 
		{
			return null;
		}
	}
	@Override
	public Post addlikes(Post post ,int idPost, Long userid) {
		
		User user = user_Repository.findById(userid).orElse(null);
		 
	if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_USER))) {
    Post  post1  = postRepository.findById(idPost).get();
    Reaction react = new Reaction();
    react.setPost(post1);
    react.setUser(user);
    react.setType("like");
    post1.setLikes(post1.getLikes()+1);
    postRepository.save(post1);
    reactionRepository.save(react);
	post1.setUser(user);

    	
	}
			else { 
				return null ;
				}
	return post;
			}
			

	@Override
	public Post addDilikes( Post post,int idPost, Long userid) {
       User user = user_Repository.findById(userid).orElse(null);
		
		if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_USER))) {
			
		Post  post1  = postRepository.findById(idPost).get();
	    Reaction react = new Reaction();
	    react.setPost(post1);
	    react.setUser(user);
	    react.setType("Dilikes");
	    post1.setDislikes(post1.getDislikes()+1);
	    postRepository.save(post1);
	    reactionRepository.save(react);
		post1.setUser(user);

		}
	    else
	    {
	    	return null  ;
	    }
		return (post);
		
		
	}

	@Override
	public String addPost(Post post ,Long userid) {
       User user = user_Repository.findById(userid).orElse(null);
		
		if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_USER))) {
			
			post.setUser(user);
		

		postRepository.save(post);
		
		return "post added  succesfuly";
	}
		
		else 
		{
			return null ;

	
	}
}
}
