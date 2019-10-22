package com.bridgelabz.fundoo.user.service;

import java.time.LocalDateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoo.exception.UserDoesNotExistException;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.utility.Response;

@PropertySource("classpath:error.properties")
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
	private ModelMapper modelMapper;
    
    @Autowired
    private Environment environment;
	
    
    
	@Override
	public String register(UserDTO userDto) {
		 
		User user = modelMapper.map(userDto,User.class);
		
	    if(userDto.getPassword().equals(userDto.getReTypePassword())) {
	    	
	    	user.setRegisteredDate(LocalDateTime.now());
			user.setUpdatedDate(LocalDateTime.now());
			userRepository.save(user);
	    	return "User Registered!!!!";
	    }
	    else {
	    	return "password and reTypePassword not same!!!";
	    }
		
		
	}

	@Override
	public Response login(LoginDTO loginDTO)  throws UserDoesNotExistException {
		
		
		
		    Optional<User> userCheck = userRepository.findByEmail(loginDTO.getEmail());
		     
		  if(!userCheck.isPresent()) {
			  throw new UserDoesNotExistException(environment.getProperty("status.login.userexist"));
		  }else if(userCheck.get().getPassword().equals(loginDTO.getPassword())) {
//				  userCheck.get().setFirstName("sohan");
//				  userRepository.save(userCheck.get());
				  System.out.println(userCheck.get().getFirstName());
				throw new UserDoesNotExistException(environment.getProperty("status.login.success"));
			}else if(!userCheck.get().getPassword().equals(loginDTO.getPassword())) {
				return new Response(environment.getProperty("status.login.incorrectpassword"),HttpStatus.BAD_REQUEST.value());
			}else {
				return new Response(environment.getProperty("status.login.usernotexit"),HttpStatus.BAD_REQUEST.value());
			}

	}

	@Override
	public String updateUser(LoginDTO loginDTO) {

		Optional<User> checkEmail = userRepository.findByEmail(loginDTO.getEmail());
        
		if(checkEmail.isPresent()) {
			
			if(checkEmail.get().getPassword().equals(loginDTO.getPassword())) {

				  checkEmail.get().setFirstName("rohan");
			      checkEmail.get().setLastName("ombale");
	    		  checkEmail.get().setUpdatedDate(LocalDateTime.now());
			      userRepository.save(checkEmail.get());
				  return "user update usscessfully";
			}else {
				return "incorrect password";
			}
					
		}else {
			return "user not exist";
		}
		
		
	}
	
	
	
//
//	@Override
//	public String forgetPassword(ForgetPasswordDTO forgetPassword) {
//		
//		Optional<User> checkEmail = userRepository.findByEmail(forgetPassword.getEmail());
//		if(checkEmail.isPresent()) {
//			
//			User user = modelMapper.map(forgetPassword,User.class);
//			
//			if(forgetPassword.getPassword().equals(forgetPassword.getReTypePassword())) {
//				user.setPassword(forgetPassword.getPassword());
//				userRepository.save(user);
//			}
//			
//			
//			
//		}else {
//			
//		}
//		
//		return null;
//	}

//	@Override//	public String forgetPassword(LoginDTO loginDTO);
//	
    //public String login(LoginDTO loginDTO) throws Exception;
	

}
