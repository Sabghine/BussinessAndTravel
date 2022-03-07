package pidev.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pidev.spring.entities.StatusComplaints;
import pidev.spring.entities.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint ,Long> {
	
	public List<Complaint> findByStatusComplaints(StatusComplaints statusComplaints);
	
}
