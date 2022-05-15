package com.uit.chophen.httpdomains.response;
import java.util.List;

import com.uit.chophen.entities.Listing;
public class SearchListingResponseBody {

	private String searchString;
	private int pageIndex;
	private int pageSize;
	private List<Listing> listingPage;
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Listing> getListingPage() {
		return listingPage;
	}
	public void setListingPage(List<Listing> listingPage) {
		this.listingPage = listingPage;
	}
	
	
}
