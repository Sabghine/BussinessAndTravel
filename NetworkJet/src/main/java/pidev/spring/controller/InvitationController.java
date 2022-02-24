package pidev.spring.controller;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pidev.spring.entities.Invitation;
import pidev.spring.service.InvitationService;

@Controller
public class InvitationController {
	
	@Autowired
	InvitationService invitationService;
	
	@RequestMapping("/sendInvitation")
	public String sendInvitation()
	{
	return "sendInvitation";
	}

	@RequestMapping("/saveInvitation")
	public String saveInvitation(@ModelAttribute("invitation") Invitation invitation,
	 @RequestParam("date") String date,
	 ModelMap modelMap) throws
	ParseException
	{
	//conversion de la date
	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 Date date_expiration = dateformat.parse(String.valueOf(date));
	 invitation.setDate_expiration(date_expiration);
	 SecureRandom random = new SecureRandom();
	 byte bytes[] = new byte[20];
	 random.nextBytes(bytes);
	 String token = bytes.toString();
	 invitation.setToken(token);
	Invitation saveInvitation = invitationService.saveInvitation(invitation);
	String msg ="invitation envoyée avec Id "+saveInvitation.getIdInvitation();
	modelMap.addAttribute("msg", msg);
	return "sendInvitation";
	}
	
	@RequestMapping("/ListeInvitations")
	public String listeInvitations(ModelMap modelMap)
	{
	List<Invitation> invits = invitationService.getAllInvitations();
	modelMap.addAttribute("invitations", invits);
	return "listeInvitations";
	}
	
	@RequestMapping("/supprimerInvitation")
	public String supprimerInvitation(@RequestParam("id") Long id,
	 ModelMap modelMap)
	{
	invitationService.deleteInvitationById(id);
	List<Invitation> invits = invitationService.getAllInvitations();
	modelMap.addAttribute("invitations", invits);
	return "listeInvitations";
	}
	
	@RequestMapping("/modifierInvitation")
	public String editerInvitation(@RequestParam("id") Long id,ModelMap modelMap)
	{
		Invitation i= invitationService.getInvitation(id);
	modelMap.addAttribute("invitation", i);
	return "editerInvitation";
	}
	@RequestMapping("/updateInvitation")
	public String updateInvitation(@ModelAttribute("invitation") Invitation invitation,
	@RequestParam("date") String date, ModelMap modelMap) throws ParseException 
	{
	//conversion de la date
	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 Date date_expiration= dateformat.parse(String.valueOf(date));
	 invitation.setDate_expiration(date_expiration);

	 invitationService.updateInvitation(invitation);
	 List<Invitation> invits = invitationService.getAllInvitations();
	 modelMap.addAttribute("invitations", invits);
	return "listeInvitations";
}

}
