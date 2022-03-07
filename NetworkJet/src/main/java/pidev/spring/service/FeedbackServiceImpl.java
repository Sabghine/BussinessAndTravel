package pidev.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Feedback;
import pidev.spring.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements IFeedback {

	@Autowired
	FeedbackRepository feebackRepository;
	

	@Override
	public List<Feedback> retrieveAllFeedbacks() {
		return feebackRepository.findAll();
	}

	@Override
	public Feedback addFeeback(Feedback f) {
		Date dateFeedback = new Date();
		f.setDatefeedback(dateFeedback);
		return feebackRepository.save(f);
	}

	@Override
	public void deleteFeedback(Long id) {
		feebackRepository.deleteById(id);
		
	}

	@Override
	public Feedback updateFeedback(Feedback f , Long id) {
		Date dateFeedback = new Date();
		f.setDatefeedback(dateFeedback);
		f.setId(id);
		return feebackRepository.save(f);
	}

	@Override
	public Feedback retrieveFeedback(Long id) {
		return feebackRepository.findById(id).orElse(null);
	}

}


