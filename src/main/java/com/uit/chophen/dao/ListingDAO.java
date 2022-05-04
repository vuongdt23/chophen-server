package com.uit.chophen.dao;

import com.uit.chophen.entities.Listing;

public interface ListingDAO {

	public void saves(Listing listing);
	public Listing getListingById(int listingId);
}
