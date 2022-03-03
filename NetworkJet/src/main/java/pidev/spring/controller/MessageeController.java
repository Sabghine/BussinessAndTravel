package pidev.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pidev.spring.entities.MessageEntity;
import pidev.spring.entities.MessageRecorderEntity;
import pidev.spring.entities.User;
import pidev.spring.service.IUser;
import pidev.spring.service.MessageRecoderService;

@RestController
@CrossOrigin
@RequestMapping("/chat")
public class MessageeController {
	
	@Autowired
	private IUser us ;
	@Autowired
	private MessageRecoderService ms ;
	@Autowired
	private SimpMessagingTemplate Simpmsgtemp ;
	
	@MessageMapping("/chat/{to}")
	@ResponseBody
    public void sendMessage(@DestinationVariable String to, MessageEntity message) {
		
		User destination = us.findByUserName(to);
		User sender = us.findByUserName(message.getFromLogin());
		
		int control = 0;
		
        if (destination.getId() != null) 
        {
        	try {
        		Boolean blockControl = us.blockControl(destination.getUserName(), sender.getUserName());
        		if(blockControl.equals(Boolean.TRUE)) {
        			control=1;
        			throw new Exception("You can not sent message " + to);
        		}
        	}
        	catch(Exception e)
        	{
        		if(control==0)
        		{
        			Simpmsgtemp.convertAndSend("/topic/messages/" + to, message);
        			ms.save(message.getFromLogin(), destination.getUserName(), message.getMessage());
        		}
        		control=1;
        	}
        	if(control==0)
        	{
        		Simpmsgtemp.convertAndSend("/topic/messages/" + to, message);
    			ms.save(message.getFromLogin(), destination.getUserName(), message.getMessage());
        	}
        	
            
        }
    }
	
	@GetMapping("/mymessages")
	
	public List<MessageRecorderEntity> getMyMessages(@RequestParam String receiverName) throws Exception
	{
		User user = us.findByUserName(receiverName);
		
		if(user.getId() == null) 
		{
			throw new Exception("There is no user with this " + user.getUserName() + "user name!");
		}
		
		List<MessageRecorderEntity> response = ms.findAllByReceiverName(receiverName);
		
		return response;
		
	}

}
