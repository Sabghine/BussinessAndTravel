package pidev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.BadWord;
import pidev.spring.repository.badwordRepo;

@Service
public class BadWordServiceImp  implements BadWordService{

	@Autowired
	private badwordRepo badwordRepo;

	@Override
	public BadWord addBadWord(BadWord badWord) {
		return badwordRepo.save(badWord);
	}	
}

	
