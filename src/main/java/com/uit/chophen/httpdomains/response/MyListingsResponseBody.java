package com.uit.chophen.httpdomains.response;

import java.util.List;

import com.uit.chophen.entities.Listing;

public class MyListingsResponseBody {

	List<Listing> listings;

	public List<Listing> getListings() {
		return listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}
}
