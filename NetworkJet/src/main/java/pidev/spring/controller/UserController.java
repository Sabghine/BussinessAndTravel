package pidev.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pidev.spring.service.IUser;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private  IUser us;
	
	
	
	
	
	
	
	@GetMapping("/blockUser")
	public ResponseEntity<Void> block(@RequestParam String angryUser, @RequestParam String blockedUser) throws Exception
	{
		us.block(angryUser, blockedUser);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/unblockUser")
	public ResponseEntity<Void> unblock(@RequestParam String angryUser, @RequestParam String blockedUser) {
		Boolean b = us.unblock(angryUser, blockedUser);
		if(b.equals(Boolean.TRUE)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}
