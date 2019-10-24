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
import com.bridgelabz.fundoo.utility.ErrorResponse;
import com.bridgelabz.fundoo.utility.Response;

@PropertySource(name="user",value= {"classpath:response.properties","classpath:userupdate.properties"})
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
	private ModelMapper modelMapper;
    
    @Autowired
    private Environment environment;
	
	@Override
	public Response register(UserDTO userDto) throws UserDoesNotExistException {
		 
		Optional<User> userCheck = userRepository.findByEmail(userDto.getEmail());

		User user = modelMapper.map(userDto,User.class);
		  if(!userCheck.isPresent()) {
	    if(userDto.getPassword().equals(userDto.getReTypePassword())) {
	    	
	    	user.setRegisteredDate(LocalDateTime.now());
			user.setUpdatedDate(LocalDateTime.now());
			userRepository.save(user);

	    	return new Response (HttpStatus.BAD_REQUEST.value(),environment.getProperty("status.register.success"));
              }
	    else {
	    	throw new UserDoesNotExistException(environment.getProperty("status.register.incorrectpassword"));
	    }
	    }else {
	    	throw new UserDoesNotExistException(environment.getProperty("status.register.ol"));

	    }
	}
		
	
	@Override
	public Response login(LoginDTO loginDTO)  throws UserDoesNotExistException {
		
		  Optional<User> userCheck = userRepository.findByEmail(loginDTO.getEmail());
		     
		  if(!userCheck.isPresent()) {
			  throw new UserDoesNotExistException(environment.getProperty("status.login.userexist"));
		  }else if(userCheck.get().getPassword().equals(loginDTO.getPassword())) {
				throw new UserDoesNotExistException(environment.getProperty("status.login.success"));
			}else if(!userCheck.get().getPassword().equals(loginDTO.getPassword())) {
				return new Response(HttpStatus.UNAUTHORIZED.value(),environment.getProperty("status.login.incorrectpassword"));
			}else {
				return new Response(HttpStatus.UNAUTHORIZED.value(),environment.getProperty("status.login.usernotexit"));
			}

	}

	@Override
	public String updateUser(LoginDTO loginDTO) {

		Optional<User> checkEmail = userRepository.findByEmail(loginDTO.getEmail());
        
		if(checkEmail.isPresent()) {
			
			if(checkEmail.get().getPassword().equals(loginDTO.getPassword())) {

				  checkEmail.get().setFirstName(environment.getProperty("user.firstname"));
			      checkEmail.get().setLastName(environment.getProperty("user.lastname"));
	    		  checkEmail.get().setUpdatedDate(LocalDateTime.now());
			      userRepository.save(checkEmail.get());
				  return "user update usscessfully";
			}else {
				return "incorrect password";
			}
					
		}else {
			return "user deos't exist";
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
