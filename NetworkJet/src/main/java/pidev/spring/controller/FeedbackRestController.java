package pidev.spring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pidev.spring.entities.Feedback;
import pidev.spring.service.IFeedback;

@RestController
public class FeedbackRestController {
	IFeedback feedbackService;

	
	@PostMapping("/add-feedback")
	@ResponseBody
	public Feedback addComplaint(@RequestBody Feedback f) {
		Feedback feedback = feedbackService.addFeeback(f);
		return feedback;
	}
}
