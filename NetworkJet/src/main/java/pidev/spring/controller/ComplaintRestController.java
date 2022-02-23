package pidev.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pidev.spring.entities.Complaint;
import pidev.spring.service.IComplaint;

@RestController
public class ComplaintRestController {
	@Autowired 
	IComplaint complaintService;
	
	// http://localhost:8080/complaint/retrieve-all-complaints
		@GetMapping("/retrieve-all-complaints")
		@ResponseBody
		public List<Complaint> getComplaints() {
			List<Complaint> listComplaints = complaintService.retrieveAllComplaints();
			return listComplaints;
		}

		//http://localhost:8080/complaint/get-complaint/5	
		//@GetMapping("/get-complaint/{id}")
		//@ResponseBody
		//public Complaint getComplaint(@PathVariable("id") Long complaintId) {
			//Complaint c = complaintService.retrieveComplaint(complaintId);
			//return c;
		//}

		// http://localhost:8080/complaint/add-complaint
		@PostMapping("/add-complaint")
		@ResponseBody
		public Complaint addComplaint(@RequestBody Complaint c) {
			Complaint complaint = complaintService.addComplaint(c);
			return complaint;
		}

		// http://localhost:8080/complaint/update-complaint/5
		@PutMapping("update-complaint/{id}")
		@ResponseBody
		public Complaint updateComplaint(@PathVariable("id") Long id, @RequestBody Complaint c) {
			Complaint complaint = complaintService.updateComplaint(c);
			return complaint;
		}

		//http://localhost:8080/complaint/delete-complaint/7
		@DeleteMapping("delete-complaint/{id}")
		@ResponseBody
		public void deleteComplaint(@PathVariable("id") Long complaintId) {
			complaintService.deleteComplaint(complaintId);
		}

	}

	
	