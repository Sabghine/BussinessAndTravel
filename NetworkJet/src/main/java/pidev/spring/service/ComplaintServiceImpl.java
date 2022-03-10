package pidev.spring.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Complaint;
import pidev.spring.entities.ERole;
import pidev.spring.entities.StatusComplaints;
import pidev.spring.entities.User;
import pidev.spring.repository.ComplaintRepository;
import pidev.spring.repository.User_Repository;


@Service
public class  ComplaintServiceImpl implements IComplaint{
	
	
	@Autowired
    private MailSender sender;

	@Autowired
	ComplaintRepository complaintRepository;
	
	@Autowired
	User_Repository userRepository;

	@Override
	public List<Complaint> retrieveAllComplaints() {
		return complaintRepository.findAll();
	}

	@Override
	public Complaint addComplaint(Complaint c,Long userid) {
		User user = userRepository.findById(userid).orElse(null);
		
		if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_EMPLOYEE))) {
		
		Date dateComplaint = new Date();
		c.setDateComplaint(dateComplaint);
		c.setUser(user);
			return complaintRepository.save(c);
		}
		else
		{
			return null;
		}
		
	}

	@Override
	public void deleteComplaint(Long id) {
		complaintRepository.deleteById(id);
		
	}

	

	@Override
	public Complaint retrieveComplaint(Long id) {
		return complaintRepository.findById(id).orElse(null);
	}

	@Override
	public int CountComplaint() {
		int max=0;
		List<Complaint> c =(List<Complaint>) complaintRepository.findAll();
		for(Complaint complaint:c) {
			max++;
			
		}
		return max;
	}

	
	@Override
	@SuppressWarnings("deprecation")
	public int ComplaintToday() {
		List<Complaint> comp=(List<Complaint>) complaintRepository.findAll();
		int max=0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();			
		for(Complaint c:comp) {			
		if ((c.getDateComplaint().getDay()== date.getDay() ) 
				&& (c.getDateComplaint().getMonth()==date.getMonth() 
				&&(c.getDateComplaint().getYear() ==date.getYear())))
						max++;		
		}
		return max;	
	}

	@Override
	public List<Complaint> ListComplaintsByStatus(StatusComplaints statusComplaints) {
		return complaintRepository.findByStatusComplaints(statusComplaints);
	}

	@Override
	public List<Complaint> GetSortedComplaints(String field) {
		return complaintRepository.findAll(Sort.by(Sort.Direction.DESC,field)) ;
	}

	@Override
	public void sendMail(long id) {
		User u = userRepository.findById(id).orElse(null);
	    SimpleMailMessage msg = new SimpleMailMessage();

	     msg.setFrom("sabrine.networkjet@gmail.com");
	     msg.setTo(u.getEmail());
	     msg.setSubject("Employee en retard");
	     msg.setText(" email envoyé lors de test des metiers pidev  ");
	     
	     this.sender.send(msg);
	     
	}
        
	     
	     @Scheduled(cron="*/30 * * * * *")
	 	public void nbreComplaintsByStatusComplaints(){
	 		int nbrGrave=complaintRepository.getComplaintsByStatusComplaints(StatusComplaints.grave);
	 		int nbrMoyenne=complaintRepository.getComplaintsByStatusComplaints(StatusComplaints.moyenne);
	 		int nbrFaible=complaintRepository.getComplaintsByStatusComplaints(StatusComplaints.faible);
	 		System.out.println("Nombre des réclamations grave:"+nbrGrave);
	 		System.out.println("nombre des Reclamations moyenne:"+nbrMoyenne);
	 		System.out.println("nombre des Reclamations faible:"+nbrFaible);
	 		
	 	}

		@Override
		public Complaint updateComplaint(Complaint c, Long id,Long userid) {
			User user = userRepository.findById(userid).orElse(null);
			
			if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_EMPLOYEE))) {
				
			
			Date dateComplaint = new Date();
			c.setDateComplaint(dateComplaint);
			c.setId(id);
			c.setUser(user);
				return complaintRepository.save(c);
		}
			else
			{
				return null;
				}
		
				}
}
		

		
		

			


