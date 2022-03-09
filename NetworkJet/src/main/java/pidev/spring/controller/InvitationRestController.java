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

import pidev.spring.entities.Invitation;
import pidev.spring.service.InvitationService;

@RestController
@RequestMapping("/api/invitation")
@CrossOrigin
public class InvitationRestController {
		
	@Autowired
	private InvitationService invitationService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Invitation> getAllInvitations() {
	return invitationService.getAllInvitations();
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
	
	@RequestMapping(value="/Statistics",method = RequestMethod.GET)
	public String statistiques()
	{
		String msg="Taux de Reussite des invitations : "+invitationService.tauxDeReussite()+"%";
		
		List<Invitation> listaccepter = invitationService.getAllByStatus(null);
		List<Invitation> listEnAttente = invitationService.getAllNonAccepted();
		msg=msg+"\nListe des invitation accepter :"+listaccepter.size()+" invitations \n";
		for(int i = 0;i<listaccepter.size();i++)
		{
			msg=msg+"\n--------------------------------------------------";
			msg=msg+"\nId :"+listaccepter.get(i).getIdInvitation();
			msg=msg+"\nemail : "+listaccepter.get(i).getEmail();
			msg=msg+"\nStatus : Accepted";
		
		}
		msg=msg+"\n--------------------------------------------------";
		msg=msg+"\n--------------------------------------------------";
		msg=msg+"\nListe des invitation non accepter encore :"+listEnAttente.size()+" invitations \n";
		for(int i = 0;i<listEnAttente.size();i++)
		{
			msg=msg+"\n--------------------------------------------------";
			msg=msg+"\nemail : "+listEnAttente.get(i).getEmail();
			msg=msg+"\nDate d'expiration : "+listEnAttente.get(i).getDate_expiration();
		}
		
		
		return msg;
		
	}



}
