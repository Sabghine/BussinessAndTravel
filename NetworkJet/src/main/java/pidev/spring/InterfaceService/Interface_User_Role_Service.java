package pidev.spring.InterfaceService;

import java.util.Collection;
import java.util.Optional;

import pidev.spring.entities.Role;
 
public interface Interface_User_Role_Service {

	 Role addRole(Role roleToAdd);
		
	 Collection<Role> retrieveAllRole();

	 Role saveOrUpdate(Role role);

	 Optional<Role> findById(Long id);

	 String deleteById(Long id);
}
