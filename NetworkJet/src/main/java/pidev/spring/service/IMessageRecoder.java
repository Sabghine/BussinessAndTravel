package pidev.spring.service;

import java.util.List;

import pidev.spring.entities.MessageRecorderEntity;



public interface IMessageRecoder {

	void save(String sender, String receiver, String messageContent);
	
	List<MessageRecorderEntity> findAllByReceiverName(String receiverName);

}
