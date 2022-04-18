package com.uit.chophen.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.uit.chophen.utils.Role.*;
import static com.uit.chophen.utils.SecurityConstant.*;
import javax.mail.MessagingException;

import com.uit.chophen.dao.UserProfileDAO;
import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.exception.AccountExistsException;
import com.uit.chophen.exception.EmailExistsException;
import com.uit.chophen.exception.EmailNotFoundException;
import com.uit.chophen.exception.UserNotFoundException;
import com.uit.chophen.security.UserPrincipal;

@Service
@Transactional
@Qualifier("UserProfileService")
public class UserProfileServiceImp implements UserProfileService, UserDetailsService {
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private UserProfileDAO userProfileDAO;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private EmailService emailService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserProfile userProfile = userProfileDAO.findUserProfileByAccountName(username);
		if (userProfile == null) {
			LOGGER.error("User not found with account name: " + username);
			throw new UsernameNotFoundException("User not found with account name: " + username);
		} else {
			UserPrincipal userPrincipal = new UserPrincipal(userProfile);
			LOGGER.info("User found with account name: " + username);

			return userPrincipal;

		}
	}

	@Autowired
	public UserProfileServiceImp(UserProfileDAO userProfileDAO, BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService) {
		this.userProfileDAO = userProfileDAO;
		this.bCryptPasswordEncoder= bCryptPasswordEncoder;
		this.emailService = emailService;
	}

	@Override
	public UserProfile signUp(String userAddress, String userEmail, String userFullName, String userPhone,
			String accountName, String password) throws AccountExistsException, EmailExistsException, UserNotFoundException {
		
		validateNewAccountnameAndEmail(StringUtils.EMPTY, accountName, userEmail);
		
		UserProfile profile = new UserProfile();
		profile.setUserId(generateUserId());
		String encodedPassword = encodePassword(password);
		profile.setUserFullName(userFullName);
		profile.setUserAddress(userAddress);
		profile.setUserEmail(userEmail);
		profile.setPassword(encodedPassword);
		profile.setIsActive(true);
		profile.setIsNotLocked(true);
		profile.setAccountName(accountName);
		profile.setRole(ROLE_USER.name());
		profile.setAuthorities(ROLE_USER.getAuthorities());
		profile.setUserPic(getTempProfileImageUrl());
		
		userProfileDAO.save(profile);
		LOGGER.info("New user password: " + password);
		return profile;
	}

	private String getTempProfileImageUrl() {
		// TODO Auto-generated method stub
		return "aa";
	}

	private String encodePassword(String password) {
		// TODO Auto-generated method stub
		return bCryptPasswordEncoder.encode(password);
	}

	@Override
    public void resetPassword(String email) throws MessagingException, EmailNotFoundException {
        UserProfile user = userProfileDAO.findUserProfileByEmail(email);
        if (user == null) {
            throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + email);
        }
        String password = generatePassword();
        user.setPassword(encodePassword(password));
        userProfileDAO.save(user);
        LOGGER.info("New user password: " + password);
        emailService.sendNewPasswordEmail(user.getUserFullName(), password, user.getUserEmail());
    }
	
	private String generatePassword() {
		
		return RandomStringUtils.randomAlphanumeric(10);
	}

	private int generateUserId() {
		return Integer.parseInt(RandomStringUtils.randomNumeric(6));
	}

	private UserProfile validateNewAccountnameAndEmail(String currentUsername, String newUsername, String newEmail) throws AccountExistsException, EmailExistsException, UserNotFoundException {
		if (StringUtils.isNotBlank(currentUsername)) {
			UserProfile currentUser = findUserbyAccoutname(currentUsername);
			if (currentUser == null) {
				throw new UserNotFoundException("No user with account" + currentUsername);
			}

			UserProfile userByNewAccountname = findUserbyAccoutname(newUsername);
			if (userByNewAccountname != null && currentUser.getUserId() == userByNewAccountname.getUserId()) {
				throw new AccountExistsException("Username already exists");
			}
			UserProfile userByNewEmail = findUserByEmail(newEmail);
			if (userByNewEmail != null && currentUser.getUserId() == userByNewEmail.getUserId()) {
				throw new EmailExistsException("Email already exists");

			}
			return currentUser;
		} else {
			UserProfile userByAccountname = findUserbyAccoutname(newUsername);
			if (userByAccountname != null) {
				throw new AccountExistsException("Username already exists");
			}

			UserProfile userByEmail = findUserByEmail(newEmail);
			if (userByEmail != null) {
				throw new EmailExistsException("Email already exists");
			}
			return null;
		}
	}

	@Override
	@Transactional
	public UserProfile findUserbyAccoutname(String accountName) {
		
		return userProfileDAO.findUserProfileByAccountName(accountName);
	}

	@Override
	@Transactional
	public UserProfile findUserByEmail(String email) {
	
		return userProfileDAO.findUserProfileByEmail(email);
	}

	@Override
	public UserProfile findUserbyId(int Id) {
		// TODO Auto-generated method stub
		return userProfileDAO.findUserProfileById(Id);
	}

}
