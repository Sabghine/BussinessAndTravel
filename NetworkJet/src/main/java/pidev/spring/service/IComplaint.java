package pidev.spring.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pidev.spring.entities.Complaint;

@Repository
public interface IComplaint {
	
	List<Complaint> retrieveAllComplaints();

	Complaint addComplaint(Complaint c);

	//@Transactional
	//void deleteComplaint(Long id);

	Complaint updateComplaint(Complaint c);

	//Complaint retrieveComplaint(Long id);

}
