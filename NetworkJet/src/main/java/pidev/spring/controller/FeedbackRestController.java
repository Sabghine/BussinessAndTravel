package pidev.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

	
	@PostMapping("/add")
	@ResponseBody
	public Feedback addFeedback(@RequestBody Feedback f) {
		return feedbackService.addFeeback(f);
		
	}
}
