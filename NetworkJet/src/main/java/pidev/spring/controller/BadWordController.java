package pidev.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pidev.spring.entities.BadWord;
import pidev.spring.service.BadWordService;

@Controller
@RequestMapping("/badword")
public class BadWordController {
	@Autowired 
	private BadWordService badWordService;


	@PostMapping("/addbadword")
    @ResponseBody
    public BadWord addBadWord(@RequestBody BadWord badWord)
    
	{
		return badWordService.addBadWord(badWord);
}
}