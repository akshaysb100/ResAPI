package com.bridgelabz.fundoo.userservice;

import com.bridgelabz.fundoo.userdto.LoginDTO;
import com.bridgelabz.fundoo.userdto.UserDTO;


public interface UserService {
	

public String register(UserDTO userDto);
public String login(LoginDTO loginDTO);
public String failed(String value);
//	
//	public String forgetPassword(LoginDTO loginDTO);
//	
       //public String login(LoginDTO loginDTO) throws Exception;
	
}
