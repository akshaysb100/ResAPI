
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
import com.bridgelabz.fundoo.user.dto.EmailPasswordDTO;
import com.bridgelabz.fundoo.user.dto.ForgetPasswordDTO;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UpdateUserInformationDTO;
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
    
	
	/**
	 *purpose : this function used for Register user information and create token for email verification
	 */
	@Override
	public Response register(UserDTO userDto) throws UserDoesNotExistException {
		 
		Optional<User> userCheck = userRepository.findByEmail(userDto.getEmail());

	   if(!userCheck.isPresent()) {
			  
	       if(userDto.getPassword().equals(userDto.getReTypePassword())) {
	    	
	    	passwordEncode.encode(userDto.getPassword());
	  
	    	User user = modelMapper.map(userDto,User.class);
	    	user.setRegisteredDate(LocalDateTime.now());
			user.setUpdatedDate(LocalDateTime.now());
			user.setTime(System.currentTimeMillis());
			userRepository.save(user);
 
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setTo(userDto.getEmail());
			mailMessage.setFrom("akshaybavalekar100@gmail.com");
			mailMessage.setSubject("valid user check");
			
			String token = tokenutil.createToken(user.getId());
			mailMessage.setText("verification link " + " http://192.168.0.140:8080/users/verrifyUser/" + token);
			javaMailSender.send(mailMessage);
			
	    	return new Response (LocalDateTime.now(),HttpStatus.OK.value(),environment.getProperty("status.register.success"));
             
	        }else {
	        	 throw new UserDoesNotExistException(environment.getProperty("status.register.incorrectpassword"));
	          }
	    }else {
	    	     throw new UserDoesNotExistException(environment.getProperty("status.register.already.exists"));

	    }
	}
		
	
	/**
	 *purpose : this function used for login user
	 */
	@Override
	public Response login(LoginDTO loginDTO)  throws UserDoesNotExistException {
		
		  Optional<User> userCheck = userRepository.findByEmail(loginDTO.getEmail());
		     
		  if(!userCheck.isPresent()) {
			
			  throw new UserDoesNotExistException(environment.getProperty("status.login.userexist"));
		  
		  }else if(userCheck.get().getPassword().equals(loginDTO.getPassword())) {
				
			  throw new UserDoesNotExistException(environment.getProperty("status.login.success"));
			
		  }else if(!userCheck.get().getPassword().equals(loginDTO.getPassword())) {
				
			  return new Response(LocalDateTime.now(),HttpStatus.UNAUTHORIZED.value(),environment.getProperty("status.login.incorrectpassword"));
		  }else {
				
			   return new Response(LocalDateTime.now(),HttpStatus.UNAUTHORIZED.value(),environment.getProperty("status.login.usernotexit"));
			}

	}
	
	
	/**
	 *purpose : this function used for registration verification 
	 */
	@Override
	public Response verifyUser(String token) throws VerificationFailedException {
		
		Long id = tokenutil.decodeToken(token);
		Optional<User> verifyuser = userRepository.findById(id);
		
		long time = ((System.currentTimeMillis()/1000)-(verifyuser.get().getTime()/1000));
		System.out.println("time in second"+time);
		
		if (time<=600) {
			
			if(verifyuser.isPresent())
			{
					verifyuser.get().setVerify(true);
					userRepository.save(verifyuser.get());
					throw new VerificationFailedException(environment.getProperty("status.login.verifiedSucceed"));

			}else {
				return new Response(LocalDateTime.now(),HttpStatus.ACCEPTED.value(), environment.getProperty("status.login.verificationFailed"));
			}
			
		} else {
			 
			 verifyuser.get().setEmail(null);
			 verifyuser.get().setFirstName(null);
//			 verifyuser.get().setId(0);
			 verifyuser.get().setLastName(null);
			 verifyuser.get().setPassword(null);
			 verifyuser.get().setUpdatedDate(null);
			 verifyuser.get().setReTypePassword(null);
			 verifyuser.get().setRegisteredDate(null);
     		 verifyuser.get().setTime(0);
			 verifyuser.get().setVerify(false);
			 userRepository.save(verifyuser.get());
			
			return new Response(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), environment.getProperty("status.token.timeout"));
		}
		
	}


	/**
	 *purpose : this function used for create forget password and and create token for password verification
	 */
	@Override
	public Response forgetPassword(String email) throws UserDoesNotExistException {
		Optional<User> checkEmail = userRepository.findByEmail(email);
        
		if(checkEmail.isPresent()) {
			
			checkEmail.get().setTime(System.currentTimeMillis());
			userRepository.save(checkEmail.get());
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setTo(email);
			mailMessage.setFrom("akshaybavalekar100@gmail.com");
			mailMessage.setSubject("valid user check");
			String token = tokenutil.createToken(checkEmail.get().getId());
			mailMessage.setText("verification link " + "http://192.168.0.140:8080/users/resetPassword/" + token);
			javaMailSender.send(mailMessage);
			
			  throw new UserDoesNotExistException(environment.getProperty("status.user.exists"));
		}else {
			   return new Response(LocalDateTime.now(),HttpStatus.UNAUTHORIZED.value(),environment.getProperty("status.login.usernotexit"));
		}
	
	}


	/**
	 *purpose : this function used password verification
	 */
	@Override
	public Response resetPassword(String token,ForgetPasswordDTO forgetPassword) throws VerificationFailedException {
		
		Long id = tokenutil.decodeToken(token);
		Optional<User> verifyuser = userRepository.findById(id);
		  
		String password = forgetPassword.getPassword();
		String ConfirmPassword = forgetPassword.getConfirmPassword();

		long time = ((System.currentTimeMillis()/1000)-(verifyuser.get().getTime()/1000));	
		
		if (time<=300) {
				
				if(password.equals(ConfirmPassword)) {
					
					 verifyuser.get().setPassword(password);
					 verifyuser.get().setReTypePassword(ConfirmPassword);
					 verifyuser.get().setUpdatedDate(LocalDateTime.now());
					 userRepository.save(verifyuser.get());
					
					 throw new VerificationFailedException(environment.getProperty("status.update.password"));

				 }else {
					return new Response(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), environment.getProperty("status.resetpassword.incorrectpassword"));
				}
				
			} else {
				return new Response(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), environment.getProperty("status.token.timeout"));
			}			
	}



	/**
	 *purpose : this function used to update user information
	 */
	@Override
	public Response updateUser(EmailPasswordDTO emailPasswordDTO) throws UserDoesNotExistException {

		Optional<User> userCheck = userRepository.findByEmail(emailPasswordDTO.getEmail());

		   if(userCheck.isPresent()) {
				  
		       if(emailPasswordDTO.getPassword().equals(userCheck.get().getPassword())) {
		    	
		    	userCheck.get().setTime(System.currentTimeMillis());
				userRepository.save(userCheck.get());
				
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				
				mailMessage.setTo(emailPasswordDTO.getEmail());
				mailMessage.setFrom("akshaybavalekar100@gmail.com");
				mailMessage.setSubject("valid user check");
				
				String token = tokenutil.createToken(userCheck.get().getId());
				mailMessage.setText("verification link " + " http://192.168.0.140:8080/users/resetInformation/" + token);
				javaMailSender.send(mailMessage);
				
		    	return new Response (LocalDateTime.now(),HttpStatus.OK.value(),environment.getProperty("status.Update.process"));
	             
		        }else {
		        	 throw new UserDoesNotExistException(environment.getProperty("status.Update.incorrectpassword"));
		          }
		       
		    }else {
		    	     throw new UserDoesNotExistException(environment.getProperty("status.update.usernotexit"));

		    }
	}


	/**
	 *purpose : this function used email verification
	 */
	@Override
	public Response resetInformation(String token, UpdateUserInformationDTO userInfo) throws VerificationFailedException {
		
		Long id = tokenutil.decodeToken(token);
		Optional<User> verifyuser = userRepository.findById(id);
		
		long time = ((System.currentTimeMillis()/1000)-(verifyuser.get().getTime()/1000));	
		
		if (time<=300) {
				
			 verifyuser.get().setFirstName(userInfo.getFirstName());
			 verifyuser.get().setLastName(userInfo.getLastName());
			 verifyuser.get().setUpdatedDate(LocalDateTime.now());
			 userRepository.save(verifyuser.get());		
			 throw new VerificationFailedException(environment.getProperty("status.update.password"));
					 
			} else {
				return new Response(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), environment.getProperty("status.token.timeout"));
			}		
	}


	@Override
	public Response deleteUser(EmailPasswordDTO emailPasswordDTO) throws UserDoesNotExistException {
		
		Optional<User> userCheck = userRepository.findByEmail(emailPasswordDTO.getEmail());

		   if(userCheck.isPresent()) {
				  
		       if(emailPasswordDTO.getPassword().equals(userCheck.get().getPassword())) {
		    	
		    	userCheck.get().setTime(System.currentTimeMillis());
				userRepository.save(userCheck.get());
				
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				
				mailMessage.setTo(emailPasswordDTO.getEmail());
				mailMessage.setFrom("akshaybavalekar100@gmail.com");
				mailMessage.setSubject("valid user check");
				
				String token = tokenutil.createToken(userCheck.get().getId());
				mailMessage.setText("verification link " + " http://192.168.0.140:8080/users/deleteVerifyUser/" + token);
				javaMailSender.send(mailMessage);
				
		    	return new Response (LocalDateTime.now(),HttpStatus.OK.value(),environment.getProperty("status.delete.process"));
	             
		        }else {
		        	 throw new UserDoesNotExistException(environment.getProperty("status.delete.incorrectpassword"));
		          }
		       
		    }else {
		    	     throw new UserDoesNotExistException(environment.getProperty("status.delete.usernotexit"));

		    }
	}


	@Override
	public Response deleteVerifyUser(String token) throws VerificationFailedException {
		
		Long id = tokenutil.decodeToken(token);
		Optional<User> verifyuser = userRepository.findById(id);
		
		long time = ((System.currentTimeMillis()/1000)-(verifyuser.get().getTime()/1000));
		
		if (time<=600) {
			
			if(verifyuser.isPresent())
			{
					userRepository.deleteById(verifyuser.get().getId());
					throw new VerificationFailedException(environment.getProperty("status.delete.verifiedSucceed"));

			}else {
				return new Response(LocalDateTime.now(),HttpStatus.ACCEPTED.value(), environment.getProperty("status.delete.verificationFailed"));
			}
			
		} else {
			return new Response(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), environment.getProperty("status.token.timeout"));
		}
	}
	
}
