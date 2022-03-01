package pidev.spring.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pidev.spring.entities.Complaint;


@Repository
public interface ComplaintRepository extends JpaRepository<Complaint ,Long> {
	
	//@Query("SELECT c FROM Appointment c WHERE c.statut=:grave ")
	//List<Complaint> retrieveAllComplaintsByStatut(@Param("grave")String State);
	
}
