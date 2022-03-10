package pidev.spring.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import pidev.spring.entities.Complaint;
import pidev.spring.entities.StatusComplaints;
import pidev.spring.service.ComplaintPDF;
import pidev.spring.service.IComplaint;
import pidev.spring.service.Session_UserDetails;


@RestController
@RequestMapping("complaint")
public class ComplaintRestController {
	
	@Autowired
	IComplaint complaintService;
	
	
	
	
	
	// http://localhost:8080/complaint/retrieve-all-complaints
		@GetMapping("/get-all")
		@ResponseBody
		public List<Complaint> getComplaints() {
			return complaintService.retrieveAllComplaints();
			
		}

		//http://localhost:8080/complaint/get-complaint/5	
		@GetMapping("/get/{id}")
		@ResponseBody
		public Complaint getComplaint(@PathVariable("id") Long complaintId) {
		return complaintService.retrieveComplaint(complaintId);
		
		}

		// http://localhost:8080/complaint/add
		@PostMapping("/add")
		@ResponseBody
		public Complaint addComplaint(Authentication auth,@RequestBody Complaint c) {
		   	SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return complaintService.addComplaint(c,userDetails.getId());
			 
		}

		// http://localhost:8080/complaint/update-complaint/5
		@PutMapping("/update/{id}")
		@ResponseBody
		public Complaint updateComplaint(@PathVariable("id") Long id, @RequestBody Complaint c) {
			return complaintService.updateComplaint(c, id);
			 
		}

		//http://localhost:8080/complaint/delete-complaint/7
		@DeleteMapping("/delete/{id}")
		@ResponseBody
		public void deleteComplaint(@PathVariable("id") Long complaintId) {
			complaintService.deleteComplaint(complaintId);
		}
		
		
		@GetMapping ("/ComplaintToday")
		@ResponseBody
		   public int ComplaintToday() {
		   return complaintService.ComplaintToday();
		   }
		
		
		 @GetMapping("/count")
		   @ResponseBody
		   public int CountComplaint() {
		   return complaintService.CountComplaint();
		   }
		 
		 @GetMapping("/GetComplaintStatus/{statusComplaints}")
			@ResponseBody
			 List<Complaint> GetComplaintStatus(@PathVariable("statusComplaints")StatusComplaints statusComplaints) {
				return complaintService.ListComplaintsByStatus(statusComplaints);
			}
		 
		 
		 @GetMapping("/{field}")
			@ResponseBody
			List<Complaint> GetSortedComplaints(@PathVariable("field") String field){
			return complaintService.GetSortedComplaints(field);
			}
		 
		 
		 
		 @PostMapping("/mail/{userId}")
		 @ResponseBody
		 public void sendMail( @PathVariable("userId") long userId) {
		complaintService.sendMail(userId);
			}
		 
		 @GetMapping("/export/pdf")
		    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		        response.setContentType("application/pdf");
		        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		        String currentDateTime = dateFormatter.format(new Date());
		         
		        String headerKey = "Content-Disposition";
		        String headerValue = "attachment; filename=complaints_" + currentDateTime + ".pdf";
		        response.setHeader(headerKey, headerValue);
		         
		        List<Complaint> listComplaints = complaintService.retrieveAllComplaints();
		         
		        ComplaintPDF exporter = new ComplaintPDF(listComplaints);
		        exporter.export(response);
		 
		 
		

	}
		 }

	