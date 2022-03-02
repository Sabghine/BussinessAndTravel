package pidev.spring.Controller;


import java.io.UnsupportedEncodingException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pidev.spring.Exeption.API_Request_Exception_NotFound;
import pidev.spring.Repository.Role_Repository;
import pidev.spring.Repository.User_Repository;
import pidev.spring.Service.Session_UserDetails;
import pidev.spring.Service.User_Service;
import pidev.spring.entities.ERole;
import pidev.spring.entities.Role;
import pidev.spring.entities.User;
import pidev.spring.entities.RequestApiForm.JwtResponse;
import pidev.spring.entities.RequestApiForm.MessageResponse;
import pidev.spring.entities.RequestApiForm.RequestLogin;
import pidev.spring.entities.RequestApiForm.SignupRequest;
import pidev.spring.security.JwtUtils;
@Slf4j
@SuppressWarnings("ALL")
@CrossOrigin("*")
@RestController

public class User_Controller_Rest_Web_Service {
    @Autowired
    private User_Service I_User_Service;

    @Autowired
    User_Repository Jpa_User_Repository;
    @Autowired
    Role_Repository Jpa_Role_Repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;



    @RequestMapping("/LoggingTest")
    public String index() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");

        return "hi ! Check out the Logs to see the output...";
    }


    /* Authentication API */
    
    @RequestMapping("/Sign-In")
    public ResponseEntity<?> authenticateUser(  @RequestBody RequestLogin loginRequest, HttpServletRequest request,
                                              Authentication authentication ) throws UnsupportedEncodingException {


        if (loginRequest.getEmail()==null || loginRequest.getPassword()==null) {
            return ResponseEntity.badRequest().body(new MessageResponse("All this fiels are required" ));
        }

        Optional<User> userAuth = Jpa_User_Repository.findByEmailIgnoreCase(loginRequest.getEmail());
        
        if (userAuth.isPresent() == false) {
            return ResponseEntity.badRequest().body(new MessageResponse(
                    "Error: Couldn’t find account with this email  during Sign-In OR this mail may be NOT VALID!"));
        }

    

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // generate Jwt  Token
            String jwt = jwtUtils.generateJwtToken(authentication);
            
            
            log.info("token is :" + jwt);
            Session_UserDetails userDetails = (Session_UserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            try {
                Jpa_User_Repository.updateUserTimeLoggedIn(userDetails.getId(), new Date());

                Jpa_User_Repository.updateUserSessionId(request.getSession().getId(), userDetails.getId());
                log.info(
                        "Session reference is : " + request.getSession().getId() + " Id User : " + userDetails.getId());
                log.info("Time_Logged_In has been updated for user with First name : " + userDetails.getUsername());
            } catch (Exception e) {
                log.error("Something is wrong time logged in not updated " + e.getMessage());
            }

            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                    userDetails.getEmail(), roles));
        }
        catch (BadCredentialsException e) { 
        	return ResponseEntity.badRequest()
                    .body(new MessageResponse("Please try to login again."));
        }
    }

   

   
    /* REGISTRATION */
    @RequestMapping("/Sign-Up")
    public ResponseEntity<?> add_user(@Valid @RequestBody SignupRequest signUpRequest) {

        if (Jpa_User_Repository.existsByFirstName(signUpRequest.getFirstName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (Jpa_User_Repository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getEmail(), signUpRequest.getFirstName(),
                signUpRequest.getLastName(), false, signUpRequest.getDate(),
                passwordEncoder.encode(signUpRequest.getPassword()));
        
        Set<Role> roles = new HashSet<>();

            Role userRole = Jpa_Role_Repository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
    
        user.setActif(true);
        user.setRole(roles);
        user.setAccountNonLocked(true);
        user.setDate(signUpRequest.getDate());
        user.setCreatedTime(new Date());
        Jpa_User_Repository.save(user);
        /*yetb3ath email*/
  
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    
    /* END REGISTRATION */
    /* Logged out API */
    @PostMapping("/log-out")
    public ResponseEntity<?> logout(HttpSession session, Authentication auth, HttpServletRequest request) {
        log.info("Before logout");
    
        session.invalidate();
        SecurityContextHolder.getContext().setAuthentication(auth);
        Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        Jpa_User_Repository.updateUserTimeLoggedOut(userDetails.getId(), new Date());
        log.info("time logged out has been updated with user id : " + userDetails.getId());
        return ResponseEntity.ok(new MessageResponse("session logged out"));
    }



    /* Get all the users */
    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("/retrieve-all-users")
    public List<User> getUsers() {
        List<User> list = I_User_Service.retrieveAllUsers();
        return list;
    }

    /* Get user by his identify */
    @GetMapping("/retrieve-user/{user-id}")
    public Optional<User> retrieveUserById(@PathVariable("user-id") Long userId, HttpServletRequest request,
                                               HttpServletResponse response) throws UserPrincipalNotFoundException {
        if (!Jpa_User_Repository.existsById(userId)) {
            throw new API_Request_Exception_NotFound("User not found with this Identify : " + userId);
        }
        return I_User_Service.findById(userId);
    }

    /*
     * Delete user by identify Only user with role ADMIN is authorized to use
     * this API
     */
    @Secured(value = {"ROLE_ADMIN"})
    @DeleteMapping("/remove-user/{user-id}")
    public void removeUser(@PathVariable("user-id") Long userId) {

        if (!Jpa_User_Repository.existsById(userId)) {
            throw new API_Request_Exception_NotFound("User not found with this Identify : " + userId);
        }
        Jpa_User_Repository.deleteById(userId);
    }

    /* update account Only user with role ADMIN is authorized to use this API */
    @Secured(value = {"ROLE_ADMIN"})
    @PutMapping("/updateUser/{user-id}")
    public Object updateUser(@RequestBody User user, @PathVariable("user-id") Long id,
                             BindingResult bindingResult) {
        User userToUpdate = Jpa_User_Repository.findById(id).orElse(null);

        String lastName = user.getLastName();
        userToUpdate.setLastName(lastName);
        String firstname = user.getFirstName();
        userToUpdate.setFirstName(firstname);
        Date date = user.getDate();
        userToUpdate.setDate(date);
        boolean actif = user.isActif();
        userToUpdate.setActif(actif);
        if (user.getRole() == null) {
            userToUpdate.setRole(userToUpdate.getRole());
        }
        Map<String, Object> Errors = new HashMap<>();


        Optional<User> existingUserWithFirstName = Jpa_User_Repository
                .findByFirstNameIgnoreCase(user.getFirstName());
        Optional<User> existingUserWithLastName = Jpa_User_Repository
                .findByLastNameIgnoreCase(user.getLastName());

        if (existingUserWithFirstName.isPresent()) {

            Errors.put("Errors : this First Name is already exist please choose another one !", true);
            for (FieldError fe : bindingResult.getFieldErrors()) {
                Errors.put(fe.getField(), fe.getDefaultMessage());
            }
            return Errors;
        } else if (existingUserWithLastName.isPresent()) {

            Errors.put("Errors : this Last Name is already exist please choose another one !", true);
            for (FieldError fe : bindingResult.getFieldErrors()) {
                Errors.put(fe.getField(), fe.getDefaultMessage());
            }
            return Errors;
        }

        return Jpa_User_Repository.save(userToUpdate);

    }
  /*  @PutMapping("/ajouterVoyage")
 void ajouter_voyage(Voyage v,Authentication auth)
    {
        Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
        
        
        User u=Jpa_User_Repository.getById(userDetails.getId());
        
        v.setUser(u);
        
    	
    }*/
    
    
    
    
    
    

    
    
    


}
