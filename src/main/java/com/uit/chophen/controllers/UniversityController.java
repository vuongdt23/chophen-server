package com.uit.chophen.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.chophen.dao.UniversityDAO;
import com.uit.chophen.httpdomains.request.GetUniListResponseBody;

@RestController
@RequestMapping(value = "/uni")
public class UniversityController {
	private UniversityDAO universityDAO;

	@Autowired
	public UniversityController(UniversityDAO universityDAO) {
		super();
		this.universityDAO = universityDAO;
	}

	
	@GetMapping("/getAll")
	public ResponseEntity<GetUniListResponseBody> getAll(){
		GetUniListResponseBody responseBody = new GetUniListResponseBody();
		responseBody.setUniversityList(universityDAO.getAll());
		return new ResponseEntity<GetUniListResponseBody>(responseBody, HttpStatus.OK);
	}
}
