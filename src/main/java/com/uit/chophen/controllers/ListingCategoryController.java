package com.uit.chophen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.chophen.entities.ListingCategory;
import com.uit.chophen.httpdomains.response.GetAllCategoriesResponseBody;
import com.uit.chophen.services.ListingService;
import com.uit.chophen.utils.JWTTokenProvider;


@RestController
@RequestMapping(value = "/listingCategories")
public class ListingCategoryController {

	private JWTTokenProvider jwtTokenProvider;
	private ListingService listingService;
	
	@Autowired
	public ListingCategoryController(JWTTokenProvider jwtTokenProvider, ListingService listingService) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
		this.listingService = listingService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<GetAllCategoriesResponseBody> getAllCategories(){
		GetAllCategoriesResponseBody resBody = new GetAllCategoriesResponseBody();
		
		List<ListingCategory> categories = listingService.getAllListingCategories();
		resBody.setCategories(categories);
		resBody.setCount(categories.size());
		return new ResponseEntity<GetAllCategoriesResponseBody>(resBody, HttpStatus.OK);
	}

}
