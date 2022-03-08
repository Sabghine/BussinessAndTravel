package pidev.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Post;
import pidev.spring.repository.PostRepository;
import pidev.spring.repository.ReactionRepository;
import pidev.spring.repository.badwordRepo;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;
	
	
	@Autowired
	private ReactionRepository reactionRepository;
	@Autowired
	private badwordRepo badwordRepo;




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

	/*@Override
	public List<Post> findAllPostsByUser(Long idUser) {
		User user = userRepository.findById(idUser).get();		
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
	}*/
	/*@Override
	public void addlikes(int idPost, Long id) {
    Post  post  = postRepository.findById(idPost).get();
    User user = userRepository.findById(id).get();
    Reaction react = new Reaction();
    react.setPost(post);
    react.setUser(user);
    react.setType("like");
    post.setLikes(post.getLikes()+1);
    postRepository.save(post);
    reactionRepository.save(react);
    	
	}*/

	/*@Override
	public void addDilikes(int idPost, Long id) {
	    Post  post  = postRepository.findById(idPost).get();
	    User user = userRepository.findById(id).get();
	    Reaction react = new Reaction();
	    react.setPost(post);
	    react.setUser(user);
	    react.setType("Dilikes");
	    post.setDislikes(post.getDislikes()+1);
	    postRepository.save(post);
	    reactionRepository.save(react);
		
	}*/

	@Override
	public String addPost(Post post) {
		postRepository.save(post);
		
		return "post added  succesfuly";
	}

	

}
