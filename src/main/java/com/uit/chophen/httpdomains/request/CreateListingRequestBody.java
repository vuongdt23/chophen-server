package com.uit.chophen.httpdomains.request;

public class CreateListingRequestBody {


	private String listingTitle;
	private String listingBody;
	private String listingAddress;
	private int[] listingCategories;
	private long listingPrice;
	
	
	public String getListingTitle() {
		return listingTitle;
	}
	public void setListingTitle(String listingTitle) {
		this.listingTitle = listingTitle;
	}
	public String getListingBody() {
		return listingBody;
	}
	public void setListingBody(String listingBody) {
		this.listingBody = listingBody;
	}
	public String getListingAddress() {
		return listingAddress;
	}
	public void setListingAddress(String listingAddress) {
		this.listingAddress = listingAddress;
	}
	public int[] getListingCategories() {
		return listingCategories;
	}
	public void setListingCategories(int[] listingCategories) {
		this.listingCategories = listingCategories;
	}
	public long getListingPrice() {
		return listingPrice;
	}
	public void setListingPrice(long listingPrice) {
		this.listingPrice = listingPrice;
	}
	
	
}
