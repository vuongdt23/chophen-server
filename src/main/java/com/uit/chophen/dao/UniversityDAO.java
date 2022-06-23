package com.uit.chophen.dao;

import java.util.List;

import com.uit.chophen.entities.University;

public interface UniversityDAO {
	public University save(University uni);
	public long getCount();
	public List<University> getAll();
}
