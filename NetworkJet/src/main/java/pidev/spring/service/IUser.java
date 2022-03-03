package pidev.spring.service;

import pidev.spring.entities.User;

public interface IUser {
	
	Boolean block(String angry, String blocked);
	
	Boolean unblock(String angry, String blocked);
	
	Boolean blockControl(String angry, String blocked);
	
	User findByUserName(String userName);

}
