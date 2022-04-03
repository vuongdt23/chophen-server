package com.uit.chophen.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;

	@Column(name="user_address")
	private String userAddress;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_full_name")
	private String userFullName;

	@Column(name="user_phone")
	private String userPhone;

	//bi-directional many-to-one association to Conversation
	@OneToMany(mappedBy="user1")
	private List<Conversation> conversations1;

	//bi-directional many-to-one association to Conversation
	@OneToMany(mappedBy="user2")
	private List<Conversation> conversations2;

	//bi-directional many-to-one association to Listing
	@OneToMany(mappedBy="user")
	private List<Listing> listings;

	//bi-directional many-to-one association to UserNotification
	@OneToMany(mappedBy="user")
	private List<UserNotification> userNotifications;

	//bi-directional many-to-one association to UserRating
	@OneToMany(mappedBy="user1")
	private List<UserRating> userRatings1;

	//bi-directional many-to-one association to UserRating
	@OneToMany(mappedBy="user2")
	private List<UserRating> userRatings2;

	//bi-directional many-to-one association to UserReport
	@OneToMany(mappedBy="user1")
	private List<UserReport> userReports1;

	//bi-directional many-to-one association to UserReport
	@OneToMany(mappedBy="user2")
	private List<UserReport> userReports2;

	//bi-directional many-to-one association to UserSavedListing
	@OneToMany(mappedBy="user")
	private List<UserSavedListing> userSavedListings;

	public User() {
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
		conversations1.setUser1(this);

		return conversations1;
	}

	public Conversation removeConversations1(Conversation conversations1) {
		getConversations1().remove(conversations1);
		conversations1.setUser1(null);

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
		conversations2.setUser2(this);

		return conversations2;
	}

	public Conversation removeConversations2(Conversation conversations2) {
		getConversations2().remove(conversations2);
		conversations2.setUser2(null);

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
		listing.setUser(this);

		return listing;
	}

	public Listing removeListing(Listing listing) {
		getListings().remove(listing);
		listing.setUser(null);

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
		userNotification.setUser(this);

		return userNotification;
	}

	public UserNotification removeUserNotification(UserNotification userNotification) {
		getUserNotifications().remove(userNotification);
		userNotification.setUser(null);

		return userNotification;
	}

	public List<UserRating> getUserRatings1() {
		return this.userRatings1;
	}

	public void setUserRatings1(List<UserRating> userRatings1) {
		this.userRatings1 = userRatings1;
	}

	public UserRating addUserRatings1(UserRating userRatings1) {
		getUserRatings1().add(userRatings1);
		userRatings1.setUser1(this);

		return userRatings1;
	}

	public UserRating removeUserRatings1(UserRating userRatings1) {
		getUserRatings1().remove(userRatings1);
		userRatings1.setUser1(null);

		return userRatings1;
	}

	public List<UserRating> getUserRatings2() {
		return this.userRatings2;
	}

	public void setUserRatings2(List<UserRating> userRatings2) {
		this.userRatings2 = userRatings2;
	}

	public UserRating addUserRatings2(UserRating userRatings2) {
		getUserRatings2().add(userRatings2);
		userRatings2.setUser2(this);

		return userRatings2;
	}

	public UserRating removeUserRatings2(UserRating userRatings2) {
		getUserRatings2().remove(userRatings2);
		userRatings2.setUser2(null);

		return userRatings2;
	}

	public List<UserReport> getUserReports1() {
		return this.userReports1;
	}

	public void setUserReports1(List<UserReport> userReports1) {
		this.userReports1 = userReports1;
	}

	public UserReport addUserReports1(UserReport userReports1) {
		getUserReports1().add(userReports1);
		userReports1.setUser1(this);

		return userReports1;
	}

	public UserReport removeUserReports1(UserReport userReports1) {
		getUserReports1().remove(userReports1);
		userReports1.setUser1(null);

		return userReports1;
	}

	public List<UserReport> getUserReports2() {
		return this.userReports2;
	}

	public void setUserReports2(List<UserReport> userReports2) {
		this.userReports2 = userReports2;
	}

	public UserReport addUserReports2(UserReport userReports2) {
		getUserReports2().add(userReports2);
		userReports2.setUser2(this);

		return userReports2;
	}

	public UserReport removeUserReports2(UserReport userReports2) {
		getUserReports2().remove(userReports2);
		userReports2.setUser2(null);

		return userReports2;
	}

	public List<UserSavedListing> getUserSavedListings() {
		return this.userSavedListings;
	}

	public void setUserSavedListings(List<UserSavedListing> userSavedListings) {
		this.userSavedListings = userSavedListings;
	}

	public UserSavedListing addUserSavedListing(UserSavedListing userSavedListing) {
		getUserSavedListings().add(userSavedListing);
		userSavedListing.setUser(this);

		return userSavedListing;
	}

	public UserSavedListing removeUserSavedListing(UserSavedListing userSavedListing) {
		getUserSavedListings().remove(userSavedListing);
		userSavedListing.setUser(null);

		return userSavedListing;
	}

}