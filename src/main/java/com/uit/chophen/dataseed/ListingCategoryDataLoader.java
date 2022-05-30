package com.uit.chophen.dataseed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.uit.chophen.dao.ListingCategoryDAO;
import com.uit.chophen.entities.ListingCategory;


@Component
public class ListingCategoryDataLoader implements CommandLineRunner {

	private ListingCategoryDAO listingCategoryDAO;

	@Autowired
	public ListingCategoryDataLoader(ListingCategoryDAO listingCategoryDAO) {
		this.listingCategoryDAO = listingCategoryDAO;
	}

	@Transactional
	private void loadCategoryData() {
		ListingCategory clothing =  new ListingCategory();
		clothing.setListingCategoryName("Clothing");
		ListingCategory books =  new ListingCategory();
		ListingCategory electronics =  new ListingCategory();
		ListingCategory furniture =  new ListingCategory();
		ListingCategory jewelry =  new ListingCategory();
		ListingCategory sporting =  new ListingCategory();
		ListingCategory watches =  new ListingCategory();
		ListingCategory tools =  new ListingCategory();
		ListingCategory musicalInstruments =  new ListingCategory();
		ListingCategory other =  new ListingCategory();
		
		clothing.setListingCategoryName("clothing");
		books.setListingCategoryName("books");
		electronics.setListingCategoryName("electronics");
		furniture.setListingCategoryName("furniture");
		jewelry.setListingCategoryName("jewelry");
		sporting.setListingCategoryName("sporting");
		watches.setListingCategoryName("watches");
		tools.setListingCategoryName("tools");
		musicalInstruments.setListingCategoryName("musicalInstruments");
		other.setListingCategoryName("other");

		listingCategoryDAO.save(clothing);
		listingCategoryDAO.save(books);
		listingCategoryDAO.save(electronics);
		listingCategoryDAO.save(furniture);
		listingCategoryDAO.save(jewelry);
		listingCategoryDAO.save(sporting);
		listingCategoryDAO.save(watches);
		listingCategoryDAO.save(tools);
		listingCategoryDAO.save(musicalInstruments);
		listingCategoryDAO.save(other);

		

	}

	@Override
	public void run(String... args) throws Exception {
	if(listingCategoryDAO.getCount()==0) {
		loadCategoryData();
	}
	else {
		return;
	}

	}

}
