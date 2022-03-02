package pidev.spring.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pidev.spring.entities.Complaint;
import pidev.spring.entities.StatusComplaints;


@Repository
public interface ComplaintRepository extends JpaRepository<Complaint ,Long> {
	
	public List<Complaint> findByStatusComplaints(StatusComplaints statusComplaints);
	
	
}
