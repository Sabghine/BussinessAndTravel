package pidev.spring.ExternService;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import pidev.spring.Repository.User_Repository;
import pidev.spring.Service.User_Service;
import pidev.spring.entities.User;



@Component
public class OAuth2_Login_Success_Handler extends SimpleUrlAuthenticationSuccessHandler    {
	@Autowired
	User_Service CustomerService;
	@Autowired
	User_Repository CustomerRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Custom_Oauth2_User OauthUser = (Custom_Oauth2_User) authentication.getPrincipal();
		 
		String email = OauthUser.getEmail();
		String name = OauthUser.getName();
		Date birthDate = OauthUser.getBirthdate();
		String session = request.getSession().getId();
		
		Date now = new Date();
		User Customer = CustomerRepository.findByEmail(email);
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
