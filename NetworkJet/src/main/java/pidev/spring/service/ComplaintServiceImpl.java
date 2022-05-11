package pidev.spring.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Complaint;
import pidev.spring.entities.User;
import pidev.spring.repository.ComplaintRepository;
import pidev.spring.repository.UserRepository;




@Service
public class ComplaintServiceImpl implements IComplaint{
	
	@Autowired
	ComplaintRepository complaintRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	
	@Autowired
	private MailSender sender;

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
	public void sendMail(long userId) {
	User u = userRepository.findById(userId).orElse(null);
    SimpleMailMessage msg = new SimpleMailMessage();

     msg.setFrom("sabrine.networkjet@gmail.com");
     msg.setTo(u.getEmail());
     msg.setSubject("Employee en retard");
     msg.setText(" email envoyé lors de test des metiers pidev  ");

      this.sender.send(msg);
    }
	
		
	//}
	
	
	

	//@Override
	//public List<Complaint> SearchComplaint() {
	//	List<Complaint> com=(List<Complaint>)complaintRepository.retrieveAllComplaintsByStatut("grave");
		//for(Complaint complaint:com) {
		
	//}
		//return com;
	//}
	
	
}
	
	