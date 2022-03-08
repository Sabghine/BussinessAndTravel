package pidev.spring.service;

import java.util.List;

import pidev.spring.entities.Comment;

public interface CommentService {
	Comment addComment(Comment comment);
	void deleteComment(int idComment);
	List<Comment> getAllComment();
	Comment getCommentById(int idComment);
	Comment updateCommentById(Comment comment,int idComment);
	//void addlikesComment(int idComment , Long id);
	  // void addDilikesComment(int idComment , Long id);

}
