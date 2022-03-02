package pidev.spring.controller;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pidev.spring.entities.Complaint;
import pidev.spring.entities.Invitation;
import pidev.spring.service.IComplaint;
import pidev.spring.service.InvitationService;

@RestController
@RequestMapping("/api/invitation")
@CrossOrigin
public class InvitationRestController {
		
	@Autowired
	private InvitationService invitationService;
	
	
	@GetMapping("/getall")
	@ResponseBody
	ResponseEntity <List<Invitation>> getAllInvitations() {
	return new ResponseEntity(invitationService.getAllInvitations(),HttpStatus.OK);
	}

	
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Invitation getInvitationById(@PathVariable("id") Long id) {
		
	return invitationService.getInvitation(id);
	 }
	
	@RequestMapping(method = RequestMethod.POST)
	public Invitation sendInvitation(@RequestBody Invitation invitation) {
		SecureRandom random = new SecureRandom();
		 byte bytes[] = new byte[20];
		 random.nextBytes(bytes);
		 String token = bytes.toString();
		 invitation.setToken(token);
	return invitationService.saveInvitation(invitation);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Invitation updateInvitation(@RequestBody Invitation invitation) {
	return invitationService.updateInvitation(invitation);
	}



}
