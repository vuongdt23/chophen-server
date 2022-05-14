package com.uit.chophen.httpdomains.response;

import java.util.List;

import com.uit.chophen.entities.Listing;

public class GetAllListingsResponseBody {
	List<Listing> listingPage;
	public List<Listing> getListingPage() {
		return listingPage;
	}
	public void setListingPage(List<Listing> listingPage) {
		this.listingPage = listingPage;
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
	int pageIndex;
	int pageSize;

}
