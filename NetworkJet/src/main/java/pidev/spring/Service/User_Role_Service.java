package pidev.spring.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.InterfaceService.Interface_User_Role_Service;
import pidev.spring.entities.Role;
import pidev.spring.repository.Role_Repository;
 
 

@Service
public class User_Role_Service implements Interface_User_Role_Service{
	
	
	@Autowired
	 Role_Repository role_Repository;

	@Override
	public Role addRole(Role roleToAdd) {
		 
		return role_Repository.save(roleToAdd);
	}

	@Override
	public Collection<Role> retrieveAllRole() {
		 
		return role_Repository.findAll();
	}

	@Override
	public Role saveOrUpdate(Role role) {
		
		return role_Repository.saveAndFlush(role);
	}

 

	@Override
	public void deleteById(Long id) {
			
	
		 role_Repository.deleteById(id);
	}

	@Override
	public Optional<Role> findById(Long id) {
		return role_Repository.findById(id);
	}
}
