package pidev.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pidev.spring.entities.Complaint;


public interface ComplaintRepository extends JpaRepository<Complaint ,Long> {
	
	public Complaint save(Complaint c);

	//public Complaint findByIdComplaint(Long id);

	//public void deleteByComplaintId(Long id);

}
