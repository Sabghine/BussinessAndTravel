package pidev.spring.InterfaceService;

import java.util.List;
import java.util.Optional;

import pidev.spring.entities.User;



public interface Interface_User_Service {

	User addUser(User userToAdd);

	List<User> retrieveAllUsers();

	User saveOrUpdate(User user);

	Optional<User> findById(Long id);

	String deleteById(Long id);

	Long FindIDUserByEmail(String email);


}
