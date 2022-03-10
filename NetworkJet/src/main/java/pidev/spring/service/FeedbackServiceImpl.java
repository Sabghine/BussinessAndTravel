package pidev.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.ERole;
import pidev.spring.entities.Feedback;
import pidev.spring.entities.Trophy;
import pidev.spring.entities.User;
import pidev.spring.repository.FeedbackRepository;
import pidev.spring.repository.User_Repository;

@Service
public class FeedbackServiceImpl implements IFeedback {

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	User_Repository userRepository;
	

	@Override
	public List<Feedback> retrieveAllFeedbacks() {
		return feedbackRepository.findAll();
	}

	@Override
	public Feedback addFeeback(Feedback f, Long userid) {
		
		User user = userRepository.findById(userid).orElse(null);
		
		if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_USER))) {
		Date dateFeedback = new Date();
		f.setDatefeedback(dateFeedback);
		f.setUser(user);
		return feedbackRepository.save(f);
		}
		else
			return null;
		
	}
	

	@Override
	public void deleteFeedback(Long id) {
		feedbackRepository.deleteById(id);
		
	}

	@Override
	public Feedback updateFeedback(Feedback f , Long id) {
		Date dateFeedback = new Date();
		f.setDatefeedback(dateFeedback);
		f.setId(id);
		return feedbackRepository.save(f);
	}

	@Override
	public Feedback retrieveFeedback(Long id) {
		return feedbackRepository.findById(id).orElse(null);
	}

	@Override
	public Trophy AffectTrophy(Long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	

}


