package com.uit.chophen.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uit.chophen.dao.UserProfileDAO;
import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.security.UserPrincipal;

@Service
@Transactional
@Qualifier("UserProfileService")
public class UserProfileServiceImp implements UserProfileService, UserDetailsService {
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private UserProfileDAO userProfileDAO;

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
	public UserProfileServiceImp(UserProfileDAO userProfileDAO) {
		this.userProfileDAO = userProfileDAO;
	}

}
