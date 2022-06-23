package com.uit.chophen.httpdomains.request;

import java.util.List;

import com.uit.chophen.entities.University;

public class GetUniListResponseBody {
 
	private List<University> universityList;

	public List<University> getUniversityList() {
		return universityList;
	}

	public void setUniversityList(List<University> universityList) {
		this.universityList = universityList;
	}
}
