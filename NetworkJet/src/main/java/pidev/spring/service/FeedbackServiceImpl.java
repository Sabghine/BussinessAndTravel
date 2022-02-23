package pidev.spring.service;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback addFeeback(Feedback f) {
		return feebackRepository.save(f);
	}

	@Override
	public void deleteProduit(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Feedback updateFeedback(Feedback f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback retrieveFeed(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
