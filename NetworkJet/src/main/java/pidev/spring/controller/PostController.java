package pidev.spring.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pidev.spring.entities.APIResponse;
import pidev.spring.entities.Post;
import pidev.spring.service.PostService;

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
	@ResponseBody
	void deletePost(@PathVariable("idPost") int idPost) {
		postService.deletePost(idPost);
	}
	
	@GetMapping("/getAllPosts")
	ResponseEntity<List<Post>> getAllPosts(){
		return new ResponseEntity(postService.getAllPosts(), HttpStatus.OK);
	}
	@PutMapping("/modifyPostByID/{idPost}")
	@ResponseBody
	public Post ModifyPostById(@PathVariable("idPost") int idPost,@RequestBody Post post) {
	return postService.updatePostById(post, idPost);
	}
	@GetMapping ("/getPost/{idPost}")
	@ResponseBody
    public 	Post getPostById (@PathVariable("idPost") int idPost)
    {
		return postService.getPostById(idPost);
    }
	
	   @GetMapping("/search/{field}")
	   @ResponseBody
	    private APIResponse<List<Post>> getPostWithSort(@PathVariable String field) {
	        List<Post> posts = postService.findPostWithSorting(field);
	        return new APIResponse<>(posts.size(), posts);
	    }

	    @GetMapping("/pagination/{offset}/{pageSize}")
	    @ResponseBody
	    private APIResponse<Page<Post>> getPostWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
	        Page<Post> posts = postService.findPostWithPagination(offset, pageSize);
	        return new APIResponse<>(posts.getSize(), posts);
	    }

	    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
	    @ResponseBody
	    private APIResponse<Page<Post>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
	        Page<Post> posts = postService.findPostWithPaginationAndSorting(offset, pageSize, field);
	        return new APIResponse<>(posts.getSize(), posts);
	
        }
	    @GetMapping()
	    @ResponseBody
	    private APIResponse<List<Post>> getPost() {
	        List<Post> allProducts = postService.findAllProducts();
	        return new APIResponse<>(allProducts.size(), allProducts);
	    }
}
