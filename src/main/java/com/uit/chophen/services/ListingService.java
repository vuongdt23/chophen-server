package com.uit.chophen.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.uit.chophen.entities.Listing;

public interface ListingService {

	Listing createListing(String listingTitle, String listingBody, String listingAdress, long listingPrice,
			MultipartFile listingImg, int[] listingCateogriesId, int creatorId) throws IOException;
	
	Listing getListingById(int listingId);
}
