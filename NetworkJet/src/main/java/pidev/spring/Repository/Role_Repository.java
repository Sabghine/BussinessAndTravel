package pidev.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pidev.spring.entities.ERole;
import pidev.spring.entities.Role;

@Repository
public interface Role_Repository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(ERole name);

	Role findByName(String nameRole);
}
