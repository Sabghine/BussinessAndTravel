package pidev.spring.service;

import java.util.List;

import pidev.spring.entities.Comment;
import pidev.spring.entities.Post;

public interface CommentService {
	Comment addComment(Comment comment);
	String updateComment(Comment comment);
	void deleteComment(int idComment);
	List<Comment> getAllComment();
	Comment getCommentById(int idComment);
	Comment updateCommentById(Comment comment,int idComment);
    
	
}
