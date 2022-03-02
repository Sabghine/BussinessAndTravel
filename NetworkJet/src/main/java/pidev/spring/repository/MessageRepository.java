package pidev.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pidev.spring.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message , Long> {

}
