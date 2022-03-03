package pidev.spring.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pidev.spring.entities.Complaint;
import pidev.spring.entities.StatusComplaints;


public interface IComplaint {
	
	List<Complaint> retrieveAllComplaints();

	public Complaint addComplaint(Complaint c);

	@Transactional
	void deleteComplaint(Long id);

	Complaint updateComplaint(Complaint c,Long id);

	Complaint retrieveComplaint(Long id);
	
	
	int CountComplaint();
	
	public int ComplaintToday();
	
	List<Complaint> ListComplaintsByStatus(StatusComplaints statusComplaints);
	
	List<Complaint> GetSortedComplaints(String field);

	
	 public void sendMail(long userId);

	
	//public List<Complaint> SearchComplaint();

}
