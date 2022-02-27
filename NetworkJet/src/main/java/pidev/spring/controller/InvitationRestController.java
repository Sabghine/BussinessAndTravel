package pidev.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pidev.spring.service.InvitationService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class InvitationRestController {
	@Autowired
	InvitationService invitationService;
	@RequestMapping(method = RequestMethod.GET)
	public List<Produit> getAllProduits() {
	return produitService.getAllProduits();

}
