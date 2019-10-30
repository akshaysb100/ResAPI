package com.bridgelabz.fundoo.user.service;

import java.time.LocalDateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoo.exception.UserDoesNotExistException;
import com.bridgelabz.fundoo.exception.VerificationFailedException;
import com.bridgelabz.fundoo.user.dto.ForgetPasswordDTO;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.utility.Response;
import com.bridgelabz.fundoo.utility.TokenUtil;


@PropertySource(name="user",value= {"classpath:response.properties","classpath:userupdate.properties"})
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
	private ModelMapper modelMapper;
    
    @Autowired
    private Environment environment;
    
    @Autowired
	private PasswordEncoder passwordEncode;
    
    @Autowired
    private TokenUtil tokenutil;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
	
	@Override
	public Response register(UserDTO userDto) throws UserDoesNotExistException {
		 
		Optional<User> userCheck = userRepository.findByEmail(userDto.getEmail());

		
	   if(!userCheck.isPresent()) {
			  
	       if(userDto.getPassword().equals(userDto.getReTypePassword())) {
	    	
	    	passwordEncode.encode(userDto.getPassword());
	  
	    	User user = modelMapper.map(userDto,User.class);
	    	user.setRegisteredDate(LocalDateTime.now());
			user.setUpdatedDate(LocalDateTime.now());
			userRepository.save(user);
 
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setTo(userDto.getEmail());
			mailMessage.setFrom("akshaybavalekar100@gmail.com");
			mailMessage.setSubject("valid user check");
			
			String token = tokenutil.createToken(user.getId());
			mailMessage.setText("verification link " + " http://192.168.0.140:8080/users/verrifyUser/" + token);
			javaMailSender.send(mailMessage);
			
	    	return new Response (HttpStatus.BAD_REQUEST.value(),environment.getProperty("status.register.success"));
             
	        }else {
	        	 throw new UserDoesNotExistException(environment.getProperty("status.register.incorrectpassword"));
	          }
	    }else {
	    	     throw new UserDoesNotExistException(environment.getProperty("status.register.already.exists"));

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

	@Override
	public Response verifyUser(String token) throws VerificationFailedException {
		
		Long id = tokenutil.decodeToken(token);
		
		Optional<User> verifyuser = userRepository.findById(id);
		if(verifyuser.isPresent())
		{
				verifyuser.get().setVerify(true);
				userRepository.save(verifyuser.get());
				throw new VerificationFailedException(environment.getProperty("status.login.verifiedSucceed"));

		}else
		return new Response(HttpStatus.ACCEPTED.value(), environment.getProperty("status.login.verificationFailed"));
	}


	@Override
	public Response forgetPassword(ForgetPasswordDTO forgetPassword) throws UserDoesNotExistException {
		Optional<User> checkEmail = userRepository.findByEmail(forgetPassword.getEmail());
        
		if(checkEmail.isPresent()) {
			
			User user = modelMapper.map(forgetPassword,User.class);			
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setTo(forgetPassword.getEmail());
			mailMessage.setFrom("akshaybavalekar100@gmail.com");
			mailMessage.setSubject("valid user check");
			String token = tokenutil.createToken(user.getId());
			mailMessage.setText("verification link " + "http://192.168.0.140:8080/users/verrifyMail/" + token);
			javaMailSender.send(mailMessage);
			
			  throw new UserDoesNotExistException(environment.getProperty("status.user.exists"));
		}else {
			   return new Response(HttpStatus.UNAUTHORIZED.value(),environment.getProperty("status.login.usernotexit"));
		}
	
	}


	@Override
	public Response verifyMail(String token,ForgetPasswordDTO forgetPassword) throws VerificationFailedException {
		
		
		Long id = tokenutil.decodeToken(token);
		
		Optional<User> verifyuser = userRepository.findById(id);
		 	
//			String password = forgetPassword.getPassword();
//			String ConfirmPassword = forgetPassword.getPassword();
		  
		String password = "akshay123";
		String ConfirmPassword = "akshay123";

			System.out.println("password "+password);
			System.out.println("conf "+ConfirmPassword);
			if(password.equals(ConfirmPassword)) {
				System.out.println("hi password");
				 verifyuser.get().setPassword(password);
				 verifyuser.get().setReTypePassword(ConfirmPassword);
				 userRepository.save(verifyuser.get());
				
				 throw new VerificationFailedException(environment.getProperty("status.update.password"));

			 }else {
				return new Response(HttpStatus.ACCEPTED.value(), environment.getProperty("status.register.incorrectpassword"));

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
