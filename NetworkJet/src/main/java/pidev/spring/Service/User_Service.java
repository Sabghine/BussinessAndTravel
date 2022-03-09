package pidev.spring.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pidev.spring.InterfaceService.Interface_User_Service;
import pidev.spring.entities.Domaine;
import pidev.spring.entities.ERole;
import pidev.spring.entities.User;
import pidev.spring.entities.RequestApiForm.MessageResponse;
import pidev.spring.repository.Role_Repository;
import pidev.spring.repository.User_Repository;

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
		userToSave.setActif(true);
		userToSave.setDate(user.getDate());

		return Jpa_User_Repository.save(userToSave);
	}

	@Override
	public void deleteById(Long id) {

		Jpa_User_Repository.deleteById(id);

	}

	@Override
	public Long FindIDUserByEmail(String email) {
		return Jpa_User_Repository.FindIDUserByEmail(email);
	}

	public ResponseEntity<?> StatistiqueSelonDomaine(Long user_id) {
		// TODO Auto-generated method stub
		User user = Jpa_User_Repository.findById(user_id).orElse(null);
		if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {

			float nbruser = Jpa_User_Repository.count();
			String response = "";
		
				float count_sante = (Jpa_User_Repository.countdomaine("sante") / nbruser) * 100;
				float count_commerce = (Jpa_User_Repository.countdomaine("commerce") / nbruser) * 100;
				float count_informatique = (Jpa_User_Repository.countdomaine("informatique") / nbruser) * 100;

				response =  "nbr  all user  " + nbruser + "  les nombres Domaine sante  sont   " + count_sante
						+ "  % " + "    les nombres  Domaine commerce  sont " + count_commerce + " %"
						+ "    les nombres  Domaine informatique  sont " + count_informatique;
			

			return ResponseEntity.ok(new MessageResponse(response));

		}

		return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

	}
	
	
	 public ResponseEntity<?> filter(String filter ,Long user_id) {
		
		User user = Jpa_User_Repository.findById(user_id).orElse(null);
		if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
		List<User> users=Jpa_User_Repository.findByFilter(filter).stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
		
		
		return ResponseEntity.ok(new MessageResponse(users.toString()));
		}

	

	return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));
	}
	 
	 
public List<User> searchMultiCriteria(String name, String email,Domaine domaine,String lastname) {
	
	
	List<User> users=retrieveAllUsers();
	
	if (!name.isEmpty()) {
		users = users.stream().filter(x -> x.getFirstName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList());
	}
	if (!lastname.isEmpty()) {
		users = users.stream().filter(x -> x.getLastName().toLowerCase().contains(lastname.toLowerCase()))
				.collect(Collectors.toList());
	}

	if (email.isEmpty()){
		users =  users.stream().filter(x -> x.getEmail().toLowerCase().contains(email.toLowerCase()))
				.collect(Collectors.toList());
	}
	if (domaine != null){
		users =  users.stream().filter(x -> x.getDomaine().toString().contains(domaine.toString().toLowerCase()))
				.collect(Collectors.toList());
	}

	return users;

}

		

}
