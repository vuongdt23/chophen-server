package com.uit.chophen.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the listings database table.
 * 
 */
@Entity
@Table(name="listings")
@NamedQuery(name="Listing.findAll", query="SELECT l FROM Listing l")
public class Listing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="listing_id")
	private int listingId;

	@Column(name="listing_address")
	private String listingAddress;

	@Column(name="listing_body")
	private String listingBody;

	@Column(name="listing_image")
	private String listingImage;

	@Column(name="listing_timestamp")
	private String listingTimestamp;

	@Column(name="listing_title")
	private String listingTitle;
	
	@Column(name="listing_Price")
	private long listingPrice;

	//bi-directional many-to-many association to ListingCategory
	@ManyToMany
	@JoinTable(
		name="list_cat"
		, joinColumns={
			@JoinColumn(name="listing_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="category_id")
			}
		)
	private List<ListingCategory> listingCategories;

	//bi-directional many-to-one association to ListingStatus
	@ManyToOne
	@JoinColumn(name="listing_status")
	private ListingStatus listingStatus;

	//bi-directional many-to-one association to UserProfile
	@ManyToOne
	@JoinColumn(name="listing_poster")
	private UserProfile poster;

	//bi-directional many-to-one association to UserSavedListing
	@OneToMany(mappedBy="listing")
	private List<UserSavedListing> userSavedListings;

	public Listing() {
	}

	public int getListingId() {
		return this.listingId;
	}

	public void setListingId(int listingId) {
		this.listingId = listingId;
	}

	public String getListingAddress() {
		return this.listingAddress;
	}

	public void setListingAddress(String listingAddress) {
		this.listingAddress = listingAddress;
	}

	public String getListingBody() {
		return this.listingBody;
	}

	public void setListingBody(String listingBody) {
		this.listingBody = listingBody;
	}

	public String getListingImage() {
		return this.listingImage;
	}

	public void setListingImage(String listingImage) {
		this.listingImage = listingImage;
	}

	public String getListingTimestamp() {
		return this.listingTimestamp;
	}

	public void setListingTimestamp(String listingTimestamp) {
		this.listingTimestamp = listingTimestamp;
	}

	public String getListingTitle() {
		return this.listingTitle;
	}

	public void setListingTitle(String listingTitle) {
		this.listingTitle = listingTitle;
	}

	public List<ListingCategory> getListingCategories() {
		return this.listingCategories;
	}

	public void setListingCategories(List<ListingCategory> listingCategories) {
		this.listingCategories = listingCategories;
	}

	public ListingStatus getListingStatus() {
		return this.listingStatus;
	}

	public void setListingStatusBean(ListingStatus listingStatus) {
		this.listingStatus = listingStatus;
	}

	public long getListingPrice() {
		return listingPrice;
	}

	public void setListingPrice(long listingPrice) {
		this.listingPrice = listingPrice;
	}

	public UserProfile getPoster() {
		return this.poster;
	}

	public void setPoster(UserProfile userProfile) {
		this.poster = userProfile;
	}

	public List<UserSavedListing> getUserSavedListings() {
		return this.userSavedListings;
	}

	public void setUserSavedListings(List<UserSavedListing> userSavedListings) {
		this.userSavedListings = userSavedListings;
	}

	public UserSavedListing addUserSavedListing(UserSavedListing userSavedListing) {
		getUserSavedListings().add(userSavedListing);
		userSavedListing.setListing(this);

		return userSavedListing;
	}

	public UserSavedListing removeUserSavedListing(UserSavedListing userSavedListing) {
		getUserSavedListings().remove(userSavedListing);
		userSavedListing.setListing(null);

		return userSavedListing;
	}

}