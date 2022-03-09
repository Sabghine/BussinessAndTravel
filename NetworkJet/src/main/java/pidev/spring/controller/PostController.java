package pidev.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pidev.spring.entities.APIResponse;
import pidev.spring.entities.BadWord;
import pidev.spring.entities.Post;
import pidev.spring.repository.badwordRepo;

import pidev.spring.service.PostService;
import pidev.spring.service.Session_UserDetails;

@Controller
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private badwordRepo badwordRepo;
	@PostMapping("/addPost")
	@ResponseBody
	public String addPost(Authentication auth ,@RequestBody Post post) {
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		String textbody= post.getContent();
		List<BadWord> badwordlist = badwordRepo.findAll();
		int compteur=0;
		for(int i=0 ; i<badwordlist.size(); i++)
		{
			if (textbody.contains(badwordlist.get(i).getText()))
			{
				compteur++; 
			}
		}
		if (compteur>0)
		{
			return "your post contains "+compteur+" bad words";

		}
		else	
				{
		return postService.addPost(post,userDetails.getId()) ;
				}
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
		 @GetMapping("/findall")
		    @ResponseBody
		    private APIResponse<List<Post>> getPost() {
		        List<Post> allProducts = postService.findAllProducts();
		        return new APIResponse<>(allProducts.size(), allProducts);
		    }
		 @GetMapping("/findPostsByUser/{idUser}")
		    @ResponseBody
		    private ResponseEntity findPostsByUser(Authentication auth,@PathVariable("idUser") Long idUser) {
			 SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		    	try {
		    		return new ResponseEntity(postService.findAllPostsByUser(idUser), HttpStatus.OK);
		    	}catch(Exception e) {
		    		return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		    	}
		   
		    }
		    @PostMapping("/addlikes/{idpost}")
		    @ResponseBody
		    public void likepost(Authentication auth ,  Post post ,@PathVariable("idpost") int idpost  )
		    {
			 SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		    	postService.addlikes(post,idpost,userDetails.getId());
		    }
		    @PostMapping("/addDislikes/{idpost}")
		    @ResponseBody
		    public void dislikepost(Authentication auth , Post post ,@PathVariable("idpost") int idpost)
		    {
		    	SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		    	postService.addDilikes(post ,idpost,userDetails.getId());
		    }
	
	}
	
	
	


