package com.bridgelabz.fundoo.user.service;

import com.bridgelabz.fundoo.exception.UserDoesNotExistException;
import com.bridgelabz.fundoo.utility.Response;
import com.bridgelabz.fundoo.user.dto.ForgetPasswordDTO;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.model.User;


public interface UserService {

public Response register(UserDTO userDto);
public Response login(LoginDTO loginDTO) throws UserDoesNotExistException ;
//public String forgetPassword(ForgetPasswordDTO forgetPassword);
public String updateUser(LoginDTO loginDTO);


	
}
