package com.uit.chophen.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_saved_listings database table.
 * 
 */
@Entity
@Table(name="user_saved_listings")
@NamedQuery(name="UserSavedListing.findAll", query="SELECT u FROM UserSavedListing u")
public class UserSavedListing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="saved_listing_id")
	private int savedListingId;

	//bi-directional many-to-one association to Listing
	@ManyToOne
	@JoinColumn(name="saved_listing_listing")
	private Listing listing;

	//bi-directional many-to-one association to UserProfile
	@ManyToOne
	@JoinColumn(name="saved_listing_user")
	private UserProfile userProfile;

	public UserSavedListing() {
	}

	public int getSavedListingId() {
		return this.savedListingId;
	}

	public void setSavedListingId(int savedListingId) {
		this.savedListingId = savedListingId;
	}

	public Listing getListing() {
		return this.listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}

	public UserProfile getUserProfile() {
		return this.userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

}