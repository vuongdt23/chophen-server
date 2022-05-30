package com.uit.chophen.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.uit.chophen.entities.Listing;
import com.uit.chophen.entities.ListingCategory;
import com.uit.chophen.entities.UserSavedListing;

public interface ListingService {

	public Listing createListing(String listingTitle, String listingBody, String listingAdress, long listingPrice,
			MultipartFile listingImg, int[] listingCateogriesId, int creatorId) throws IOException;
	
	public Listing getListingById(int listingId);
	public List<Listing> getListingsByUserId(int userId);
	public List<Listing> getListingsPageByCategories(int pageSize, int pageIndex, int[] listingCateogriesId);
	public List<Listing> getListingsPageByString(int pageSize, int pageIndex, String searchString);
	public List<ListingCategory> getAllListingCategories();
	public UserSavedListing saveListing(int userId, int listingId);
	public boolean checkCanSave(int userId, int listingId);
	public void unsaveListing(int userId, int listingId);
	public int getListingOwnerId(int listingId);
	public List<ListingCategory> getListingCategoriesFromId(int[] listingCateogriesId);
	public Listing updateListing(Listing listing);
	public void deleteListing(Listing listing);

}
