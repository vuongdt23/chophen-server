package com.uit.chophen.dao;

import java.util.List;

import com.uit.chophen.entities.Listing;
import com.uit.chophen.entities.ListingCategory;

public interface ListingDAO {

	public void saves(Listing listing);
	public Listing getListingById(int listingId);
	public List<Listing> getListingByUser(int userId);
	public long getAllListingsCount();
	long getListingCountByCategories(List<ListingCategory> categories);
	public List<Listing> getListingByCategories(int firstResult, int lastResult, List<ListingCategory> categories);
	public List<Listing> getAllListings(int firstResult, int lastResult);
	public List<Listing> getListingByString(int firstResult, int lastResult, String searchString);
}
