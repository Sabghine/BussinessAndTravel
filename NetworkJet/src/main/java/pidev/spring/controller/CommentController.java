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

import pidev.spring.entities.Comment;
import pidev.spring.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/addComment")
    @ResponseBody
    public Comment addComment(@RequestBody Comment comment)
    
	{
		return commentService.addComment(comment);
		//return new ResponseEntity<>(commentService.addComment(comment), HttpStatus.CREATED) ;
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
	/*@PostMapping("/addDislikes/{idComment}")
	 @ResponseBody
	    public void dislikeComment(@PathVariable("idComment") int idpost)
	    {
	    	commentService.addDilikesComment(idpost, (long) 1);
	    }

	 @PostMapping("/addlikes/{idComment}")
	    @ResponseBody
	    public void likeComment(@PathVariable("idComment") int idpost)
	    {
	    	commentService.addlikesComment(idpost, (long) 1);
	    }*/
}
