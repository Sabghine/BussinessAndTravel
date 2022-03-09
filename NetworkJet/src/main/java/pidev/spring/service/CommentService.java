package pidev.spring.service;

import java.util.List;

import pidev.spring.entities.Comment;

public interface CommentService {
	 String  addComment(Comment comment,int idPost, Long userid);
	void deleteComment(int idComment);
	List<Comment> getAllComment();
	Comment getCommentById(int idComment);
	Comment updateCommentById(Comment comment,int idComment);
	Comment addlikesComment(Comment comment, int idComment , Long userid);
	 Comment addDilikesComment(Comment comment, int idComment , Long userid);

}
