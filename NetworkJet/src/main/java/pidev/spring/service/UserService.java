package pidev.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.BlockUserEntity;
import pidev.spring.entities.User;
import pidev.spring.repository.BlockUserRepository;

import pidev.spring.repository.UserRepository;

@Service
public class UserService implements IUser {
	
	@Autowired 
	UserRepository ur;
	BlockUserRepository br;
	
	@Autowired
	public UserService(UserRepository ur, BlockUserRepository br) {
	
		this.ur = ur;
		this.br = br;
	}
	
	

	@Override
	public Boolean block(String angryName, String blockedName) {

		User angry = ur.findByUserName(angryName);
		User blocked = ur.findByUserName(blockedName);
		
		if(angry.getId() != null && blocked.getId() != null)
		{
			
			BlockUserEntity blockEntity = new BlockUserEntity();
			
			blockEntity.setAngryId(angry.getId());
			blockEntity.setBlockedId(blocked.getId());
			
			br.save(blockEntity);
			
			return true;
		}	
		return false;
	}

	@Override
	public Boolean unblock(String angryName, String blockedName) {
		User angry = ur.findByUserName(angryName);
		User blocked = ur.findByUserName(blockedName);
		
		if(angry.getId() != null && blocked.getId()!= null) 
		{
			try {
				br.unblock(angry.getId(), blocked.getId());
		} 
			catch(Exception e)
			{
				return false;
			}
		}
		
		return true;
	}

	@Override
	public Boolean blockControl(String angryName, String blockedName) {

		User angry = ur.findByUserName(angryName);
		User blocked = ur.findByUserName(blockedName);
		
		List<BlockUserEntity> listOfBlock = br.findAllByAngryId(angry.getId());
		
		ArrayList<Long> blockedIds = new ArrayList<Long>();
		
		int loop = listOfBlock.size();
		
		for(int flag = 0; flag<loop;flag++)
		{
			blockedIds.add(listOfBlock.get(flag).getBlockedId());
		}
		
		if(blockedIds.contains(blocked.getId())) 
		{
			return true;
		}
		
		return false;
	}



	@Override
	public User findByUserName(String userName) {
		User user = ur.findByUserName(userName);
		return user;
	}

}
