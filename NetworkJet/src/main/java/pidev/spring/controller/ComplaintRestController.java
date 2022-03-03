package pidev.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pidev.spring.entities.Complaint;
import pidev.spring.entities.StatusComplaints;
import pidev.spring.service.IComplaint;

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

		// http://localhost:8080/complaint/add-complaint
		@PostMapping("/add")
		@ResponseBody
		public Complaint addComplaint(@RequestBody Complaint c) {
			return complaintService.addComplaint(c);
			 
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
		 
		 
		 
		 //@GetMapping("/recherche")
		 // @ResponseBody
		// public List<Complaint> SearchComp() {
			//  List<Complaint> list  =complaintService.SearchComplaint();
			//return list;
		 //  }

	}

	
	