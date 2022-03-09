package pidev.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pidev.spring.entities.Comment;
import pidev.spring.entities.Post;
import pidev.spring.service.CommentService;
import pidev.spring.service.Session_UserDetails;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/addComment/{idPost}")
    @ResponseBody
    public String addComment( Authentication auth ,@RequestBody Comment comment , @PathVariable("idPost") int idPost )
    
	{
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();

		return commentService.addComment(comment, idPost ,userDetails.getId());
		
}
	@DeleteMapping("/deleteComment/{idComment}")
	@ResponseBody
	void deleteComment(@PathVariable("idComment") int idComment) {
		commentService.deleteComment(idComment);
	}
	@GetMapping("/getAllComment")
	@ResponseBody
	ResponseEntity<List<Comment>> getAllComment(){
		return new ResponseEntity(commentService.getAllComment(), HttpStatus.OK);
	}
	@PutMapping("/modifyCommentByID/{idComment}")
	@ResponseBody
	public Comment ModifyCommentById(@PathVariable("idComment") int idComment,@RequestBody Comment comment) {
	return commentService.updateCommentById(comment, idComment);
	}
	@PostMapping("/addDislikes/{idComment}")
	 @ResponseBody
	    public void dislikeComment(Authentication auth , Comment comment ,@PathVariable("idComment") int idComment)
	    {
		 SecurityContextHolder.getContext().setAuthentication(auth);
		 
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
	    	commentService.addDilikesComment(comment, idComment, userDetails.getId());
	    	
	    }

	 @PostMapping("/addlikes/{idComment}")
	    @ResponseBody
	    public void likeComment(Authentication auth , Comment comment ,@PathVariable("idComment") int idComment)
	    {
		 SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
	    	commentService.addlikesComment(comment, idComment, userDetails.getId());
	    }
}
