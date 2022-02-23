package pidev.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import pidev.spring.Repository.User_Repository;
import pidev.spring.Service.User_Service;
import pidev.spring.entities.User;
@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Autowired
	private User_Service I_User_Service;
	 
	@Autowired
	User_Repository Jpa_User_Repository;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String email = request.getParameter("email");
		User user = Jpa_User_Repository.findByEmail(email);

		
		super.onAuthenticationFailure(request, response, exception);
	}
}
