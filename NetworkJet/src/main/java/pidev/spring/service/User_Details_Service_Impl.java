package pidev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pidev.spring.entities.User;
import pidev.spring.repository.User_Repository;

@Service
public class User_Details_Service_Impl implements UserDetailsService {


	@Autowired
	private User_Repository userRepository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user with this Email " + email);
		}
		return Session_UserDetails.build(user);
	}



}
