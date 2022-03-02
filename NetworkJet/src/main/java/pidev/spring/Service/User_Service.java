package pidev.spring.Service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pidev.spring.InterfaceService.Interface_User_Service;
import pidev.spring.Repository.Role_Repository;
import pidev.spring.Repository.User_Repository;
import pidev.spring.entities.User;
@Slf4j
@Service("I_User_Service")
@Transactional
public class User_Service implements Interface_User_Service {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private User_Repository Jpa_User_Repository;
	@Autowired
	Role_Repository Jpa_Role_Repository;

	



	

	@Override
	public User addUser(User userToAdd) {

		return Jpa_User_Repository.save(userToAdd);
	}

	@Override
	public List<User> retrieveAllUsers() {
		return (List<User>) Jpa_User_Repository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return Jpa_User_Repository.findById(id);
	}

	@Override
	public User saveOrUpdate(User user) {
		User userToSave = new User();

		userToSave.setFirstName(user.getFirstName());
		userToSave.setLastName(user.getLastName());
		userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
		userToSave.setEmail(user.getEmail());
		userToSave.setActif(false);
		userToSave.setDate(user.getDate());

		return Jpa_User_Repository.save(userToSave);
	}

	@Override
	public String deleteById(Long id) {

		JSONObject jsonObject = new JSONObject();
		try {
			Jpa_User_Repository.deleteById(id);
			jsonObject.put("message", "User deleted successfully ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}


	@Override
	public Long FindIDUserByEmail(String email) {
		return Jpa_User_Repository.FindIDUserByEmail(email);
	}

}
