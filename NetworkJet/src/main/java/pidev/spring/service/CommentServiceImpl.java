package pidev.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Comment;
import pidev.spring.entities.ERole;
import pidev.spring.entities.Post;
import pidev.spring.entities.Reaction;
import pidev.spring.entities.User;
import pidev.spring.repository.CommentRepository;
import pidev.spring.repository.PostRepository;
import pidev.spring.repository.ReactionRepository;
import pidev.spring.repository.User_Repository;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	User_Repository user_Repository ;
	@Autowired
	ReactionRepository reactionRepository;
	@Autowired
	PostRepository postRepository ;

	@Override
	public String addComment(Comment comment, int idPost, Long userid) {
		
		Post post = postRepository.findById(idPost).orElse(null);
		comment.setPost(post);
		 User user = user_Repository.findById(userid).orElse(null);
			
			if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_USER))) {
				
				post.setUser(user);
	
			commentRepository.save(comment);
			
			return "Comment added  succesfuly";
			}
			else {
				return null;
			}
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

	@Override
	public Comment addlikesComment(Comment comment ,int idComment, Long userid) {
		
		User user = user_Repository.findById(userid).orElse(null);
		 
	if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_USER))) {
		Comment  comment1  = commentRepository.findById(idComment).get();
	    Reaction react = new Reaction();
	    react.setComments(comment1);
	    react.setUser(user);
	    react.setType("likes");
	    comment1.setLikes(comment1.getLikes()+1);
	    commentRepository.save(comment1);
	    reactionRepository.save(react);
	
    	
	}
			else { 
				return null ;
				}
	return comment;
			}
			

	@Override
	public Comment addDilikesComment( Comment comment,int idComment, Long userid) {
		User user = user_Repository.findById(userid).orElse(null);
		 
		if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_USER))) {
			Comment  comment1  = commentRepository.findById(idComment).get();
		    Reaction react = new Reaction();
		    react.setComments(comment1);
		    react.setUser(user);
		    react.setType("dislikes");
		    comment1.setDislikes(comment1.getDislikes()+1);
		    commentRepository.save(comment1);
		    reactionRepository.save(react);
		
	    	
		}
				else { 
					return null ;
					}
		return comment;
				}
}
