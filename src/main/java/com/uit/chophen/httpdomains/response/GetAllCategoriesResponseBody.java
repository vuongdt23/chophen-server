package com.uit.chophen.httpdomains.response;

import java.util.List;

import com.uit.chophen.entities.ListingCategory;

public class GetAllCategoriesResponseBody {

	private int count;
	private List<ListingCategory> categories;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<ListingCategory> getCategories() {
		return categories;
	}
	public void setCategories(List<ListingCategory> categories) {
		this.categories = categories;
	}
}
