package pidev.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pidev.spring.entities.Feedback;

public interface FeedbackRepository extends JpaRepository <Feedback,Long> {

}
