package pidev.spring.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pidev.spring.entities.Complaint;


public interface IComplaint {
	
	List<Complaint> retrieveAllComplaints();

	Complaint addFournisseur(Complaint c);

	@Transactional
	void deleteComplaint(Long complaintId);

	Complaint updateComplaint(Complaint c);

	Complaint retrieveComplaint(Long complaintId);

}
