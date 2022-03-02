package pidev.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Complaint;
import pidev.spring.repository.ComplaintRepository;



@Service
public class ComplaintServiceImpl implements IComplaint{
	
	@Autowired
	ComplaintRepository complaintRepository;

	@Override
	public List<Complaint> retrieveAllComplaints() {
		return complaintRepository.findAll();
		
	}

	@Override
	public Complaint addComplaint(Complaint c) {
		Date dateComplaint = new Date();
		c.setDateComplaint(dateComplaint);
		return complaintRepository.save(c);
	}

	@Override
	public void deleteComplaint(Long id) {
		complaintRepository.deleteById(id);
		
	}

	@Override
	public Complaint updateComplaint(Complaint c , Long id) {
		Date dateComplaint = new Date();
		c.setDateComplaint(dateComplaint);
		c.setId(id);
		return complaintRepository.save(c);
	}

	@Override
	public Complaint retrieveComplaint(Long id) {
		return complaintRepository.findById(id).orElse(null);
		
	}
}
	
	