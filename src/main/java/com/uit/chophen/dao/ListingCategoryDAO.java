package com.uit.chophen.dao;

import java.util.List;

import com.uit.chophen.entities.ListingCategory;

public interface ListingCategoryDAO {

	public ListingCategory getListingCategoryById(int listingCategoryId);
	public List<ListingCategory> getAll();
}
