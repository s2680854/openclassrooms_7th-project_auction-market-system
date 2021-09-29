package com.nnk.springboot;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.repositories.BidsListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BidsTests {

	@Autowired
	private BidsListRepository bidsListRepository;

	/*@Test
	public void bidListTest() {
		BidsList bid = new BidsList("Account Test", "Type Test", 10d);

		// Save
		bid = bidsListRepository.save(bid);
		assertEquals(bid.getBidQuantity(), 10d, 10d);

		// Update
		bid.setBidQuantity(20d);
		bid = bidsListRepository.save(bid);
		assertEquals(bid.getBidQuantity(), 20d, 20d);

		// Find
		List<BidsList> listResult = bidsListRepository.findAll();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bid.getBidListId();
		bidsListRepository.delete(bid);
		Optional<BidsList> bidList = bidsListRepository.findById(id);
		assertFalse(bidList.isPresent());
	}*/
}
