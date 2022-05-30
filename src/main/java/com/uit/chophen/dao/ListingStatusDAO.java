package com.uit.chophen.dao;

import com.uit.chophen.entities.ListingStatus;

public interface ListingStatusDAO {

	public ListingStatus save(ListingStatus status);
	public long getCount();
	public ListingStatus getListingStatusById(int listingStatusId);
;}
