package com.bridgelabz.fundoo.userservice;

import java.time.LocalDateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.userdto.LoginDTO;
import com.bridgelabz.fundoo.userdto.UserDTO;
import com.bridgelabz.fundoo.usermodel.User;
import com.bridgelabz.fundoo.userrepository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
	private ModelMapper modelMapper;
	
	@Override
	public String register(UserDTO userDto) {
		 
		User user = modelMapper.map(userDto,User.class);
		
	    if(userDto.getPassword().equals(userDto.getReTypePassword())) {
	    	
	    	user.setRegisteredDate(LocalDateTime.now());
			user.setUpdatedDate(LocalDateTime.now());
			userRepository.save(user);
	    	return "User Registered!!!!";
	    }
	    else if(user.getRegisteredDate()!=null){
			return "failed registration!!!";
		}
	    else {
	    	return "failed registration!!!";
	    }
		
		
	}

	@Override
	public String login(LoginDTO loginDTO) {
		
		try {
		
			User userCheck = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new Exception("invalid user name"));
		   
			if(userCheck.getPassword().equals(loginDTO.getPassword())) {
			       return "login successful";
			}else if(!userCheck.getPassword().equals(loginDTO.getPassword())) {
				return "Invalid password";
			}else {
			  return "user does't exit";	
			}
			
		
		} catch (Exception e) {
		 	
		System.out.println("failed process");
		}
		
		return "null";
	}

	@Override
	public String failed(String value) {
		System.out.println("registation is failed"+value);
		return value;
	}
	
}
