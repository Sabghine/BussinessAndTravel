package pidev.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pidev.spring.entities.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

	List<Invitation> findByToken(String token);
	
}
