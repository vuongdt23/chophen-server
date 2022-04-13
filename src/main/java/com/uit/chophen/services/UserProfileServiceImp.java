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
import com.uit.chophen.dao.UserProfileDAO;
import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.exception.AccountExistsException;
import com.uit.chophen.exception.EmailExistsException;
import com.uit.chophen.exception.UserNotFoundException;
import com.uit.chophen.security.UserPrincipal;

@Service
@Transactional
@Qualifier("UserProfileService")
public class UserProfileServiceImp implements UserProfileService, UserDetailsService {
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private UserProfileDAO userProfileDAO;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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
	public UserProfileServiceImp(UserProfileDAO userProfileDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userProfileDAO = userProfileDAO;
		this.bCryptPasswordEncoder= bCryptPasswordEncoder;
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

	//private String generatePassword() {
		// TODO Auto-generated method stub
	//	return RandomStringUtils.randomAlphanumeric(10);
//	}

	private int generateUserId() {
		// TODO Auto-generated method stub
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
	public UserProfile findUserbyAccoutname(String accountName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
