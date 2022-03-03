package pidev.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pidev.spring.entities.MessageRecorderEntity;

@Repository
public interface MessageRecoderRepository extends JpaRepository<MessageRecorderEntity , Long>{
	
	List<MessageRecorderEntity> findAllByReceiverName(String receiverName);

}
