package pidev.spring.service;

import java.util.List;

import pidev.spring.entities.Invitation;

public interface InvitationService {
	Invitation saveInvitation(Invitation I);
	Invitation updateInvitation(Invitation I);
	void deleteInvitation(Invitation I);
	 void deleteInvitationById(Long id);
	Invitation getInvitation(Long id);
	List<Invitation> getAllInvitations();

}
