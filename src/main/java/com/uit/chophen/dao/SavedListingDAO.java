package com.uit.chophen.dao;

import java.util.List;

import com.uit.chophen.entities.Listing;
import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.entities.UserSavedListing;

public interface SavedListingDAO {

    public UserSavedListing getSavedListingByUserAndListing(UserProfile user, Listing listing);
    public UserSavedListing save(UserSavedListing userSavedListing);
    public void remove(UserSavedListing userSavedListing);
    public List<Listing> getSaveListingsByUser(UserProfile user);
}
