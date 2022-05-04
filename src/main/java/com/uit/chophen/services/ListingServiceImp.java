package com.uit.chophen.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.RandomStringUtils;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.uit.chophen.dao.ListingCategoryDAO;
import com.uit.chophen.dao.ListingDAO;
import com.uit.chophen.dao.UserProfileDAO;
import com.uit.chophen.entities.Listing;
import com.uit.chophen.entities.ListingCategory;
import com.uit.chophen.entities.UserProfile;

@Service
public class ListingServiceImp implements ListingService {

	private ListingDAO listingDAO;
	private ListingCategoryDAO listingCategoryDAO;
	private UserProfileDAO userProfileDAO;

	@Autowired
	public ListingServiceImp(ListingDAO listingDAO, ListingCategoryDAO listingCategoryDAO, UserProfileDAO userProfileDAO) {

		this.userProfileDAO = userProfileDAO;
		this.listingDAO = listingDAO;
		this.listingCategoryDAO = listingCategoryDAO;
	}

	@Override
	public Listing createListing(String listingTitle, String listingBody, String listingAdress, long listingPrice,
			MultipartFile listingImg, int[] listingCateogriesId, int creatorId) throws IOException {
		String listingImgLink = uploadImgAndGetLink(listingImg);
		UserProfile creator = userProfileDAO.findUserProfileById(creatorId);
		Listing listing = new Listing();
		
		
		listing.setPoster(creator);
		listing.setListingImage(listingImgLink);
		listing.setListingAddress(listingAdress);
		listing.setListingBody(listingBody);
		listing.setListingPrice(listingPrice);
		listing.setListingTitle(listingTitle);
		listing.setListingTimestamp(getDateTimeString());
		

		
		List<ListingCategory> listingCategories = new ArrayList<ListingCategory>();
		
		for(int i = 0; i< listingCateogriesId.length; i++) {
			listingCategories.add(listingCategoryDAO.getListingCategoryById(listingCateogriesId[i]));
		}
		listing.setListingCategories(listingCategories);
		listingDAO.saves(listing);
		return listing;

	}

	private String uploadImgAndGetLink(MultipartFile img) throws IOException {
		String pattern = "dd-MM-yyyy HH:mm:ss";
		DateFormat df = new SimpleDateFormat(pattern);
		Bucket storageBucket = StorageClient.getInstance().bucket();
		String fileName = "postImages/" +  RandomStringUtils.randomAlphanumeric(10) + "PostImg " + getDateTimeString();
		Blob blob = storageBucket.create(fileName, img.getBytes(), "image/jpeg");
		return blob.getMediaLink();
	}

	private String getDateTimeString() {
		String pattern = "dd-MM-yyyy HH:mm:ss";
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(new Date());
	}

	@Override
	@Transactional
	public Listing getListingById(int listingId) {
		return listingDAO.getListingById(listingId);
	}

	@Override
	@Transactional
	public List<Listing> getListingsByUserId(int userId) {
		return listingDAO.getListingByUser(userId);
	}

}
