package com.uit.chophen.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the listing_status database table.
 * 
 */
@Entity
@Table(name="listing_status")
@NamedQuery(name="ListingStatus.findAll", query="SELECT l FROM ListingStatus l")
public class ListingStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="listing_status_id")
	private int listingStatusId;

	@Column(name="listing_status_description")
	private String listingStatusDescription;

	@Column(name="listing_status_name")
	private String listingStatusName;

	//bi-directional many-to-one association to Listing
	@OneToMany(mappedBy="listingStatusBean")
	private List<Listing> listings;

	public ListingStatus() {
	}

	public int getListingStatusId() {
		return this.listingStatusId;
	}

	public void setListingStatusId(int listingStatusId) {
		this.listingStatusId = listingStatusId;
	}

	public String getListingStatusDescription() {
		return this.listingStatusDescription;
	}

	public void setListingStatusDescription(String listingStatusDescription) {
		this.listingStatusDescription = listingStatusDescription;
	}

	public String getListingStatusName() {
		return this.listingStatusName;
	}

	public void setListingStatusName(String listingStatusName) {
		this.listingStatusName = listingStatusName;
	}

	public List<Listing> getListings() {
		return this.listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}

	public Listing addListing(Listing listing) {
		getListings().add(listing);
		listing.setListingStatusBean(this);

		return listing;
	}

	public Listing removeListing(Listing listing) {
		getListings().remove(listing);
		listing.setListingStatusBean(null);

		return listing;
	}

}