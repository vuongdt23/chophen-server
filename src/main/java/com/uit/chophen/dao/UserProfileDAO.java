package com.uit.chophen.dao;

import java.util.List;


import com.uit.chophen.entities.UserProfile;

public interface UserProfileDAO {

	public UserProfile findUserProfileByAccountName(String accountName);
	public UserProfile findUserProfileById(int id);
	public List<UserProfile> findAll();
	public void save (UserProfile userProfile); 
	public UserProfile findUserProfileByEmail(String email);
	
}
