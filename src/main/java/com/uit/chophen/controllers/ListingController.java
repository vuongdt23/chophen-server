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
import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.entities.UserSavedListing;
import com.uit.chophen.httpdomains.request.CreateListingRequestBody;
import com.uit.chophen.httpdomains.request.GetAllListingsRequestBody;
import com.uit.chophen.httpdomains.request.SearchListingRequestBody;
import com.uit.chophen.httpdomains.request.UpdateListingRequestBody;
import com.uit.chophen.httpdomains.response.BasicBooleanResponseBody;
import com.uit.chophen.httpdomains.response.BasicStringResponseBody;
import com.uit.chophen.httpdomains.response.GetAllListingsResponseBody;
import com.uit.chophen.httpdomains.response.GetProfileResponseBody;
import com.uit.chophen.httpdomains.response.MyListingsResponseBody;
import com.uit.chophen.httpdomains.response.SearchListingResponseBody;
import com.uit.chophen.services.ListingService;
import com.uit.chophen.services.UserRatingService;
import com.uit.chophen.utils.JWTTokenProvider;

@RestController
@RequestMapping(value = "/listings")
public class ListingController {

	private ListingService listingService;
	private JWTTokenProvider jwtTokenProvider;
	private UserRatingService userRatingService;

	@Autowired
	public ListingController(ListingService listingService, JWTTokenProvider jwtTokenProvider, UserRatingService userRatingService) {
		this.listingService = listingService;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRatingService = userRatingService;
	}

	@PostMapping("/createListing")
	public ResponseEntity<Listing> createListing(@RequestHeader(name = "Authorization") String jwtToken,
			@RequestPart("body") CreateListingRequestBody reqBody, @RequestPart("img") MultipartFile img)
			throws IOException {

		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		String listingTitle = reqBody.getListingTitle();
		String listingBody = reqBody.getListingBody();
		int[] listingCategories = reqBody.getListingCategories();
		long listingPrice = reqBody.getListingPrice();
		String listingAddress = reqBody.getListingAddress();

		Listing listing = listingService.createListing(listingTitle, listingBody, listingAddress, listingPrice, img,
				listingCategories, userId);

		return new ResponseEntity<Listing>(listing, HttpStatus.OK);

	}

	@GetMapping("/{listingId}")
	public ResponseEntity<Listing> getListingById(@PathVariable int listingId,
			@RequestHeader(name = "Authorization") String jwtToken) {
		Listing listing = listingService.getListingById(listingId);

		return new ResponseEntity<Listing>(listing, HttpStatus.OK);
	}

	@GetMapping("/myListings")
	public ResponseEntity<MyListingsResponseBody> getUserOwnedListings(
			@RequestHeader(name = "Authorization") String jwtToken) {
		MyListingsResponseBody listingsResponseBody = new MyListingsResponseBody();
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));

		listingsResponseBody.setListings(listingService.getListingsByUserId(userId));

		return new ResponseEntity<MyListingsResponseBody>(listingsResponseBody, HttpStatus.OK);
	}

	@GetMapping("/mySavedListings")
	public ResponseEntity<MyListingsResponseBody> getUserSavedListings(
			@RequestHeader(name = "Authorization") String jwtToken) {
		MyListingsResponseBody listingsResponseBody = new MyListingsResponseBody();
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));

		listingsResponseBody.setListings(listingService.getSavedListingByUser(userId));

		return new ResponseEntity<MyListingsResponseBody>(listingsResponseBody, HttpStatus.OK);
	}

	@PostMapping("/getListings")
	public ResponseEntity<GetAllListingsResponseBody> getAllListings(@RequestBody GetAllListingsRequestBody reqBody) {
		int[] listingCategoriesIds = reqBody.getListingCategoriesIds();
		int pageSize = reqBody.getPageSize();
		int pageIndex = reqBody.getPageIndex();

		GetAllListingsResponseBody resBody = new GetAllListingsResponseBody();
		resBody.setPageIndex(pageIndex);
		resBody.setListingPage(listingService.getListingsPageByCategories(pageSize, pageIndex, listingCategoriesIds));
		resBody.setPageSize(pageSize);
		ResponseEntity<GetAllListingsResponseBody> response = new ResponseEntity<GetAllListingsResponseBody>(resBody,
				HttpStatus.OK);
		return response;

	}

	@PostMapping("/search")
	public ResponseEntity<SearchListingResponseBody> searchListings(@RequestBody SearchListingRequestBody reqBody) {
		String searchString = reqBody.getSearchString();
		int pageSize = reqBody.getPageSize();
		int pageIndex = reqBody.getPageIndex();
		SearchListingResponseBody resBody = new SearchListingResponseBody();
		resBody.setPageIndex(pageIndex);
		resBody.setPageSize(pageSize);
		resBody.setSearchString(searchString);
		resBody.setListingPage(listingService.getListingsPageByString(pageSize, pageIndex, searchString));
		return new ResponseEntity<SearchListingResponseBody>(resBody, HttpStatus.OK);
	}

	@PostMapping("/save/{listingId}")
	public ResponseEntity<BasicStringResponseBody> saveListing(@PathVariable int listingId,
			@RequestHeader(name = "Authorization") String jwtToken) {
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		BasicStringResponseBody resBody = new BasicStringResponseBody();
		if (listingService.checkCanSave(userId, listingId)) {
			UserSavedListing saved = listingService.saveListing(userId, listingId);
			resBody.setMessage("Save Listing sucessfully");
			return new ResponseEntity<BasicStringResponseBody>(resBody, HttpStatus.OK);
		}
		resBody.setMessage("You already saved this listing");
		return new ResponseEntity<BasicStringResponseBody>(resBody, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/canSave/{listingId}")
	public ResponseEntity<BasicBooleanResponseBody> getCanSaveListing(@PathVariable int listingId,
			@RequestHeader(name = "Authorization") String jwtToken) {
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		BasicBooleanResponseBody resBody = new BasicBooleanResponseBody();
		if (listingService.checkCanSave(userId, listingId)) {
			resBody.setValue(true);
			return new ResponseEntity<BasicBooleanResponseBody>(resBody, HttpStatus.OK);
		}
		resBody.setValue(false);
		return new ResponseEntity<BasicBooleanResponseBody>(resBody, HttpStatus.OK);
	}

	@PostMapping("/unsave/{listingId}")
	public ResponseEntity<BasicStringResponseBody> unsaveListing(@PathVariable int listingId,
			@RequestHeader(name = "Authorization") String jwtToken) {
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		BasicStringResponseBody resBody = new BasicStringResponseBody();
		if (!listingService.checkCanSave(userId, listingId)) {
			listingService.unsaveListing(userId, listingId);
			resBody.setMessage("Unsaved sucessfully");
			return new ResponseEntity<BasicStringResponseBody>(resBody, HttpStatus.BAD_REQUEST);
		}
		resBody.setMessage("You have not saved this listing");
		return new ResponseEntity<BasicStringResponseBody>(resBody, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("update/{listingId}")
	public ResponseEntity<BasicStringResponseBody> updateListing(@PathVariable int listingId,
			@RequestHeader(name = "Authorization") String jwtToken, @RequestBody UpdateListingRequestBody reqBody) {
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		BasicStringResponseBody resBody = new BasicStringResponseBody();

		if (listingService.getListingOwnerId(listingId) != userId) {
			resBody.setMessage("You are not allowed to update this content");
			return new ResponseEntity<BasicStringResponseBody>(resBody, HttpStatus.FORBIDDEN);
		}
		Listing listing = listingService.getListingById(listingId);
		listing.setListingAddress(reqBody.getListingAddress());
		listing.setListingBody(reqBody.getListingBody());
		listing.setListingPrice(reqBody.getListingPrice());
		listing.setListingCategories(listingService.getListingCategoriesFromId(reqBody.getListingCategories()));
		listing.setListingTitle(reqBody.getListingTitle());
		listingService.updateListing(listing);
		resBody.setMessage("Update Listing sucessfully");
		return new ResponseEntity<BasicStringResponseBody>(resBody, HttpStatus.OK);
	}

	@PostMapping("delete/{listingId}")
	public ResponseEntity<BasicStringResponseBody> deleteListing(@PathVariable int listingId,
			@RequestHeader(name = "Authorization") String jwtToken) {
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		BasicStringResponseBody resBody = new BasicStringResponseBody();
		if (listingService.getListingOwnerId(listingId) != userId) {
			resBody.setMessage("You are not allowed to delete this content");
			return new ResponseEntity<BasicStringResponseBody>(resBody, HttpStatus.FORBIDDEN);
		}
		Listing listing = listingService.getListingById(listingId);
		listingService.deleteListing(listing);
		resBody.setMessage("Delete listing successfully");
		return new ResponseEntity<BasicStringResponseBody>(resBody, HttpStatus.OK);

	}

	@PostMapping("/markAsSold/{listingId}")
	public ResponseEntity<BasicStringResponseBody> markAsSold(@PathVariable int listingId,
			@RequestHeader(name = "Authorization") String jwtToken) {
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		BasicStringResponseBody resBody = new BasicStringResponseBody();
		if (listingService.getListingOwnerId(listingId) != userId) {
			resBody.setMessage("You are not allowed to edit this content");
			return new ResponseEntity<BasicStringResponseBody>(resBody, HttpStatus.FORBIDDEN);
		}
		listingService.markListingAsSold(listingId);
		resBody.setMessage("Mark listing as sold sucessfully");
		return new ResponseEntity<BasicStringResponseBody>(resBody, HttpStatus.OK);

	}

	@GetMapping("/getPosterProfile/{listingId}")
	public ResponseEntity<GetProfileResponseBody> getPosterProfile(@PathVariable int listingId,
			@RequestHeader(name = "Authorization") String jwtToken) {
		Listing listing = listingService.getListingById(listingId);
		UserProfile user = listing.getPoster();
		GetProfileResponseBody resBody = new GetProfileResponseBody(user.getUserId(), user.getUserAddress(),
				user.getUserEmail(), user.getUserFullName(), user.getUserPhone(), user.getUserPic(),
				userRatingService.getUserLikeCount(user.getUserId()), userRatingService.getUserDisLikeCount(user.getUserId()));

		return new ResponseEntity<GetProfileResponseBody>(resBody, HttpStatus.OK);
	}
}
