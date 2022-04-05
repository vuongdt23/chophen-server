package com.uit.chophen.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the listing_categories database table.
 * 
 */
@Entity
@Table(name="listing_categories")
@NamedQuery(name="ListingCategory.findAll", query="SELECT l FROM ListingCategory l")
public class ListingCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="listing_category_id")
	private int listingCategoryId;

	@Column(name="listing_category_description")
	private String listingCategoryDescription;

	@Column(name="listing_category_name")
	private String listingCategoryName;

	//bi-directional many-to-many association to Listing
	@ManyToMany(mappedBy="listingCategories")
	private List<Listing> listings;

	public ListingCategory() {
	}

	public int getListingCategoryId() {
		return this.listingCategoryId;
	}

	public void setListingCategoryId(int listingCategoryId) {
		this.listingCategoryId = listingCategoryId;
	}

	public String getListingCategoryDescription() {
		return this.listingCategoryDescription;
	}

	public void setListingCategoryDescription(String listingCategoryDescription) {
		this.listingCategoryDescription = listingCategoryDescription;
	}

	public String getListingCategoryName() {
		return this.listingCategoryName;
	}

	public void setListingCategoryName(String listingCategoryName) {
		this.listingCategoryName = listingCategoryName;
	}

	public List<Listing> getListings() {
		return this.listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}

}