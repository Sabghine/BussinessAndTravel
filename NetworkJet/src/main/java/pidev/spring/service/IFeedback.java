package pidev.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import pidev.spring.entities.Feedback;
import pidev.spring.entities.Trophy;

public interface IFeedback {
	
	List<Feedback> retrieveAllFeedbacks();

	Feedback addFeeback(Feedback f,Long userid);

	@Transactional
	void deleteFeedback(Long id);

	Feedback updateFeedback(Feedback f, Long id,Long userid);

	Feedback retrieveFeedback(Long id);
	
	public Trophy AffectTrophy(Long userid);
	
	


}
