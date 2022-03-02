package pidev.spring.controller;

import java.util.List;
import java.util.Optional;

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

import pidev.spring.entities.Feedback;
import pidev.spring.entities.Message;
import pidev.spring.service.MessageService;



@RestController
@RequestMapping("m")
public class MessageController {
	@Autowired
	MessageService ms;
	
	@GetMapping("/retrieveAll") 
	@ResponseBody 
	public List<Message> retrieveAllMessage() {
		return ms.retrieveAllMessage();
	}

	@PostMapping("/add") 
	@ResponseBody 
	public Message addMessage(@RequestBody Message m) {
		return ms.addMessage(m);
	}

	@DeleteMapping("/delete/{idMessage}") 
	public void deleteMessage(@PathVariable Long idMessage) {
		ms.deleteMessage(idMessage);
		
	}

	@PutMapping("/update/{id}") 
	@ResponseBody 
	public Message updateMessage(@PathVariable("id") Long id, @RequestBody Message m) {
		return ms.updateMessage(m, id);
	}

	@GetMapping("/retrieveBy/{id}") 
	@ResponseBody 
	public Optional<Message> retrieveMessageById(@PathVariable("id") Long id) {
		return ms.retrieveMessageById(id);
	}

	
}
