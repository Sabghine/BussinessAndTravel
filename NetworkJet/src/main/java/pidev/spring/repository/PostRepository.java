package pidev.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pidev.spring.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {


}
