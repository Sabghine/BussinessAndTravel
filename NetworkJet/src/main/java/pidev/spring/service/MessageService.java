package pidev.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Message;
import pidev.spring.repository.MessageRepository;

@Service
public class MessageService implements IMessageService{
	@Autowired 
	MessageRepository mr;

	@Override
	public List<Message> retrieveAllMessage() {
		return (List<Message>) mr.findAll();
	}

	@Override
	public Message addMessage(Message m) {
		return mr.save(m);
	}

	@Override
	public void deleteMessage(Long id) {
		mr.deleteById(id);
		
	}

	@Override
	public Message updateMessage(Message m, Long id) {
		m.setId(id);
		return mr.save(m);
	}

	@Override
	public Optional<Message> retrieveMessageById(Long id) {
		return mr.findById(id);
	}
	

}
