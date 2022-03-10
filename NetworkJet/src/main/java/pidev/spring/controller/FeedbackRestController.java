package pidev.spring.controller;

import java.util.List;

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

import pidev.spring.entities.Feedback;
import pidev.spring.service.IFeedback;
import pidev.spring.service.Session_UserDetails;

@RestController
@RequestMapping("feedback")
public class FeedbackRestController {
	
	@Autowired
	IFeedback feedbackService;
		
		
		// http://localhost:8080/feedback/retrieve-all
		@GetMapping("/retrieve-all")
		@ResponseBody
		public List<Feedback> getFeedbacks() {
			return feedbackService.retrieveAllFeedbacks(); }
		
		
		//http://localhost:8080/feedback/get/5	
		@GetMapping("/get/{id}")
		@ResponseBody
		public Feedback getFeedback(@PathVariable("id") Long feedbackId) {
			return feedbackService.retrieveFeedback(feedbackId);}


		//http://localhost:8080/feedback/add
		@PostMapping("/add")
		@ResponseBody
		public Feedback addFeedback(Authentication auth,@RequestBody Feedback f) {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return feedbackService.addFeeback(f,userDetails.getId());
			
		}
		
		// http://localhost:8080/feedback/update/5
				@PutMapping("/update/{id}")
				@ResponseBody
				public Feedback updateFeedback(Authentication auth,@PathVariable("id") Long id, @RequestBody Feedback f) {
					SecurityContextHolder.getContext().setAuthentication(auth);
					Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
					
					return feedbackService.updateFeedback(f,id,userDetails.getId());
					 
				}
				
				//http://localhost:8080/feedback/delete/1
				@DeleteMapping("/delete/{id}")
				@ResponseBody
				public void deleteFeedback(@PathVariable("id") Long feedbackId) {
					feedbackService.deleteFeedback(feedbackId);
				}
	}

