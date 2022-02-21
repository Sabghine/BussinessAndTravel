package pidev.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pidev.spring.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
