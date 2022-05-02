package com.uit.chophen.services;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.exception.AccountExistsException;
import com.uit.chophen.exception.EmailExistsException;
import com.uit.chophen.exception.EmailNotFoundException;
import com.uit.chophen.exception.UserNotFoundException;

public interface UserProfileService {

	public UserProfile signUp(String userAddress, String userEmail, String userFullName, String userPhone, String accountName, String password) throws AccountExistsException, EmailExistsException, UserNotFoundException;
	
	public UserProfile findUserbyAccoutname(String accountName);
	public UserProfile findUserByEmail(String email);
	public UserProfile findUserbyId(int id);
    public void resetPassword(String email) throws MessagingException, EmailNotFoundException;
    public UserProfile updateProfilePic(int id, MultipartFile file) throws IOException;
    public UserProfile updateProfile(int id, String address, String fullName, String phone);
}
