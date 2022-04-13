package com.uit.chophen.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uit.chophen.entities.UserProfile;

public interface UserProfileDAO extends JpaRepository<UserProfile, Long>{

	public UserProfile findUserProfileByAccountName(String accountName);
	
}
