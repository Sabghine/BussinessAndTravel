package pidev.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import pidev.spring.Service.Session_UserDetails;
import pidev.spring.Service.User_Service;
import pidev.spring.entities.User;
@Component
public class CustomLoginSuccessHandler  extends SimpleUrlAuthenticationSuccessHandler{
	@Autowired
	private User_Service I_User_Service;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		 Session_UserDetails userDetails =  (Session_UserDetails) authentication.getPrincipal();
	        User user = userDetails.getUser();	
	   
	        super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
