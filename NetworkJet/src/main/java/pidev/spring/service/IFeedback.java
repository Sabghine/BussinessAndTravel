package pidev.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import pidev.spring.entities.Feedback;

public interface IFeedback {
	
	List<Feedback> retrieveAllFeedbacks();

	Feedback addFeeback(Feedback f);

	@Transactional
	void deleteFeedback(Long id);

	Feedback updateFeedback(Feedback f, Long id);

	Feedback retrieveFeedback(Long id);

}
