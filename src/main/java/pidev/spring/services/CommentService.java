package pidev.spring.services;

import java.util.List;

import pidev.spring.entities.Comment;

public interface CommentService {
	String addComment(Comment comment);
	String updateComment(Comment comment);
	void deleteComment(int idComment);
	List<Comment> getAllComment();
	Comment getCommentById(int idComment);
	Comment updateCommentById(Comment comment,int idComment);
}
