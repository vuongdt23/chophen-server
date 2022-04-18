package com.uit.chophen.services;

import javax.mail.MessagingException;

import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.exception.AccountExistsException;
import com.uit.chophen.exception.EmailExistsException;
import com.uit.chophen.exception.EmailNotFoundException;
import com.uit.chophen.exception.UserNotFoundException;

public interface UserProfileService {

	public UserProfile signUp(String userAddress, String userEmail, String userFullName, String userPhone, String accountName, String password) throws AccountExistsException, EmailExistsException, UserNotFoundException;
	
	public UserProfile findUserbyAccoutname(String accountName);
	public UserProfile findUserByEmail(String email);
	public UserProfile findUserbyId(int Id);
    public void resetPassword(String email) throws MessagingException, EmailNotFoundException;

}
