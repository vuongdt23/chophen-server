package com.uit.chophen.dao;

import java.util.List;

import com.uit.chophen.entities.Listing;

public interface ListingDAO {

	public void saves(Listing listing);
	public Listing getListingById(int listingId);
	public List<Listing> getListingByUser(int userId);

}
