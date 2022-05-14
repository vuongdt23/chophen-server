package com.uit.chophen.httpdomains.request;

public class GetAllListingsRequestBody {

	private int pageSize;
	private int pageIndex;
	private int[] listingCategoriesIds;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int[] getListingCategoriesIds() {
		return listingCategoriesIds;
	}
	public void setListingCategoriesIds(int[] listingCategoriesIds) {
		this.listingCategoriesIds = listingCategoriesIds;
	}
}
