package com.uit.chophen.services;
import static com.uit.chophen.utils.MiscConstants.*;
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
import com.uit.chophen.dao.ListingStatusDAO;
import com.uit.chophen.dao.SavedListingDAO;
import com.uit.chophen.dao.UserProfileDAO;
import com.uit.chophen.entities.Listing;
import com.uit.chophen.entities.ListingCategory;
import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.entities.UserSavedListing;

@Service
public class ListingServiceImp implements ListingService {

	private ListingDAO listingDAO;
	private ListingCategoryDAO listingCategoryDAO;
	private UserProfileDAO userProfileDAO;
	private SavedListingDAO savedListingDAO;
	private ListingStatusDAO listingStatusDAO;

	@Autowired
	public ListingServiceImp(ListingStatusDAO listingStatusDAO, ListingDAO listingDAO, ListingCategoryDAO listingCategoryDAO, UserProfileDAO userProfileDAO, SavedListingDAO savedListingDAO) {

		this.userProfileDAO = userProfileDAO;
		this.listingDAO = listingDAO;
		this.listingCategoryDAO = listingCategoryDAO;
		this.savedListingDAO = savedListingDAO;
		this.listingStatusDAO = listingStatusDAO;
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
		listing.setListingStatusBean(listingStatusDAO.getListingStatusById(AVAILABLE_STATUS_ID));
		
		
		List<ListingCategory> listingCategories = new ArrayList<ListingCategory>();
		
		for(int i = 0; i< listingCateogriesId.length; i++) {
			listingCategories.add(listingCategoryDAO.getListingCategoryById(listingCateogriesId[i]));
		}
		listing.setListingCategories(listingCategories);
		listingDAO.saves(listing);
		return listing;

	}

	private String uploadImgAndGetLink(MultipartFile img) throws IOException {
		Bucket storageBucket = StorageClient.getInstance().bucket();
		String fileName = "postImages/" +  RandomStringUtils.randomAlphanumeric(10) + "PostImg" + getDateTimeString();
		Blob blob = storageBucket.create(fileName, img.getBytes(), "image/jpeg");
		String imgLink = "https://storage.googleapis.com/" + blob.getBucket() +"/" + blob.getName();
		return imgLink;
	}

	private String getDateTimeString() {
		String pattern = "dd-MM-yyyyHH:mm:ss";
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

	@Override
	@Transactional
	public List<Listing> getListingsPageByCategories(int pageSize, int pageIndex, int[] listingCateogriesId) {
		
		if(listingCateogriesId.length==0) {
			long totalCountAll = listingDAO.getAllListingsCount();
			long pageCountAll = (int) Math.ceil(totalCountAll/pageSize);
			int firtResultAll = (pageIndex-1)*pageSize;
			int lastResultAll = firtResultAll + pageSize ;
			return listingDAO.getAllListings(firtResultAll, lastResultAll);
		}
		
		List<ListingCategory> categories = new ArrayList<ListingCategory>();
		for(int i: listingCateogriesId) {
		 ListingCategory category= 	listingCategoryDAO.getListingCategoryById(i);
		 categories.add(category);
		}
		
		
		long totalCount = listingDAO.getListingCountByCategories(categories);
		long pageCount = (int) Math.ceil(totalCount/pageSize);
		
		int firtResult = (pageIndex-1)*pageSize;
		int lastResult = firtResult + pageSize ;
		
		return listingDAO.getListingByCategories(firtResult, lastResult,categories);
		
		
	}

	@Override
	public List<Listing> getListingsPageByString(int pageSize, int pageIndex, String searchString) {
		int firtResult = (pageIndex-1)*pageSize;
		int lastResult = firtResult + pageSize -1;
		return listingDAO.getListingByString(firtResult, lastResult, searchString);
	}

	@Override
	public List<ListingCategory> getAllListingCategories() {
		// TODO Auto-generated method stub
		return listingCategoryDAO.getAll();
	}

	@Override
	@Transactional
	public UserSavedListing saveListing(int userId, int listingId) {
		UserProfile user = userProfileDAO.findUserProfileById(userId);
		Listing listing = listingDAO.getListingById(listingId);
		
		UserSavedListing saved = new UserSavedListing();
		saved.setListing(listing);
		saved.setUserProfile(user);
		return savedListingDAO.save(saved);
	}

	@Override
	public boolean checkCanSave(int userId, int listingId) {
		UserProfile user = userProfileDAO.findUserProfileById(userId);
		Listing listing = listingDAO.getListingById(listingId);
		
		if (savedListingDAO.getSavedListingByUserAndListing(user, listing) == null) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void unsaveListing(int userId, int listingId) {
		if(!checkCanSave(userId, listingId)) {
			UserProfile user = userProfileDAO.findUserProfileById(userId);
			Listing listing = listingDAO.getListingById(listingId);
			UserSavedListing saved = savedListingDAO.getSavedListingByUserAndListing(user, listing);
			savedListingDAO.remove(saved);
			
		}
	}

	@Override
	@Transactional
	public int getListingOwnerId(int listingId) {
		Listing listing = listingDAO.getListingById(listingId);
		return listing.getPoster().getUserId();
	}

	@Override
	public List<ListingCategory> getListingCategoriesFromId(int[] listingCateogriesId) {
	List<ListingCategory> listingCategories = new ArrayList<ListingCategory>();
		
		for(int i = 0; i< listingCateogriesId.length; i++) {
			listingCategories.add(listingCategoryDAO.getListingCategoryById(listingCateogriesId[i]));
		}
		return listingCategories;
	}

	@Override
	@Transactional
	public Listing updateListing(Listing listing) {
		
		 listingDAO.saves(listing);
		 return listing;
	}

	@Override
	@Transactional
	public void deleteListing(Listing listing) {
		listingDAO.delete(listing);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public Listing markListingAsSold(int listingId) {
		Listing listing = listingDAO.getListingById(listingId);
		listing.setListingStatusBean(listingStatusDAO.getListingStatusById(SOLD_STATUS_ID));
		listingDAO.saves(listing);
		return listing;
	}

	@Override
	public List<Listing> getSavedListingByUser(int userId) {
		UserProfile user = userProfileDAO.findUserProfileById(userId);
		return savedListingDAO.getSaveListingsByUser(user);
	}

	
}
