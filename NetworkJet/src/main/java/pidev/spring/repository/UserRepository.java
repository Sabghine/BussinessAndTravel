package pidev.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pidev.spring.entities.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> { 

}
 

