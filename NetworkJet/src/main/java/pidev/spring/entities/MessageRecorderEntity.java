package pidev.spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity(name = "messagesrecords")
@Data
public class MessageRecorderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	String senderName;
	
	@NotNull
	String receiverName;
	
	@NotNull
	String messageContent;

}
