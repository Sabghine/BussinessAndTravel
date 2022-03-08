package pidev.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Comment;
import pidev.spring.entities.User;
import pidev.spring.repository.CommentRepository;
@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Comment addComment(Comment comment) {
		
		
		 return commentRepository.save(comment);		
	}

	

	@Override
	public void deleteComment(int idComment) {
		commentRepository.deleteById(idComment);
		
	}

	@Override
	public List<Comment> getAllComment() {
		return commentRepository.findAll();

	}

	@Override
	public Comment getCommentById(int idComment) {
		return commentRepository.findById(idComment).get();

	}

	@Override
	public Comment updateCommentById(Comment comment, int idComment) {
		Comment found= commentRepository.findById(idComment).orElse(null);
		found.setContent(comment.getContent());
		found.setCreation_date(comment.getCreation_date());
		found.setDislikes(comment.getDislikes());
		found.setLikes(comment.getLikes());
	    commentRepository.saveAndFlush(found);
		return found;
	}

	/*@Override
	public void addlikesComment(int idComment, Long id) {
		Comment  comment  = commentRepository.findById(idComment).get();
	    User user = userRepository.findById(id).get();
	    Reaction react = new Reaction();
	    react.setComments(comment);
	    react.setUser(user);
	    react.setType("like");
	    comment.setLikes(comment.getLikes()+1);
	    commentRepository.save(comment);
	    reactionRepository.save(react)		
	}*/
/*@Override
public void addDilikesComment(int idComment, Long id) {
	  	Comment  comment  = commentRepository.findById(idComment).get();
	    User user = userRepository.findById(id).get();
	    Reaction react = new Reaction();
	    react.setComments(comment);
	    react.setUser(user);
	    react.setType("Dilikes");
	    comment.setDislikes(comment.getDislikes()+1);
	    commentRepository.save(comment);
	    reactionRepository.save(react);		
}*/
}
