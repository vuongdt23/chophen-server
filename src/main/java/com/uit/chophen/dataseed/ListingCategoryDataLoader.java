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
		
		clothing.setListingCategoryName("Clothing");
		clothing.setListingCategoryIcon("https://storage.googleapis.com/chophen-62aae.appspot.com/catImg/clothing.png");
		books.setListingCategoryName("Books");
		books.setListingCategoryIcon("https://storage.googleapis.com/chophen-62aae.appspot.com/catImg/book.png");
		electronics.setListingCategoryName("Electronics");
		electronics.setListingCategoryIcon("https://storage.googleapis.com/chophen-62aae.appspot.com/catImg/electronic-device.png");
		furniture.setListingCategoryName("Furniture");
		furniture.setListingCategoryIcon("https://storage.googleapis.com/chophen-62aae.appspot.com/catImg/furnitures.png");
		jewelry.setListingCategoryName("Jewelry");
		jewelry.setListingCategoryIcon("https://storage.googleapis.com/chophen-62aae.appspot.com/catImg/jewelry.png");
		sporting.setListingCategoryName("Sporting");
		sporting.setListingCategoryIcon("https://storage.googleapis.com/chophen-62aae.appspot.com/catImg/sports.png");
		watches.setListingCategoryName("Watches");
		watches.setListingCategoryIcon("https://storage.googleapis.com/chophen-62aae.appspot.com/catImg/watch.png");
		tools.setListingCategoryName("Tools");
		tools.setListingCategoryIcon("https://storage.googleapis.com/chophen-62aae.appspot.com/catImg/tool-box.png");
		musicalInstruments.setListingCategoryName("Musical Instruments");
		musicalInstruments.setListingCategoryIcon("https://storage.googleapis.com/chophen-62aae.appspot.com/catImg/music-instrument.png");
		other.setListingCategoryName("Other");
		other.setListingCategoryIcon("https://storage.googleapis.com/chophen-62aae.appspot.com/catImg/miscellaneous.png");

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
