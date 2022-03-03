package pidev.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pidev.spring.entities.User;



public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String name);
	
}
