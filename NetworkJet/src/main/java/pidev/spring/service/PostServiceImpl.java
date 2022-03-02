package pidev.spring.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
import pidev.spring.entities.Post;
import pidev.spring.repository.PostRepository;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
import javax.annotation.PostConstruct;
import java.security.PublicKey;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
		Post p = postRepository.findById(idPost).get();
		p.setViews(p.getViews()+1);
		return postRepository.save(p);

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

	
	}




