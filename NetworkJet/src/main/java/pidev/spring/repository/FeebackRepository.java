package pidev.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pidev.spring.entities.Feedback;



@Repository
public interface FeebackRepository extends JpaRepository<Feedback, Long> {

}
