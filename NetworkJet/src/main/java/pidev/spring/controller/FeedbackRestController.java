package pidev.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("feedback")
public class FeedbackRestController {
	@Autowired
	IFeedback feedbackService;
	
	
	//http://localhost:8080/feedback/get/5	
			@GetMapping("/get/{id}")
			@ResponseBody
			public Feedback getFeedback(@PathVariable("id") Long feedbackId) {
			return feedbackService.retrieveFeedback(feedbackId);
			
			}

	//http://localhost:8080/feedback/add
	@PostMapping("/add")
	@ResponseBody
	public Feedback addFeedback(@RequestBody Feedback f) {
		return feedbackService.addFeeback(f);
		
	}
	
	// http://localhost:8080/feedback/update/5
			@PutMapping("/update/{id}")
			@ResponseBody
			public Feedback updateFeedback(@PathVariable("id") Long id, @RequestBody Feedback f) {
				return feedbackService.updateFeedback(f, id);
				 
			}
}
