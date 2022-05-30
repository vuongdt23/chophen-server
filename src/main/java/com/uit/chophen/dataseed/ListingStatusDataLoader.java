package com.uit.chophen.dataseed;
import static com.uit.chophen.utils.MiscConstants.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.uit.chophen.dao.ListingStatusDAO;
import com.uit.chophen.entities.ListingStatus;

@Component
public class ListingStatusDataLoader implements CommandLineRunner {

	private ListingStatusDAO listingStatusDAO;

	public ListingStatusDataLoader(ListingStatusDAO listingStatusDAO) {
		super();
		this.listingStatusDAO = listingStatusDAO;
	}

	@Transactional
	private void loadStatusData() {
		ListingStatus available = new ListingStatus();
		ListingStatus sold = new ListingStatus();
		available.setListingStatusName("Available");
		available.setListingStatusId(AVAILABLE_STATUS_ID);
		available.setListingStatusDescription("Still selling");
		sold.setListingStatusDescription("Already Sold");
		sold.setListingStatusName("Sold");
		sold.setListingStatusId(SOLD_STATUS_ID);

		listingStatusDAO.save(available);
		listingStatusDAO.save(sold);
	}

	@Override
	public void run(String... args) throws Exception {
		if (listingStatusDAO.getCount() == 0) {
			loadStatusData();
		} else {
			return;
		}

	}

}
