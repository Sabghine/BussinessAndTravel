package pidev.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.MessageRecorderEntity;
import pidev.spring.repository.MessageRecoderRepository;

@Service
public class MessageRecoderService implements IMessageRecoder {
	
	@Autowired 
	MessageRecoderRepository mr;

	@Override
	public void save(String sender, String receiver, String messageContent) {

		MessageRecorderEntity messageEntity = new MessageRecorderEntity();
		messageEntity.setSenderName(sender);
		messageEntity.setReceiverName(receiver);
		messageEntity.setMessageContent(messageContent);
		mr.save(messageEntity);
		
	}

	@Override
	public List<MessageRecorderEntity> findAllByReceiverName(String receiverName) {

		List<MessageRecorderEntity> myMessages = mr.findAllByReceiverName(receiverName);
		return myMessages;
	}

}
