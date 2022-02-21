package pidev.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pidev.spring.entities.Post;
import pidev.spring.services.PostService;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/addPost")
	ResponseEntity<String> addPost(@RequestBody Post post) {
		return new ResponseEntity<>(postService.addPost(post), HttpStatus.CREATED) ;
	}
	
	@PutMapping("/updatePost/{idPost}")
	ResponseEntity<String> updatePost(@RequestBody Post post, @PathVariable("idPost") int idPost) {
		return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
	}
	
	@DeleteMapping("/deletePost/{idPost}")
	void deletePost(@PathVariable("idPost") int idPost) {
		postService.deletePost(idPost);
	}
	
	@GetMapping("/getAllPosts")
	ResponseEntity<List<Post>> getAllPosts(){
		return new ResponseEntity(postService.getAllPosts(), HttpStatus.OK);
	}
	
}
