package com.uit.chophen.controllers;

import static com.uit.chophen.utils.SecurityConstant.TOKEN_PREFIX;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uit.chophen.entities.Listing;
import com.uit.chophen.httpdomains.request.CreateListingRequestBody;
import com.uit.chophen.httpdomains.request.GetAllListingsRequestBody;
import com.uit.chophen.httpdomains.response.GetAllListingsResponseBody;
import com.uit.chophen.httpdomains.response.MyListingsResponseBody;
import com.uit.chophen.services.ListingService;
import com.uit.chophen.utils.JWTTokenProvider;

@RestController
@RequestMapping(value = "/listings")
public class ListingController {
	

	private ListingService listingService;
	private JWTTokenProvider jwtTokenProvider;
	
	@Autowired
	public ListingController(ListingService listingService, JWTTokenProvider jwtTokenProvider) {
		this.listingService = listingService;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	
	@PostMapping("/createListing")
	public ResponseEntity<Listing> createListing(@RequestHeader(name = "Authorization") String jwtToken, @RequestPart("body") CreateListingRequestBody reqBody, @RequestPart("img") MultipartFile img) throws IOException{
		
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		String listingTitle = reqBody.getListingTitle();
		String listingBody = reqBody.getListingBody();
		int[] listingCategories = reqBody.getListingCategories();
		long listingPrice = reqBody.getListingPrice();
		String listingAddress = reqBody.getListingAddress();
			
		Listing listing = listingService.createListing(listingTitle, listingBody, listingAddress, listingPrice, img, listingCategories, userId);
		
		return new ResponseEntity<Listing>(listing, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/{listingId}")
	public ResponseEntity<Listing> getListingById(@PathVariable int listingId, @RequestHeader(name = "Authorization") String jwtToken){
		Listing listing = listingService.getListingById(listingId);
		
		return new ResponseEntity<Listing>(listing, HttpStatus.OK);
	}
	
	
	@GetMapping("/myListings")
	public ResponseEntity<MyListingsResponseBody> getUserOwnedListings(@RequestHeader(name = "Authorization") String jwtToken){
		MyListingsResponseBody listingsResponseBody = new MyListingsResponseBody();
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));

		listingsResponseBody.setListings(listingService.getListingsByUserId(userId));
		
		return new ResponseEntity<MyListingsResponseBody>(listingsResponseBody, HttpStatus.OK);
	}
	
	@GetMapping("/getListings") ResponseEntity<GetAllListingsResponseBody> getAllListings(@RequestBody GetAllListingsRequestBody reqBody){
		int[] listingCategoriesIds = reqBody.getListingCategoriesIds();
		int pageSize = reqBody.getPageSize();
		int pageIndex = reqBody.getPageIndex();
		
		GetAllListingsResponseBody resBody = new GetAllListingsResponseBody();
		resBody.setPageIndex(pageIndex);
		reqBody.setPageSize(pageSize);
		resBody.setListingPage(listingService.getListingsPageByCategories(pageSize, pageIndex, listingCategoriesIds));
		return new ResponseEntity<GetAllListingsResponseBody>(resBody, HttpStatus.OK);

		
	}
	
	
}

