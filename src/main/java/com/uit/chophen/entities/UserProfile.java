package com.uit.chophen.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The persistent class for the user_profiles database table.
 * 
 */
@Entity
@Table(name = "user_profiles")
@NamedQuery(name = "UserProfile.findAll", query = "SELECT u FROM UserProfile u")

public class UserProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_address")
	private String userAddress;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_full_name")
	private String userFullName;

	@Column(name = "user_phone")
	private String userPhone;

	@Column(name = "user_pic", length = 2048)
	private String userPic;

	// Security
	@Column(name = "user_roles")
	private String role;

	@Column(name = "user_authorities")
	private String[] authorities;

	@Column(name = "user_is_active")
	private Boolean isActive;

	@Column(name = "user_is_not_locked")
	private Boolean isNotLocked;

	

	@Column(name = "user_account_name")
	private String accountName;

	
	@Column(name = "user_password")
	private String password;
	//

	// bi-directional many-to-one association to Conversation
	@JsonIgnore
	@OneToMany(mappedBy = "userProfile1")
	private List<Conversation> conversations1;

	// bi-directional many-to-one association to Conversation
	@JsonIgnore
	@OneToMany(mappedBy = "userProfile2")
	private List<Conversation> conversations2;

	// bi-directional many-to-one association to Listing
	@JsonIgnore
	@OneToMany(mappedBy = "userProfile")
	private List<Listing> listings;

	// bi-directional many-to-one association to UserNotification
	@JsonIgnore
	@OneToMany(mappedBy = "userProfile")
	private List<UserNotification> userNotifications;

	// bi-directional many-to-one association to UserRating
	@JsonIgnore
	@OneToMany(mappedBy = "creator")
	private List<UserRating> userRatingsCreated;

	// bi-directional many-to-one association to UserRating
	@JsonIgnore
	@OneToMany(mappedBy = "target")
	private List<UserRating> userRatingsReceived;

	// bi-directional many-to-one association to UserReport
	@OneToMany(mappedBy = "creator")
	private List<UserReport> userReportsCreated;

	// bi-directional many-to-one association to UserReport
	@OneToMany(mappedBy = "target")
	private List<UserReport> userReportsReceived;

	// bi-directional many-to-one association to UserSavedListing
	@OneToMany(mappedBy = "userProfile")
	private List<UserSavedListing> userSavedListings;

	@ManyToOne
	@JoinColumn(name = "university_id")
	private University userUniversity;

	public UserProfile() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFullName() {
		return this.userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public List<Conversation> getConversations1() {
		return this.conversations1;
	}

	public void setConversations1(List<Conversation> conversations1) {
		this.conversations1 = conversations1;
	}

	public Conversation addConversations1(Conversation conversations1) {
		getConversations1().add(conversations1);
		conversations1.setUserProfile1(this);

		return conversations1;
	}

	public Conversation removeConversations1(Conversation conversations1) {
		getConversations1().remove(conversations1);
		conversations1.setUserProfile1(null);

		return conversations1;
	}

	public List<Conversation> getConversations2() {
		return this.conversations2;
	}

	public void setConversations2(List<Conversation> conversations2) {
		this.conversations2 = conversations2;
	}

	public Conversation addConversations2(Conversation conversations2) {
		getConversations2().add(conversations2);
		conversations2.setUserProfile2(this);

		return conversations2;
	}

	public Conversation removeConversations2(Conversation conversations2) {
		getConversations2().remove(conversations2);
		conversations2.setUserProfile2(null);

		return conversations2;
	}

	public List<Listing> getListings() {
		return this.listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}

	public Listing addListing(Listing listing) {
		getListings().add(listing);
		listing.setUserProfile(this);

		return listing;
	}

	public Listing removeListing(Listing listing) {
		getListings().remove(listing);
		listing.setUserProfile(null);

		return listing;
	}

	public List<UserNotification> getUserNotifications() {
		return this.userNotifications;
	}

	public void setUserNotifications(List<UserNotification> userNotifications) {
		this.userNotifications = userNotifications;
	}

	public UserNotification addUserNotification(UserNotification userNotification) {
		getUserNotifications().add(userNotification);
		userNotification.setUserProfile(this);

		return userNotification;
	}

	public UserNotification removeUserNotification(UserNotification userNotification) {
		getUserNotifications().remove(userNotification);
		userNotification.setUserProfile(null);

		return userNotification;
	}

	public List<UserRating> getUserRatingsCreated() {
		return this.userRatingsCreated;
	}

	public void setUserRatingsCreated(List<UserRating> userRating) {
		this.userRatingsCreated = userRating;
	}

	public UserRating addUserRatingsCreated(UserRating userRating) {
		getUserRatingsCreated().add(userRating);
		userRating.setCreator(this);

		return userRating;
	}

	public UserRating removeUserRatingsCreated(UserRating userRating) {
		getUserRatingsCreated().remove(userRating);
		userRating.setCreator(null);

		return userRating;
	}

	public List<UserRating> getUserRatingsReceived() {
		return this.userRatingsReceived;
	}

	public void setUserRatingsReceived(List<UserRating> userRatings) {
		this.userRatingsReceived = userRatings;
	}

	public UserRating addUserRatingsReceived(UserRating userRating) {
		getUserRatingsReceived().add(userRating);
		userRating.setTarget(this);

		return userRating;
	}

	public UserRating removeUserRatingsReceived(UserRating userRating) {
		getUserRatingsReceived().remove(userRating);
		userRating.setTarget(null);

		return userRating;
	}

	public List<UserReport> getUserReportsCreated() {
		return this.userReportsCreated;
	}

	public void setUserReportsCreated(List<UserReport> userReports) {
		this.userReportsCreated = userReports;
	}

	public UserReport addUserReportsCreated(UserReport userReport) {
		getUserReportsCreated().add(userReport);
		userReport.setCreator(this);

		return userReport;
	}

	public UserReport removeUserReports1(UserReport userReport) {
		getUserReportsCreated().remove(userReport);
		userReport.setCreator(null);

		return userReport;
	}

	public List<UserReport> getUserReportsReceived() {
		return this.userReportsReceived;
	}

	public void setUserReportsReceived(List<UserReport> userReports) {
		this.userReportsReceived = userReports;
	}

	public UserReport addUserReportsReceived(UserReport userReport) {
		getUserReportsReceived().add(userReport);
		userReport.setTarget(this);

		return userReport;
	}

	public UserReport removeUserReportsReceived(UserReport userReport) {
		getUserReportsReceived().remove(userReport);
		userReport.setTarget(null);

		return userReport;
	}

	public List<UserSavedListing> getUserSavedListings() {
		return this.userSavedListings;
	}

	public void setUserSavedListings(List<UserSavedListing> userSavedListings) {
		this.userSavedListings = userSavedListings;
	}

	public UserSavedListing addUserSavedListing(UserSavedListing userSavedListing) {
		getUserSavedListings().add(userSavedListing);
		userSavedListing.setUserProfile(this);

		return userSavedListing;
	}

	public UserSavedListing removeUserSavedListing(UserSavedListing userSavedListing) {
		getUserSavedListings().remove(userSavedListing);
		userSavedListing.setUserProfile(null);

		return userSavedListing;
	}

	public University getUserUniversity() {
		return userUniversity;
	}

	public void setUserUniversity(University userUniversity) {
		this.userUniversity = userUniversity;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}


	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsNotLocked() {
		return isNotLocked;
	}

	public void setIsNotLocked(Boolean isNotLocked) {
		this.isNotLocked = isNotLocked;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}