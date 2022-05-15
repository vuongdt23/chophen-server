package com.uit.chophen.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.uit.chophen.entities.Listing;

public interface ListingService {

	public Listing createListing(String listingTitle, String listingBody, String listingAdress, long listingPrice,
			MultipartFile listingImg, int[] listingCateogriesId, int creatorId) throws IOException;
	
	public Listing getListingById(int listingId);
	public List<Listing> getListingsByUserId(int userId);
	public List<Listing> getListingsPageByCategories(int pageSize, int pageIndex, int[] listingCateogriesId);
	public List<Listing> getListingsPageByString(int pageSize, int pageIndex, String searchString);
}
