package pidev.spring.service;

import java.util.List;
import java.util.Optional;

import pidev.spring.entities.Message;

public interface IMessageService {
	public List<Message> retrieveAllMessage();
	
	public Message addMessage(Message m);
	
	public void deleteMessage(Long id);
	
	public Message updateMessage(Message m, Long id);
	
	public Optional<Message> retrieveMessageById(Long id);

}
