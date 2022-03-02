package pidev.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Invitation;
import pidev.spring.repository.InvitationRepository;

@Service
public class InvitationServiceImpl implements InvitationService {

	@Autowired
	InvitationRepository invitationRepository;
	
	@Override
	public Invitation saveInvitation(Invitation I) {
		
		return invitationRepository.save(I);
	}

	@Override
	public Invitation updateInvitation(Invitation I) {
		return invitationRepository.save(I);
	}

	@Override
	public void deleteInvitation(Invitation I) {
		invitationRepository.delete(I);
		
	}

	@Override
	public void deleteInvitationById(Long id) {
		invitationRepository.deleteById(id);
		
	}

	@Override
	public Invitation getInvitation(Long id) {
		return invitationRepository.findById(id).get();
	}

	@Override
	public List<Invitation> getAllInvitations() {
		return invitationRepository.findAll();
		 
	}

}
