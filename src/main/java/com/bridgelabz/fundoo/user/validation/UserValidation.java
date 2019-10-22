package com.bridgelabz.fundoo.user.validation;
import java.util.regex.Pattern;
public class UserValidation {


	public static String checkValue(String firstName) {
		//,String lastName,String mailId,String password,String reTypePassword
		String value="";
		
		boolean firstName1 = (Pattern.matches("\\D*",firstName));
		if(firstName1==false ) {
			//System.out.println("character accept only");
			value ="character accept only";
			return value;
		}
//		
//		boolean lastNmae1 = (Pattern.matches("\\D*",lastName));
//		if(lastNmae1==false) {
//			//System.out.println("character accept only");
//			value = "character accept only";
//			return value;
//		}else if (firstName.equals(null)) {
//			System.out.println("last name is not null");
//		}
//		
////		boolean mobileNumber1 = Pattern.matches("[789]{1}\\d{9}", mobileNumber);
////		if(mobileNumber1==false) {
////			//System.out.println("only accept 10 digit mobile number");
////			value = "only accept 10 digit mobile number";
////			return value;
////		}
//		
//		boolean mailId1 = (Pattern.matches("[a-z0-9@]*", mailId));
//		if(mailId1==false) {
//	
//			value = "mail id is not accepted";
//			return value;
//		}else if (firstName.equals(null)) {
//			System.out.println("in first name null value");
//		}
//		
//		boolean password1 =(Pattern.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}", password));
//		if(password1==false) {
//		
//            value = "password has digit,lower case,upper case, special character must occur at least once,password at least 8 characters";
//			return value;
//		}else if (firstName.equals(null)) {
//			System.out.println("in first name null value");
//		}
//		
//		if(password.equals(reTypePassword)) {
//			if(firstName1==true && lastNmae1==true  && mailId1==true && password1==true) {
//				value = "yes";
//				return value;
//			}
//		}else {
//			value = "re-type password is wrong";
//			return value;
//		}
		
		if(firstName1==true) {
			value ="yes";
			return value;
		}
		value="data not insert";
		return value;
	}
}
