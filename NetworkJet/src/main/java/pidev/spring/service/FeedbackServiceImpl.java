package pidev.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Feedback;
import pidev.spring.repository.FeebackRepository;

@Service
public class FeedbackServiceImpl implements IFeedback {
	
	@Autowired
	FeebackRepository feebackRepository;
	

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
	public void deleteProduit(Long id) {
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
	public Feedback retrieveFeed(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
