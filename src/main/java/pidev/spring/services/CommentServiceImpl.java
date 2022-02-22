package pidev.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Comment;
import pidev.spring.entities.Post;
import pidev.spring.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Override
	public String addComment(Comment comment) {
		commentRepository.save(comment);
		return "Comment added successfully!";
	}

	@Override
	public String updateComment(Comment comment) {
		return null;
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
		found.setComments(comment.getComments());
		found.setCreation_date(comment.getCreation_date());
		found.setDislikes(comment.getDislikes());
		found.setLikes(comment.getLikes());
	    commentRepository.saveAndFlush(found);
		return found;
	}
	

}
